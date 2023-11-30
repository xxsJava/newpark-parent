package com.newpark.resources.utils;

import com.newpark.resources.entity.dto.PartETagDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author jack
 * @date 2023/8/11
 */
@Data
@ApiModel(value = "分片上传参数DTO")
public class UploadDTO {

    @ApiModelProperty(value = "分片标识", required = true)
    private String uploadId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "UUID")
    private String taskId;

    @ApiModelProperty(value = "当前片数", required = true)
    private Integer partNumber;

    @ApiModelProperty(value = "当前片大小", required = true)
    private Integer partSize;

    @ApiModelProperty(value = "总片数", required = true)
    private Integer totalPart;

    @ApiModelProperty(value = "md5")
    private String md5;

    @ApiModelProperty(value = "分片文件参数")
    private Map<Integer, PartETagDTO> partETags;

    @ApiModelProperty(value = "分片文件", required = true)
    private MultipartFile file;
}
