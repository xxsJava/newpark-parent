package com.newpark.resources.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newpark.base.model.vo.R;
import com.newpark.resources.config.UploadConfig;
import com.newpark.resources.service.IFileService;
import com.newpark.resources.utils.IdGeneratorSnowflake;
import com.newpark.resources.utils.OssUtils;
import com.newpark.resources.utils.UploadDTO;

import cn.hutool.crypto.digest.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/29 10:12
 **/
@RestController
@RequestMapping("/uploadFile")
@Api(value = "uploadFile", tags = "文件上传功能")
public class FileController {

    @Resource
    private IFileService imageService;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;

    @Resource
    private OssUtils ossUtils;

    @Resource
    private UploadConfig uploadConfig;

    @PostMapping("/filesApi")
    @ApiOperation(notes = "文件批量上传API", value = "文件批量上传API")
    public R<?> uploadFiles(List<MultipartFile> files) {
        return R.ok(imageService.uploadFile(files));
    }

    @PostMapping("/videosApi")
    @ApiOperation(notes = "视频分片上传API", value = "视频分片上传API")
    public R<?> uploadVideos(List<MultipartFile> files, String uploadId) {
        return imageService.uploadVideos(files, uploadId);
    }

    @PostMapping("/videosIDApi")
    @ApiOperation(notes = "视频切片上传配置 API", value = "视频切片上传配置 API")
    public R<?> uploadVideosId() {
        String endPointStr = endPoint.substring(endPoint.lastIndexOf("oss"));
        uploadConfig.setUploadId(MD5.create().digestHex16(idGeneratorSnowflake.snowflakeId() + ""));
        uploadConfig.setPrefix("https://" + bucketName + "." + endPointStr + "/" + uploadConfig.getUploadId());
        return R.ok(uploadConfig);
    }

    @PostMapping("/fileShardingIdApi")
    @ApiOperation(notes = "获取分片上传唯一ID API", value = "获取分片上传唯一ID API")
    public R<?> uploadShardingId(UploadDTO uploadFiles) {
        return R.ok(ossUtils.getUploadId(uploadFiles));
    }

    @PostMapping("/fileShardingApi")
    @ApiOperation(notes = "文件分片上传API", value = "文件分片上传API")
    public R<?> uploadSharding(UploadDTO uploadFiles) throws IOException, InterruptedException {
        return R.ok(ossUtils.handelShardingFile(uploadFiles));
    }

}
