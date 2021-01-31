package com.benbird.bencenter.dto.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/29
 * 描述:  用户菜单请求DTO
 * @author Admin
 */
@Data
public class UserMenuReqDTO implements Serializable {

    @NotNull(message = "用户ID不能为空")
    @Min(value = 1,message = "用户ID不正确")
    private Integer id;

    @NotNull(message = "菜单列表不能为空")
    private List<Integer> menuList;

    @NotNull(message = "操作人不能为空")
    private String updatedBy;
}
