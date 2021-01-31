package com.benbird.bencenter.dto.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/28
 * 描述:
 * @author Admin
 */
@Data
@ToString(callSuper = true)
public class UserInfoRspDTO implements Serializable {

    private Integer id;
    private String userName;
    private String avatar;
    private String nickName;
    private String userPhone;
    private String userEmail;
    private String gender;
    private String department;
    private String lastLoginTime;
    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private String updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;


}
