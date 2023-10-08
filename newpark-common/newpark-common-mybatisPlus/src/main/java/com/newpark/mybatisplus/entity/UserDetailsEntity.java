package com.newpark.mybatisplus.entity;

import lombok.Data;

import java.util.Set;

/**
 *
 * @author : jack
 * @date :  2023/3/14
 */
@Data
public class UserDetailsEntity {

    /**
     * 项目编码
     */
    private String projectCode;

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
     * 角色id
     */
    private Long roleId;

    /**
     * 归属组织
     */
    private Long deptId;

    /**
     * 包含下级归属组织
     */
    private Set<String> deptIds;

    /**
     * 归属组织
     */
    private String deptName;

    /**
     * 管辖组织
     */
    private Set<String> manageDepts;

    /**
     * 管辖组织用户
     */
    private Set<String> manageDeptsUsers;

    /**
     * 归属组织用户
     */
    private Set<String>  deptUsers;


    /**
     * 操作权限编码
     */
    private Set<String> permSet;

    /**
     * 组织机构全路径
     */
    private String deptPath;
}
