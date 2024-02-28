package com.newpark.pay.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.newpark.pay.service.mapper.SysRoutsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.newpark.base.model.vo.R;
import com.newpark.core.utils.DateUtils;
import com.newpark.core.utils.SnowflakeIdWorker;
import com.newpark.pojo.SysRouts;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/12/20 16:44
 **/
@RestController
@RequestMapping("/v1")
public class SysController {

    @Resource
    private SysRoutsMapper sysRoutsMapper;

    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping(value = "/getAllUrl", method = RequestMethod.POST)
    public R<?> getAllUrl() {

        List<Integer> sysRoutLists = new ArrayList<>();

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            SysRouts sysRouts = new SysRouts();
            //序列ID
            sysRouts.setRsId(new SnowflakeIdWorker(1,3).nextId());
            //权限名称
            sysRouts.setRsName("pay");
            //权限描述
            sysRouts.setRsDesc("支付请求");
            //权限创建时间
            sysRouts.setRsCreateTime(DateUtils.getTimes());
            //权限修改时间
            sysRouts.setRsUpdateTime(DateUtils.getTimes());
            //权限类型
            sysRouts.setRsType("URL");

            Map<String, String> map1 = new HashMap<String, String>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                map1.put("url", url);
                //权限请求URL
                sysRouts.setRsReqUrl(url);
            }

            map1.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            //权限类名
            sysRouts.setRsClassName(method.getMethod().getDeclaringClass().getName());
            map1.put("method", method.getMethod().getName()); // 方法名
            //权限方法名
            sysRouts.setRsMethod(method.getMethod().getName());
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                map1.put("type", requestMethod.toString());
                //权限请求类型
                sysRouts.setRsReqType(requestMethod.toString());
            }
            list.add(map1);
            sysRoutLists.add(sysRoutsMapper.insert(sysRouts));
        }


        return R.ok(sysRoutLists);
    }

}
