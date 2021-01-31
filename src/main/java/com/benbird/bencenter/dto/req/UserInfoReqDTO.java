package com.benbird.bencenter.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/28
 * 描述: 用户信息请求DTO
 * @author Admin
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserInfoReqDTO extends PaginationReqDTO{

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String userPhone;

}
