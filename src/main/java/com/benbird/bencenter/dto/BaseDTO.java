package com.benbird.bencenter.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述:  基础DTO对象
 * @author Admin
 */
@Data
public class BaseDTO implements Serializable {

    private Integer id;


    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;


}
