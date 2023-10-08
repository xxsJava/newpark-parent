package com.newpark.mybatisplus.interceptor;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import com.newpark.mybatisplus.annotation.DataAuth;
import com.newpark.mybatisplus.entity.UserDetailsEntity;
import com.newpark.mybatisplus.enums.SysPermFieldValEnum;
import com.newpark.mybatisplus.feign.vo.DataPermItemVo;
import com.newpark.mybatisplus.util.JdbcTemplateUtils;
import com.zh.mustang.common.redis.utils.RedisUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * mybatis数据权限拦截器
 *
 * @author : jack
 * @date :  2023/3/18
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class DataAuthorityInterceptor implements Interceptor {

    private static final String SESSION = "session:";

    private RedisUtils redisUtils;

    public DataAuthorityInterceptor(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);

        // 获取方法上的数据权限注解，如果没有注解，则直接通过
        DataAuth dataAuth = getPermissionByDelegate(mappedStatement);
        if (dataAuth == null) {
            return invocation.proceed();
        }
        // 获取request信息，得到当前登录用户信息
        RequestAttributes req = RequestContextHolder.getRequestAttributes();
        if (req == null) {
            return invocation.proceed();
        }
        //获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取 header 的token 值
        String CLIENT_LOGIN_TOKEN = "zh-share-token";
        String token = request.getHeader(CLIENT_LOGIN_TOKEN);
        //用户详情
        String userDetailStr = redisUtils.get(SESSION + token);
        UserDetailsEntity userDetailsEntity = JSON.parseObject(userDetailStr, new TypeReference<UserDetailsEntity>() {
        });
        //条件sql
        StringBuffer conditionSql = new StringBuffer();
        //原始sql
        String originalSql = boundSql.getSql().trim();
        //如果有配置数据权限项
        if (dataAuth != null) {
            //菜单数据权限值
            String roleConfigItemListStr;
            List<DataPermItemVo> roleConfigItemList;
            if (userDetailsEntity != null) {
                roleConfigItemListStr = redisUtils.get("sys:dataPermConfig:roleConfigItemList");
            } else {
                return invocation.proceed();
            }
            if (roleConfigItemListStr != null && !roleConfigItemListStr.equals("")) {
                roleConfigItemList = JSON.parseObject(roleConfigItemListStr, new TypeReference<List<DataPermItemVo>>() {
                });
            } else {
                //TODO 权限明细数据
                roleConfigItemList = new ArrayList<DataPermItemVo>();
            }
            //默认注解参数菜单id
            Long menuId = Long.parseLong(dataAuth.menuId());
            //动态权限菜单id
            Long permMenuId = this.getPermMenuId(parameter);
            if (permMenuId != null) {
                menuId = permMenuId;
            }
            //分组集合并取出对应菜单的权限集合数据
            Map<Long, List<DataPermItemVo>> allConfigItemListMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(roleConfigItemList)) {
                allConfigItemListMap = roleConfigItemList.stream().collect(Collectors.groupingBy(DataPermItemVo::getMenuId));
            }
            roleConfigItemList = allConfigItemListMap.get(menuId);
            //动态根据定义的枚举值赋予实际的真实值数据
            if (roleConfigItemList != null) {
                Iterator<DataPermItemVo> iterator = roleConfigItemList.iterator();
                while (iterator.hasNext()) {
                    DataPermItemVo dpiv = iterator.next();
                    if (dpiv.getFieldName().equals(-1)) {
                        dpiv.setValue(dpiv.getFieldVal());
                    } else {
                        //当前登录用户
                        if (dpiv.getFieldVal().equals(SysPermFieldValEnum.LoginUser.getCode())) {
                            dpiv.setValue(userDetailsEntity.getUserId());
                        }
                        //空
                        if (dpiv.getFieldVal().equals(SysPermFieldValEnum.Empty.getCode())) {
                            dpiv.setValue(" is null ");
                        }
                    }
                }
            }

            //迭代生成条件
            if (roleConfigItemList != null && roleConfigItemList.size() > 0) {
                conditionSql.append(" and (");
                Map<Long, List<DataPermItemVo>> listMap = roleConfigItemList.stream().collect(Collectors.groupingBy(DataPermItemVo::getConfigId));
                int loop = 0;
                for (Long key : listMap.keySet()) {
                    List<DataPermItemVo> ciList = listMap.get(key);
                    conditionSql.append(" (");
                    for (int i = 0; i < ciList.size(); i++) {
                        DataPermItemVo ob = ciList.get(i);
                        conditionSql.append(ob.getFieldCode());
                        if (ob.getValue() instanceof Long || ob.getValue() instanceof Integer || ob.getValue() instanceof Double) {
                            conditionSql.append("=" + ob.getValue());
                        } else if (ob.getValue() instanceof String) {
                            if (ob.getValue().toString().indexOf("is null") > 0) {
                                conditionSql.append(ob.getValue());
                            } else {
                                conditionSql.append("='" + ob.getValue() + "'");
                            }
                        } else if (ob.getValue() instanceof List) {
                            conditionSql.append(" in " + LongIdListToString((List<Long>) ob.getValue()));
                        } else if (ob.getValue() instanceof Set) {
                            List<String> result = new ArrayList<String>((Set<String>) ob.getValue());
                            conditionSql.append(" in " + listToString(result));
                        }
                        if (i != ciList.size() - 1) {
                            conditionSql.append("  and  ");
                        }
                    }
                    conditionSql.append(" )");
                    loop++;
                    if (loop != listMap.keySet().size()) {
                        conditionSql.append(" or ");
                    }
                }
                conditionSql.append(" )");
            } else {
                //非系统角色类型执行权限控制
                if (!userDetailsEntity.getRoleType().equals("1")) {
                    //没有发现配置则默认按照管理类角色管辖机构非管理类角色归属机构
                    //没有配置数据权限的菜单页，有管辖机构的按管辖机构控制，没有管辖机构的按归属机构控制
                    String dynamicField = this.createDynamicField(originalSql, dataAuth);
                    if (!dynamicField.equals("")) {
                        if (userDetailsEntity.getIsManage() && userDetailsEntity.getManageDepts() != null && userDetailsEntity.getManageDepts().size() > 0) {
                            conditionSql.append(" and  " + dynamicField);
                            List<String> result = new ArrayList<String>((Set<String>) userDetailsEntity.getManageDepts());
                            conditionSql.append(" in " + listToString(result));
                        } else {
                            if (userDetailsEntity.getDeptIds() != null) {
                                conditionSql.append(" and  " + dynamicField);
                                conditionSql.append(" in " + listToString(new ArrayList<String>((Set<String>) userDetailsEntity.getDeptIds())));
                            }
                        }
                    }
                }
            }
            //设置sql条件
            setConditionSql(conditionSql, originalSql, mappedStatement, boundSql, args);
        }
        Object obj = invocation.proceed();
        return obj;
    }

    /**
     * 生成动态字段
     *
     * @param originalSql
     * @return
     */
    private String createDynamicField(String originalSql, DataAuth dataAuth) throws Throwable {
        //字段名
        String field = "";
        //表名
        String tableName = "";
        //包含字段列表
        List<String> includeFields = new ArrayList<>();

        //排除表名列表
        List<String> excludeTables = new ArrayList<>();

        //得到sql声明列表
        List<SQLStatement> sqlStatementList = this.getSQLStatementList(originalSql);
        //默认为一条sql语句
        SQLStatement stmt = sqlStatementList.get(0);
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        stmt.accept(visitor);
        //获取字段名称
        Collection<TableStat.Column> columns = visitor.getColumns();
        //获取表名
        Iterator<TableStat.Name> tableIterator = visitor.getTables().keySet().iterator();
        while (tableIterator.hasNext()) {
            TableStat.Name nameObj = tableIterator.next();
            tableName = nameObj.getName();
            if (!dataAuth.tableName().equals("") && tableName.equals(dataAuth.tableName())) {
                break;
            }
        }
        Iterator<TableStat.Column> iterator = columns.iterator();
        //匹配sql语句中字段名字并返回(适用于sql语句中字段匹配)
        while (iterator.hasNext()) {
            TableStat.Column column = iterator.next();
            if (includeFields.contains(column.getName()) && !excludeTables.contains(tableName)) {
                field = column.getName();
                break;
            }
        }

        //如果匹配不出字段说明用了语句中不包含字段则直接读表(直接读表万能匹配)
        if (field.equals("")) {
            JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();
            RowCountCallbackHandler rcch = new RowCountCallbackHandler();
            jdbcTemplate.query("select * from " + tableName + "  limit 1", rcch);
            String[] columnNames = rcch.getColumnNames();
            for (int i = 0; i < columnNames.length; i++) {
                if (includeFields.contains(columnNames[i]) && !excludeTables.contains(tableName)) {
                    field = columnNames[i];
                    break;
                }
            }
        }
        return field;
    }

    /**
     * 设置sql条件
     *
     * @param conditionSql
     * @param originalSql
     * @param mappedStatement
     * @param boundSql
     * @param args
     */
    private void setConditionSql(StringBuffer conditionSql, String originalSql, MappedStatement mappedStatement, BoundSql boundSql, Object[] args) {
        if (!conditionSql.equals("")) {
            String riskSql = "select a.* from (" + originalSql + ")a  where 1=1  " + conditionSql;

            BoundSql newBoundSql = copyFromBoundSql(mappedStatement, boundSql, riskSql);
            ParameterMap map = mappedStatement.getParameterMap();
            MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql), map);
            args[0] = newMs;

            //6个参数时候替换参数对象
            if (args.length == 6) {
                args[5] = newBoundSql;
            }
        }
    }

    /**
     * 取出参数中动态赋予的菜单id(适用于已经映射到sql中的参数获取否则获取不到参数值)
     *
     * @param mappedStatement
     * @param boundSql
     * @return
     */
    private Long getPermMenuId(MappedStatement mappedStatement, BoundSql boundSql) {
        Long menuId = null;
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();
        Configuration configuration = mappedStatement.getConfiguration();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (parameterMappings != null) {
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    //参数值
                    Object value;
                    //获取参数名称
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        //获取参数值
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        //如果是单个值则直接赋值
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    //取出权限菜单id
                    if (propertyName.indexOf("permMenuId") > -1) {
                        menuId = Long.parseLong(value.toString());
                        break;
                    }
                }
            }
        }
        return menuId;
    }

    /**
     * 取出参数中动态赋予的菜单id(适用于参数对象的参数获取无需映射到sql都能获取到参数)
     *
     * @param parameter
     * @return
     */
    private Long getPermMenuId(Object parameter) {
        Long menuId = null;
        if (parameter != null) {
            if (parameter instanceof Map) {
                Map map = (Map) parameter;
                for (Object obj : map.values()) {
                    if (obj.toString().indexOf("permMenuId") > -1) {
                        Map ob = JSONObject.parseObject(JSONObject.toJSONString(obj), Map.class);
                        if (ob.containsKey("permMenuId")) {
                            menuId = Long.parseLong(ob.get("permMenuId").toString());
                            break;
                        }
                    }
                }
            }
        }
        return menuId;
    }


    public class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    /**
     * 复制MappedStatement对象
     */
    private MappedStatement copyFromMappedStatement(MappedStatement ms,
                                                    SqlSource newSqlSource, ParameterMap parameterMap) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(),
                newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        // builder.keyProperty(ms.getKeyProperty());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(parameterMap);
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    /**
     * 复制BoundSql对象
     */
    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
                                      String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql,
                boundSql.getParameterMappings(), boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql
                        .getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    /**
     * 获取数据权限注解信息
     *
     * @param mappedStatement
     * @return
     */
    private DataAuth getPermissionByDelegate(MappedStatement mappedStatement) {
        DataAuth dataAuth = null;
        try {
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.substring(id.lastIndexOf(".") + 1, id.length());
            final Class<?> cls = Class.forName(className);
            final Method[] method = cls.getMethods();
            for (Method me : method) {
                if (me.getName().equals(methodName) && me.isAnnotationPresent(DataAuth.class)) {
                    dataAuth = me.getAnnotation(DataAuth.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataAuth;
    }

    @Override
    public Object plugin(Object arg0) {
        if (arg0 instanceof Executor) {
            return Plugin.wrap(arg0, this);
        }
        return arg0;
    }

    @Override
    public void setProperties(Properties arg0) {

    }

    /**
     * 将String集合拼装成字符串格式：（'A','B','C'）
     *
     * @param list 字符串集合
     * @return 返回拼接字符
     */
    public static String listToString(List<String> list) {
        if (list.size() > 0) {
            String string = list.stream().collect(Collectors.joining("','", "('", "')")).replaceAll("\n", "").replaceAll(" ", "");
            return string;
        }
        return "";
    }

    /**
     * 将Long集合拼装成字符串格式：（'A','B','C'）
     *
     * @param list 字符串集合
     * @return 返回拼接字符
     */
    public static String LongIdListToString(List<Long> list) {
        List<String> ids = new ArrayList<>();
        list.forEach(x -> ids.add(String.valueOf(x)));
        return listToString(ids);
    }

    /**
     * 得到sql声明列表
     *
     * @param sql
     * @return
     */
    private List<SQLStatement> getSQLStatementList(String sql) {
        String dbType = String.valueOf(JdbcConstants.MYSQL);
        return SQLUtils.parseStatements(sql, dbType);
    }

}
