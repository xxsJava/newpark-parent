package com.newpark.im.config;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.newpark.base.enums.Header;
import com.newpark.base.model.vo.R;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xxs18
 * @Description im请求api
 * @Date 2023/12/23 10:18
 **/
@Component
public class ImCoreReqConfig {

    @Resource
    private ImConfig imConfig;

    /**
     * post 请求
     */
    public String imPost(Map<String, Object> paramMap, String url) {
        // 链式构建请求
        return HttpRequest.post(imConfig.getApi() + url)
            .header(Header.OPERATION_ID.getValue(), new Date().getTime() + "")
            .body(JSONObject.toJSON(paramMap).toString())// body传参
            .timeout(20000)// 超时，毫秒
            .execute().body();
    }

    /**
     * post 请求带token
     */
    public String imPost(Map<String, Object> paramMap, String url,String token) {
        // 链式构建请求
        return HttpRequest.post(imConfig.getApi() + url)
                .header(Header.OPERATION_ID.getValue(), new Date().getTime() + "")
                .header("token",token)
                .body(JSONObject.toJSON(paramMap).toString())// body传参
                .timeout(20000)// 超时，毫秒
                .execute().body();
    }

    /**
     * get 请求
     */
    public String imGet(Map<String,Object> paramMap,String url){
        return "";
    }
}
