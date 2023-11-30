package com.newpark.base.enums;

import cn.hutool.core.text.UnicodeUtil;
import lombok.Getter;

/**
 * 业务状态码
 *
 * @author jack
 * @date 2023/3/14
 */
@Getter
public enum ResponseCodeEnum {

    /**
     *  重构：1。添加公共枚举；2。重构名称
     */
    SUCCESS(200, "成功"),

    CREATED(201,"新建或修改数据成功"),

    ACCEPTED(202,"一个请求已经进入后台排队"),

    NO_CONTENT(204,"删除数据成功"),

    INVALID_REQUEST(400,"用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的"),

    UNAUTHORIZED(401,"令牌失效"),

    FORBIDDEN(403,"用户得到授权（与401错误相对），但是访问是被禁止的"),

    NOT_FOUND(404,"发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的"),

    NOT_ACCEPTABLE(406,"用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),

    GONE(410,"用户请求的资源被永久删除，且不会再得到的"),

    UNPROCESABLE_ENTITY(422,"当创建一个对象时，发生一个验证错误"),

    USR_NOT_FOUND(490,"404 not found"),

    FAILURE(500, "服务器发生错误"),

    /**
     *
     */
    SYSTEM_EXCEPTION(503, "系统发生了未知错误，请稍候重试"),

    /**
     *
     */
    EXTERNAL_API_ERROR(530,"外部系统异常"),

    NO_EXIT(1, "查询数据不存在"),
    PARAMETER_ERROR(1001, "提交参数不符合规范"),
    USER_TOKEN_PARAMETER_ERROR(1002, "Token参数错误"),
    LOGIN_FAILURE(1110, "登录失败"),
    LOGIN_EXCESSIVE_ATTEMPTS(1111, "登录失败次数太多，请30分钟后再试"),
    LOGIN_VERIFY_CODE_ERROR(1112, "验证码错误"),
    LOGIN_PASSWORD_ERROR(1113, "用户密码错误"),
    LOGIN_USER_NO_EXIST(1114, "用户不存在"),
    LOGIN_USER_LOCK(1115, "此用户已被禁用"),
    LOGIN_ACCOUNT_NO_EXIST(1116, "企业账户不存在"),
    LOGIN_ACCOUNT_LOCK(1117, "企业账户被禁用"),
    USER_UNAUTHORIZED(1118, "用户无权限进行此操作"),
    USER_NO_PRIMARY_DEPT(1119, "当前用户未设置归属部门,请设置归属部门后再进行操作"),
    DEPT_NO_MATCH_AREA(1120, "当前用户归属部门未绑定区域，请绑定区域后再进行操作"),
    TOKEN_OVERDUE(1130, "登录过期，请重新登录"),
    TOKEN_OUT(1131, "登录失效，帐号在其他地方登录"),
    TOKEN_FAULT(1132, "未登录，无权限访问"),
    TINET_NO_LOGIN(1200, "用户未登录天润"),
    CNO_NO_BIND(1201, "坐席号未绑定野马用户"),
    DATABASE_DUPLICATEKEY(2001, "数据库中已存在该记录"),
    DATABASE_SAVE_FAILURE(2002, "添加失败"),
    DATABASE_UPDATE_FAILURE(2003, "修改失败"),
    DATABASE_DELETE_FAILURE(2004, "删除失败"),
    DATABASE_SELECT_FAILURE(2005, "资源不存在"),
    DATABASE_NOT_CHANGE(2006, "未作任何修改"),
    DATABASE_UPDATE_ROOT(2101, "根节点，不允许修改"),
    DATABASE_DELETE_ROOT(2102, "根节点，不允许删除"),
    DATABASE_UPDATE_CHILD(2103, "存在子节点，不允许修改"),
    DATABASE_DELETE_CHILD(2104, "存在子节点，不允许删除"),
    LOGIN_VERIFY_CODE_SUCC(2105, "验证码已发送,请注意查收"),
    LOGIN_VERIFY_CODE_SUCC1(2105, "请勿重复发送");

    private final int code;
    private final String msg;

    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
