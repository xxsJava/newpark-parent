package com.newpark.core.config;

import com.alibaba.fastjson.JSON;
import com.newpark.base.model.vo.R;
import com.newpark.core.entity.InternalApiException;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 全局 feign 请求异常处理
 *
 * @author jack
 * @date 2023/3/14
 */
@Slf4j
@Configuration
@ConditionalOnClass(value = {feign.codec.ErrorDecoder.class})
public class FeignErrorDecoderConfig implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

//        log.error("feign request error path:service methodKey={},url={},method={},requestBody={}", methodKey,
//                response.request().url(), response.request().httpMethod(), response.request().requestBody().asString().replaceAll("\\n|\\t", ""));
        try {
            String json = Util.toString(response.body().asReader());
            R r = JSON.parseObject(json, R.class);
            if (null != r && r.getCode() == R.failed().getCode()) {
                // 此处开放可允许直接返回前端
                String[] url = response.request().url().split("//|/");
                return new InternalApiException("[" + url[2] + "]:" + r.getMsg(), r.getCode());
            }
        } catch (IOException ioException) {
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
