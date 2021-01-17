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
    ACCOUNT_IS_DISABLED("ACCOUNT_IS_DISABLED","账号被禁用"),
    AUTHENTICATION_FAILED("AUTHENTICATION_FAILED","认证失败"),


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
