package com.benbird.bencenter.service;

import com.benbird.bencenter.models.DO.SysUserDO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述:
 */
public interface UserInfoService extends UserDetailsService {

    SysUserDO findByUserName(String userName);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

}
