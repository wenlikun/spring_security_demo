package com.benbird.bencenter.exception;

import lombok.Data;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/15
 * 描述: 自定义异常
 */
@Data
public class BenbirdException extends RuntimeException{

    private BenbirdErrorCode benbirdErrorCode;


    public BenbirdException(BenbirdErrorCode benbirdErrorCode){
        super(benbirdErrorCode.getDesc());
        this.benbirdErrorCode = benbirdErrorCode;
    }

    public BenbirdException(BenbirdErrorCode benbirdErrorCode, String errorMessage , Throwable throwable){
        super(errorMessage,throwable);
        this.benbirdErrorCode = benbirdErrorCode;
    }

    public BenbirdException(String message) {
        super(message);
    }

    public BenbirdException(Throwable cause) {
        super(cause);
    }

    public BenbirdException(String message, Throwable cause) {
        super(message, cause);
    }


}
