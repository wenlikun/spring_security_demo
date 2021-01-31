package com.benbird.bencenter.models;

import com.google.gson.Gson;
import lombok.Data;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/27
 * 描述: 元信息
 * @author Admin
 */
@Data
public class Meta {

    private String title;

    private String icon;

    private Boolean affix;

}
