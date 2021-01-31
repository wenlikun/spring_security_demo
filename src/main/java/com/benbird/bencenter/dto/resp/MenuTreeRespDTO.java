package com.benbird.bencenter.dto.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/29
 * 描述: 菜单树响应DTO
 * @author Admin
 */
@Data
public class MenuTreeRespDTO implements Serializable {

    private Integer id;
    private String label;
    private List<MenuTreeRespDTO> children;


}
