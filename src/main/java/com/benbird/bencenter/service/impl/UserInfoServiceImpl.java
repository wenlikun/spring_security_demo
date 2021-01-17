package com.benbird.bencenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public SysUserDO findByUserName(String userName) {
        log.info("开始查询用户信息{}",userName);
        QueryWrapper<SysUserDO> query = BaseQuery.query();
        SysUserDO sysUserDO = sysUserMapper.selectOne(query);
        if(null != sysUserDO){
            sysUserDO.setSysMenuDOList(sysMenuMapper.selectUserMenu(sysUserDO.getId()));
            return sysUserDO;
        }
        return null;
    }

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

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!new BCryptPasswordEncoder().matches(password,userDetails.getPassword())){
                throw new RuntimeException("密码不正确");
            }
            if(!userDetails.isEnabled()){
                throw new RuntimeException("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = JwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.info("登录异常:{}"+ e.getMessage());
        }
        return token;
    }
}
