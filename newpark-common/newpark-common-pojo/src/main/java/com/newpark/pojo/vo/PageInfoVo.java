package com.newpark.pojo.vo;

import com.newpark.base.vali.ValidatedStrMsg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * @Author xxs18
 * @Description 分页实体类
 * @Date 2023/6/9 14:28
 **/
@Getter
@Setter
public class PageInfoVo {

    /**
     * 当前页,偏移量
     **/
    @ApiModelProperty(value = "当前页,偏移量" , required = true)
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    @Min(value = 0,message = ValidatedStrMsg.ERROR_MSG)
    private Integer pageNo = 1;

    /**
     * 页大小,偏移量
     **/
    @ApiModelProperty(value = "当前页大小,偏移量" , required = true)
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    @Min(value = 5,message = ValidatedStrMsg.ERROR_MSG)
    @Max(value = 20,message = ValidatedStrMsg.PAGE_SIZE_MSG)
    private Integer pageSize = 20;

}
