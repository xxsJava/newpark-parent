package com.newpark.sso.entity.vo;/**
 * @Author xxs18
 * @Description
 * @Date 2023/10/20 10:16
 **/

import com.baomidou.mybatisplus.annotation.TableField;
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
    private String uNikname;

    /**
     * 学校ID
     */
    @TableField("school_id")
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
    private String uSex;

    /**
     * 手机号
     */
    @TableField("u_phone")
    private String uPhone;

    /**
     * 注册时间
     */
    @TableField("u_start_time")
    private Long uStartTime;

    /**
     * 密码
     **/
    private String pass;



}
