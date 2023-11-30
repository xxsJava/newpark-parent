package com.newpark.resources.config;

import cn.hutool.crypto.digest.MD5;
import com.newpark.resources.utils.IdGeneratorSnowflake;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author xxs18
 * @Description 上传配置
 * @Date 2023/11/30 9:30
 **/
@Getter
@Setter
@Component
public class UploadConfig {

    /**
     * 分片上传唯一ID
     */
    private String uploadId;

    /**
     * 分片视频列表前缀
     */
    private String prefix;

    /**
     * 分片大小 秒单位
     */
    private Integer uploadSize = 60;

}
