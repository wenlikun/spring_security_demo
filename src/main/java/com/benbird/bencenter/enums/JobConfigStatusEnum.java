package com.benbird.bencenter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Job状态枚举类
 * User: LZQ Date: 2015/10/09 ProjectName: verifyCenter Version: 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum JobConfigStatusEnum {

    /**
     * Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除
     */
    NONE("NONE", "删除"),

    /**
     * 正常状态
     */
    NORMAL("NORMAL", "正常"),

    /**
     * 暂停状态
     */
    PAUSED("PAUSED", "暂停"),

    /**
     * 触发器完成，但是任务可能还正在执行中
     */
    COMPLETE("COMPLETE", "运行中"),

    /**
     * 出现错误
     */
    ERROR("ERROR", "错误"),

    /**
     * 线程阻塞状态
     */
    BLOCKED("BLOCKED", "线程阻塞");

    /**
     * 任务执行状态存储集合
     */
    public static Map<String, JobConfigStatusEnum> codeMap = new HashMap<String, JobConfigStatusEnum>();

    /*
     * 静态初始块，类被加载时执行
     */
    static {
        for (JobConfigStatusEnum jobStatusEnum : values()) {
            codeMap.put(jobStatusEnum.getCode(), jobStatusEnum);
        }
    }

    /**
     * key
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    /**
     * 根据编码获取任务执行枚举
     */
    public static JobConfigStatusEnum getEnumsByCode(String code) {
        return codeMap.get(code);
    }

}
