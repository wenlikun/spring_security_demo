package com.benbird.bencenter.common;

import java.util.UUID;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 日志工具类
 * @author Admin
 */
public class TraceLogUtil {

    /**
     * 创建一个日志ID
     * @return  日志ID
     */
    public static String createLog(){
        return UUID.randomUUID().toString();
    }

}
