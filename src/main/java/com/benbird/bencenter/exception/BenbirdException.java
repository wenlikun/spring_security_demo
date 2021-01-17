package com.benbird.bencenter.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/15
 * 描述: 自定义异常
 * @author Admin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BenbirdException extends RuntimeException{

    private BenbirdErrorCode benbirdErrorCode;

    /**
     * 额外补充的信息
     */
    private String extraMsg;


    public BenbirdException(BenbirdErrorCode benbirdErrorCode){
        super();
        this.benbirdErrorCode = benbirdErrorCode;
    }

    public BenbirdException(BenbirdErrorCode benbirdErrorCode, String errorMessage ){
        super();
        this.benbirdErrorCode = benbirdErrorCode;
        this.extraMsg = errorMessage;
    }


    public BenbirdException(BenbirdErrorCode benbirdErrorCode, String errorMessage , Throwable throwable){
        super(throwable);
        this.benbirdErrorCode = benbirdErrorCode;
        this.extraMsg = errorMessage;
    }


}
