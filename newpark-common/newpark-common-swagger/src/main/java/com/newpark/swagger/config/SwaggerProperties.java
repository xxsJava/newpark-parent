package com.newpark.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jack
 * @date 2023/3/14
 */
@Data
@ConfigurationProperties("swagger")
public class
SwaggerProperties {

    /**
     * 标题
     **/
    private String title = "newpark";

    /**
     * 版本
     **/
    private String version = "1.0";

    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl = "";
}
