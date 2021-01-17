package com.benbird.bencenter.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础数据模型
 *
 */
@Getter
@Setter
@ToString
public class BaseDO implements Serializable {

    /**
     * 数据库主键
     */
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

    /**
     * 删除标识 可用：USABLE 不可用：UN_USABLE
     */
    private String usableFlag;
}
