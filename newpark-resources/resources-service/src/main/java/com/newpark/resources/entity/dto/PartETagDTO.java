package com.newpark.resources.entity.dto;


import com.newpark.base.model.dto.BaseDTO;
import lombok.Data;

/**
 * @author jack
 * @date 2023/8/14
 */
@Data
public class PartETagDTO extends BaseDTO {

    private Integer partNumber;

    private String eTag;

    private Long partSize;

    private String partCRC;

}
