package com.benbird.bencenter.models.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.benbird.bencenter.models.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 系统权限表
 * @author Admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@TableName("sys_permission")
public class SysPermissionDO extends BaseDO {

    private Integer userId;

    private Integer menuId;


}
