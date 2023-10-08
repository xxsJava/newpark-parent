package com.newpark.base.model.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author : jack
 * @date :  2023/3/13
 */
@Data
public class UserDetailEntity {

    /**
     * 归属公司
     */
    private Long parentId;

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 是否是管理类角色
     */
    private Boolean isManage;

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 操作权限编码
     */
    private Set<String> permSet;

}
