package com.benbird.bencenter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 *  定时任务类型
 * </p>
 *
 * User: weiyuanliang Date: 2018-8-6 ProductName: verifycenter Version: 1.0
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum JobTypeEnum {

                         /**
                          * 定时任务类型
                          */
    SIMPLE("SIMPLE", "普通任务"),

    DATA_FLOW("DATA_FLOW", "数据流任务"),

    SCRIPT("SCRIPT", "脚本任务"),;

    /**
     * Key
     */
    private String code;

    /**
     * 描述
     */
    private String desc;


}
