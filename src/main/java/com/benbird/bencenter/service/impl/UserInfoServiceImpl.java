package com.benbird.bencenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benbird.bencenter.exception.BenbirdErrorCode;
import com.benbird.bencenter.exception.BenbirdException;
import com.benbird.bencenter.mapper.SysMenuMapper;
import com.benbird.bencenter.mapper.SysUserMapper;
import com.benbird.bencenter.models.DO.SysUserDO;
import com.benbird.bencenter.query.BaseQuery;
import com.benbird.bencenter.service.UserInfoService;
import com.benbird.bencenter.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述:
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 查找用户信息
     * @param userName 用户名
     * @return  用户信息
     */
    @Override
    public SysUserDO findByUserName(String userName) {
        log.info("开始查询用户信息{}",userName);
        QueryWrapper<SysUserDO> query = BaseQuery.query();
        query.eq("USER_NAME",userName);
        SysUserDO sysUserDO = sysUserMapper.selectOne(query);
        if(null != sysUserDO){
            sysUserDO.setSysMenuDOList(sysMenuMapper.selectUserMenu(sysUserDO.getId()));
            return sysUserDO;
        }
        return null;
    }

    /**
     * springSecurity的认证方法
     * @param username  用户名
     * @return  UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO userInfo = findByUserName(username);
        if(null != userInfo) {
            List<GrantedAuthority> authorities = userInfo.getSysMenuDOList().stream().
                    map(sysMenuDO -> new SimpleGrantedAuthority(sysMenuDO.getMenuUrl())).
                    collect(Collectors.toList());
            return new User(userInfo.getUserName(),userInfo.getPassword(),authorities);
        }
        return null;
    }

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return  用户信息
     */
    @Override
    public SysUserDO login(String username, String password) {
        SysUserDO sysUserDO;
        UserDetails userDetails = loadUserByUsername(username);
        checkUserInfo(userDetails,password);
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = JwtTokenUtil.generateToken(userDetails);
            sysUserDO = findByUserName(username);
            sysUserDO.setToken(token);
        } catch (AuthenticationException e) {
            log.info("登录异常:{}"+ e.getMessage());
            throw new BenbirdException(BenbirdErrorCode.AUTHENTICATION_FAILED);
        }
        return sysUserDO;
    }

    /**
     * 检查用户信息
     * @param userDetails 用户明细
     * @param password    登录密码
     */
    private void checkUserInfo(UserDetails userDetails,String password){
        if(null == userDetails || !new BCryptPasswordEncoder().matches(password,userDetails.getPassword())){
            throw new BenbirdException(BenbirdErrorCode.USER_INFO_IS_ERROR);
        }
        if(!userDetails.isEnabled()){
            throw new BenbirdException(BenbirdErrorCode.ACCOUNT_IS_DISABLED);
        }
    }

}
