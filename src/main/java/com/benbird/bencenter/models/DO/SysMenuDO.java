package com.benbird.bencenter.models.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.benbird.bencenter.models.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/15
 * 描述: 系统菜单表
 * @author Admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenuDO extends BaseDO {

    private String menuName;
    private String menuUrl;
    /**
     * 字段等同于menuUrl，查询使用path字段，增删改使用menuUrl字段
     */
    @TableField(exist = false)
    private String path;
    private String menuType;
    private Integer parentMenuId;
    private String status;
    private String hidden;
    private String meta;
    private List<SysMenuDO> children;

}
