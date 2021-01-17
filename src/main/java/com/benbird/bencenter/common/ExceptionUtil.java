package com.benbird.bencenter.common;

import com.benbird.bencenter.exception.BenbirdErrorCode;
import com.benbird.bencenter.exception.BenbirdException;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述:
 */
@Slf4j
public class ExceptionUtil {

    /**
     * 统一异常处理
     *
     * @param err 异常
     * @return 外部响应对象
     */
    public static <T> Result<T> doExceptionService(Throwable err) {

        try {
            if (err instanceof BenbirdException) {
                BenbirdException e = (BenbirdException) err;
                return Result.serviceError(e.getBenbirdErrorCode().getDesc(),null);
            }
            if (err instanceof IllegalArgumentException) {
                return Result.serviceError(BenbirdErrorCode.PARAMETER_VALID_NOT_PASS.getDesc(),null);
            }
        } catch (Exception e) {
            log.error("call ExceptionUtil doExceptionService：" + e);
        }
        return Result.serviceError(BenbirdErrorCode.SYSTEM_INNER_ERROR.getDesc(),null);
    }

}
