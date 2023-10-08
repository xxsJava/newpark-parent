package com.newpark.base.constant;

/**
 * 系统特殊资源固定ID
 * 用户表，角色表，部门表的ID从1001开始，0-1000规划为系统ID，用于特殊业务需求
 *
 * @author jack
 * @date 2023/3/15
 */
public interface SystemId {
    /**
     * 超级管理员ID
     */
    Long SUPER_ADMIN = 1L;

    /**
     * 系统
     */
    Long SYSTEM = 0L;
    String SYSTEM_NAME = "系统";

    /**
     * 树根节点ID
     */
    Long TREE_ROOT = 1L;

    /**
     * 菜单跟节点ID
     */
    Long MENU_ROOT = 0L;

    /**
     * 部门树根节点ID
     */
    String SCHOOL_TREE_ROOT = "0";

    /**
     * 部门ID
     */
    enum User {
        /**
         * 创建人没有，默认为0
         */
        NO_CREATE_ID(0L);

        private Long value;

        User(Long value) {
            this.value = value;
        }

        public Long getValue() {
            return value;
        }
    }

    /**
     * 部门ID
     */
    enum Dept {

        SYSTEM_DEPT(1L, "系统部门");

        private Long value;
        private String code;

        Dept(Long value, String code) {
            this.value = value;
            this.code = code;
        }

        public Long getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }

        public boolean equals(Long value) {
            return this.value.equals(value);
        }
    }
}
