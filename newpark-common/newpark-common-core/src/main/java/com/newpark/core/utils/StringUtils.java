package com.newpark.core.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.common.collect.Lists;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * String工具类
 *
 * @author : jack
 * @date : 2023/3/14
 */

public final class StringUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");

    //定义移动端请求的所有可能类型
    private final static List<String> agentList = Lists.newArrayList("Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser");


    static {
        //返回所有字符串
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //序列化日期
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_DATE));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);
        //Long类型序列化
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
    }


    private StringUtils() {
        throw new Error("工具类不能实例化！");
    }

    /**
     * 获取UUID
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        return jodd.util.StringUtil.capitalize(str);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String uncapitalize(String str) {
        return jodd.util.StringUtil.uncapitalize(str);
    }

    /**
     * 全部转小写
     *
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        return jodd.util.StringUtil.toLowerCase(str);
    }

    /**
     * 连续重复出现的指定字符只保留一个
     *
     * @param str
     * @param c
     * @return
     */
    public static String compressChars(String str, char c) {
        return jodd.util.StringUtil.compressChars(str, c);
    }

    /**
     * 转换字符串字符集。 如果字符集名称相同，则返回相同的字符串。
     *
     * @param str
     * @param srcCharsetName
     * @param newCharsetName
     * @return
     */
    public static String convertCharset(String str, String srcCharsetName, String newCharsetName) {
        return jodd.util.StringUtil.convertCharset(str, srcCharsetName, newCharsetName);
    }

    /**
     * 子字符串在源字符串指定位置出现的次数
     *
     * @param str        源字符
     * @param sub        子字符
     * @param startIndex 查询开始位置
     * @param ignoreCase 忽略大小写
     * @return
     */
    public static int count(String str, String sub, int startIndex, boolean ignoreCase) {
        return ignoreCase ? jodd.util.StringUtil.countIgnoreCase(str.substring(startIndex), sub) : jodd.util.StringUtil.count(str, sub, startIndex);
    }

    /**
     * 删除前缀和后缀
     *
     * @param str
     * @param prefix
     * @param suffix
     * @return
     */
    public static String cut(String str, String prefix, String suffix) {
        String source = str;
        if (jodd.util.StringUtil.isNotEmpty(prefix)) {
            source = jodd.util.StringUtil.cutPrefix(str, prefix);
        }

        if (jodd.util.StringUtil.isNotEmpty(suffix)) {
            source = jodd.util.StringUtil.cutSuffix(source, suffix);
        }

        return source;
    }

    /**
     * 删除前缀
     *
     * @param str
     * @param prefix
     * @return
     */
    public static String cutPrefix(String str, String prefix) {
        String source = str;
        if (jodd.util.StringUtil.isNotEmpty(prefix)) {
            source = jodd.util.StringUtil.cutPrefix(str, prefix);
        }
        return source;
    }

    /**
     * 将字符串从提供的子字符串的第一个索引切割到结尾。
     *
     * @param str
     * @param substring
     * @return
     * @apiNote aabbccdd bbccdd
     */
    public static String cutFrom(String str, String substring) {
        return jodd.util.StringUtil.cutFromIndexOf(str, substring);
    }

    /**
     * 将Long集合拼装成字符串格式：（'A','B','C'）
     *
     * @param list 字符串集合
     * @return 返回拼接字符
     */
    public static String longIdListToString(List<Long> list) {
        List<String> ids = new ArrayList<>();
        list.forEach(x -> {
            ids.add(String.valueOf(x));
        });
        return StringUtils.listToString(ids);
    }

    /**
     * 从开始到所提供的子字符串的第一个索引切割字符串。
     *
     * @param str
     * @param substring
     * @return
     * @apiNote aabbccdd aa
     */
    public static String cutTo(String str, String substring) {
        return jodd.util.StringUtil.cutToIndexOf(str, substring);
    }

    /**
     * 从开始到所提供的子字符串的第一个索引切割字符串(不包括substring)
     *
     * @param str
     * @param substring
     */
    public static String cutBarrSub(String str, String substring) {
        int i = str.indexOf(substring);
        if (i != -1) {
            str = str.substring(i + substring.length());
        }
        return str;
    }

    /**
     * 将集合内容 拼装成字符串
     *
     * @param collction
     * @param separator
     * @return
     */
    public static String join(Collection<?> collction, String separator) {
        return jodd.util.StringUtil.join(collction, separator);
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
        return StringUtils.listToString(ids);
    }

    /**
     * 将手机号码的第四到第五位数字加密**
     *
     * @param phone 手机号码
     * @return 返回部门加密的手机字符串
     */
    public static String encryptPhone(String phone) {
        if (isNotEmpty(phone) && phone.length() >= 5) {
            StringBuilder sb = new StringBuilder(phone);
            return sb.replace(3, 5, "**").toString();
        }
        return phone;
    }

    /**
     * 将多个手机号码的第四到第五位数字加密**(phone格式为  13456564382;15013759662)
     *
     * @param phone 手机号码
     * @return 返回部门加密的手机字符串
     */
    public static String encryptManyPhone(String phone) {
        if (isNotEmpty(phone)) {
            StringBuilder sbb = new StringBuilder();
            String[] phones = phone.split(";");
            for (int i = 0; i < phones.length; i++) {
                if (phones[i].length() >= 5) {
                    StringBuilder sb = new StringBuilder(phones[i]);
                    sb.replace(3, 5, "**").toString();
                    if (i == phones.length - 1) {
                        sbb.append(sb);
                    } else {
                        sbb.append(sb).append(";");
                    }
                }
            }
            return sbb.toString();
        }
        return phone;
    }

    /**
     * 根据对象某个属性去重集合
     *
     * @param keyExtractor lambda语法
     * @param <T>          类型泛型
     * @return 返回去重集合
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }

    /**
     * 是否为Null或为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return jodd.util.StringUtil.isEmpty(str);
    }

    /**
     * 是否不为Null或为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return jodd.util.StringUtil.isNotEmpty(str);
    }

    /**
     * 是否不为Null
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
        return null != str;
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * * 判断一个对象是否是数组类型（Java基本型别的数组）
     *
     * @param object 对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }


    /**
     * 是否只含有空格
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return jodd.util.StringUtil.isBlank(str);
    }

    /**
     * 返回两个字符串的相同前缀
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String maxCommonPrefix(String str1, String str2) {
        return jodd.util.StringUtil.maxCommonPrefix(str1, str2);
    }

    /**
     * 如果不存在前缀,则增加前缀
     *
     * @param str
     * @param prefix
     * @return
     */
    public static String prefix(String str, String prefix) {
        return jodd.util.StringUtil.prefix(str, prefix);
    }

    /**
     * 反转字符串
     *
     * @param str
     * @return
     */
    public static String reverse(String str) {
        return jodd.util.StringUtil.reverse(str);
    }

    /**
     * 不存在后缀，则添加后缀
     *
     * @param str
     * @param suffix
     * @return
     */
    public static String suffix(String str, String suffix) {
        return jodd.util.StringUtil.suffix(str, suffix);
    }

    /**
     * 不存在前后缀，则添加前后缀
     *
     * @param str
     * @param prefix
     * @param suffix
     * @return
     */
    public static String surround(String str, String prefix, String suffix) {
        return jodd.util.StringUtil.surround(str, prefix, suffix);
    }

    /**
     * 删除左边空格
     *
     * @param str
     * @return
     */
    public static String trimLeft(String str) {
        return jodd.util.StringUtil.trimLeft(str);
    }

    /**
     * 删除右边空格
     *
     * @param str
     * @return
     */
    public static String trimRight(String str) {
        return jodd.util.StringUtil.trimRight(str);
    }

    /**
     * 转换为下划线
     *
     * @param camelCaseName
     * @return
     */
    public static String underscoreName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public static boolean isNotBlank(String str) {
        return jodd.util.StringUtil.isNotBlank(str);
    }

    /**
     * 将数组拼装成字符串格式：A,B,C
     *
     * @param array 数组
     * @return 返回拼接字符
     */
    public static String arrayToString(String[] array) {
        List<String> resultList = new ArrayList<>(Arrays.asList(array));
        return String.join(",", resultList);
    }

    /**
     * 切割字符串 长度
     *
     * @param str
     * @param limitLength
     * @return
     */
    public static String limitLength(String str, Integer limitLength) {
        if (isBlank(str)) {
            return str;
        }

        if (str.length() <= limitLength) {
            return str;
        }
        return str.substring(0, limitLength);

    }

    /**
     * 是否为json
     *
     * @param str
     * @return
     */
    public static boolean isJSONType(String str) {
        boolean result = false;
        if (StringUtils.isNotBlank(str)) {
            str = str.trim();
            if (str.startsWith("{") && str.endsWith("}")) {
                result = true;
            } else if (str.startsWith("[") && str.endsWith("]")) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 集合 转sql in 的参数 如 （1,2,3）
     *
     * @param ids
     * @return
     */
    public static String getSqlInParam(Collection<Long> ids) {
        if (CollectionUtil.isNotEmpty(ids)) {
            return org.apache.commons.lang3.StringUtils.joinWith(",", ids)
                    .replace("[", "(").replace("]", ")");
        }
        return "";
    }

    /**
     * 获取exception 具体信息
     *
     * @param ex
     * @return
     */
    public static String getExceptionInfo(Throwable ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * 判断字符是否为空的json字符串
     * eg: null,"{}","[]","","null"
     *
     * @param str
     * @return
     */
    public static boolean isEmptyJsonStr(String str) {

        if (isBlank(str) || "{}".equals(str) || "[]".equals(str) || "null".equals(str)) {
            return true;
        }
        return false;
    }


    /**
     * 转换JSON格式
     *
     * @param
     * @return
     */
    public static String toObjectJSON(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 生成工单编号
     *
     * @param prefix 编号前缀
     * @return
     */
    public static String generateCode(String prefix) {
        return prefix + sdf.format(System.currentTimeMillis());
    }

    /**
     * 任务编号
     *
     * @param code 任务编号
     * @return String
     */
    public static String taskCode(String code) {

        String substring = code.substring(2, code.length());
        String prefix = code.substring(0, 2);
        int i = Integer.parseInt(substring) + 1;
        return prefix + String.format("%03d", i);
    }

    /**
     * 根据时分秒判断是否存在两位数据
     *
     * @param date 时分秒
     * @return string
     */
    public static String getStringDate(int date) {

        String str = "";
        if (date > 9) {
            return date + "";
        } else {
            return "0" + date;
        }
    }

    /**
     * 获取请求方式
     *
     * @param agent 请求头标识
     * @return 1=pc端/2=移动端
     */
    public static Integer getRequestType(String agent) {
        for (String s : agentList) {
            if (agent.contains(s)) {
                return 2;
            }
        }
        return 1;
    }
}
