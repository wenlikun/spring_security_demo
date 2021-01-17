package com.benbird.bencenter.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.benbird.bencenter.dto.BaseDTO;
import com.benbird.bencenter.models.DO.SysMenuDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 登录成功返回信息
 * @author Admin
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoginRespDTO extends BaseDTO {

    private String userName;
    private String avatar;
    private String nickName;
    private String userPhone;
    private String userEmail;
    private String gender;
    private String department;
    private Date lastLoginTime;
    private List<SysMenuDO> sysMenuDOList;
    private String token;

}
