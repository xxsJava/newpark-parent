package com.newpark.sso.entity.vo;

import javax.validation.constraints.*;

import com.baomidou.mybatisplus.annotation.TableField;
import com.newpark.base.vali.ValidatedStrMsg;

import cn.hutool.crypto.digest.MD5;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description 注册vo
 * @Date 2023/10/20 10:16
 **/
@Getter
@Setter
public class RegisteredVo {

    /**
     * 编号
     */
    @TableField("u_id")
    private Long uId;

    /**
     * 头像
     */
    @TableField("u_path")
    @ApiModelProperty(value = "头像",required = true)
    private String uPath;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称",required = true)
    @TableField("u_nikname")
    @NotEmpty(message = ValidatedStrMsg.NOT_NULL_MSG)
    private String uNikname;

    /**
     * 学校ID
     */
    @ApiModelProperty(value = "学校ID",required = true)
    @TableField("school_id")
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Integer schoolId;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业",required = true)
    @TableField("u_specialty")
    private String uSpecialty;

    /**
     * 性别
     */
    @TableField("u_sex")
    @ApiModelProperty(value = "性别",required = true)
    @Min(value = 0, message = ValidatedStrMsg.ERROR_MSG)
    @Max(value = 1, message = ValidatedStrMsg.ERROR_MSG)
    private Integer uSex;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号",required = true)
    @TableField("u_phone")
    @Pattern(regexp = "^1[3-9]\\d{9}$",message = ValidatedStrMsg.PHONE_MSG)
    private String uPhone;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间",required = true)
    @TableField("u_start_time")
    @Positive
    private Long uStartTime;

    /**
     * 密码
     **/
    @ApiModelProperty(value = "密码",required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*]{8,20}$" , message = ValidatedStrMsg.PASS_MSG)
    private String pass;


    /**
     * 描述
     */
    @ApiModelProperty(value = "描述",required = true)
    @NotEmpty(message = ValidatedStrMsg.NOT_NULL_MSG)
    @Size(max = 255,message = ValidatedStrMsg.RANGE_MSG)
    private String description;

    /**
     * 兴趣爱好标签
     */
    @ApiModelProperty(value = "兴趣爱好标签",required = true)
    @NotEmpty(message = ValidatedStrMsg.NOT_NULL_MSG)
    private String uLab;

}
