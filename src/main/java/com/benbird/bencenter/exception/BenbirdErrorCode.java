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
