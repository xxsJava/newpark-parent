package com.newpark.sso.entity.vo;

import javax.validation.constraints.*;

import com.baomidou.mybatisplus.annotation.TableField;
import com.newpark.base.vali.ValidatedStrMsg;

import cn.hutool.crypto.digest.MD5;
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
    private String uPath;

    /**
     * 用户昵称
     */
    @TableField("u_nikname")
    @NotEmpty(message = ValidatedStrMsg.NOT_NULL_MSG)
    private String uNikname;

    /**
     * 学校ID
     */
    @TableField("school_id")
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Integer schoolId;

    /**
     * 专业
     */
    @TableField("u_specialty")
    private String uSpecialty;

    /**
     * 性别
     */
    @TableField("u_sex")
    @Min(value = 0, message = ValidatedStrMsg.ERROR_MSG)
    @Max(value = 1, message = ValidatedStrMsg.ERROR_MSG)
    private Integer uSex;

    /**
     * 手机号
     */
    @TableField("u_phone")
    @NotEmpty(message = ValidatedStrMsg.NOT_NULL_MSG)
    @Size(min = 11,max = 11,message = ValidatedStrMsg.ERROR_MSG)
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private String uPhone;

    /**
     * 注册时间
     */
    @TableField("u_start_time")
    private Long uStartTime;

    /**
     * 密码
     **/
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}$",message = ValidatedStrMsg.PASS_MSG)
    private String pass;

    /**
     * 描述
     */
    @NotEmpty(message = ValidatedStrMsg.NOT_NULL_MSG)
    @Size(max = 255,message = ValidatedStrMsg.RANGE_MSG)
    private String description;

    /**
     * 兴趣爱好标签
     */
    @NotEmpty(message = ValidatedStrMsg.NOT_NULL_MSG)
    private String uLab;

    public void setPass(String pass) {
        this.pass = pass;
        if (pass.length() >= 8 && pass.length() <= 16) {
            this.pass = MD5.create().digestHex16(pass.trim());
        }
    }
}
