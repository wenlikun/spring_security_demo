package com.benbird.bencenter.models.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.benbird.bencenter.models.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import java.util.Date;
import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述: 系统用户表
 * @author Admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@TableName("sys_user")
public class SysUserDO extends BaseDO {

    private String userName;
    private String password;
    private String avatar;
    private String nickName;
    private String userPhone;
    private String userEmail;
    private String gender;
    private String department;
    private Date lastLoinTime;
    @TableField(exist = false)
    private List<SysMenuDO> sysMenuDOList;

}
