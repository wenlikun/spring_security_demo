package com.benbird.bencenter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/15
 * 描述:
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum BenbirdErrorCode {

    /**
     * job配置错误
     */
    JOB_CONFIG_ERROR("JOB_CONFIG_ERROR", "job配置错误"),

    PARAMETER_VALID_NOT_PASS("PARAMETER_VALID_NOT_PASS", "参数校验不通过"),

    PARAMETER_IS_NULL("PARAMETER_IS_NULL", "请求参数为空"),

    /**
     * 亲，系统内部异常，请稍后重试
     */
    SYSTEM_INNER_ERROR("SYSTEM_INNER_ERROR", "亲，系统内部异常，请稍后重试"),

    /**
     * 登录相关的错误码
     */
    USER_INFO_IS_ERROR("USER_INFO_IS_ERROR","用户名或密码不正确"),
    USER_NOT_EXISTS("USER_IS_NOT_EXISTS","用户不存在"),
    MENU_NOT_EXISTS("MENU_NOT_EXISTS","菜单不存在"),
    ACCOUNT_IS_DISABLED("ACCOUNT_IS_DISABLED","账号被禁用"),
    AUTHENTICATION_FAILED("AUTHENTICATION_FAILED","认证失败"),
    QUERY_EMPTY("QUERY_EMPTY", "查询结果为空"),
    DATA_CONVERTER_ERROR("DATA_CONVERTER_ERROR", "数据对象转换异常"),
    TARGET_LIST_IS_NULL("TARGET_LIST_IS_NULL","目标集合为空"),
    NOT_DELETE_LOGIN_USER("NOT_DELETE_LOGIN_USER","不能删除当前登录用户"),
    START_CANT_BEFORE_END_DATE("START_CANT_BEFORE_END_DATE","开始日期不能早于结束日期"),
    CANT_UPDATE_MONTH("CANT_UPDATE_MONTH","不允许修改月份"),

    ;

    /**
     * 异常码
     */
    private String code;

    /**
     * 异常描述
     */
    private String desc;


}
