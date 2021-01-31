package com.benbird.bencenter.service;

import com.benbird.bencenter.models.DO.SysUserDO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述:
 */
public interface UserInfoService extends UserDetailsService {

    /**
     * 查找用户信息
     * @param userName 用户名
     * @return  用户信息
     */
    SysUserDO findByUserName(String userName);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return  用户信息
     */
    SysUserDO login(String username,String password);

    /**
     * 查询总记录数
     * @param sysUserDO DO
     * @return  Integer
     */
    Integer queryCount(SysUserDO sysUserDO);

    /**
     * 分页查询结果
     * @param sysUserDO DO
     * @param startRow  开始行
     * @param pageSize  页容量
     * @return List
     */
    List<SysUserDO> queryPageList(SysUserDO sysUserDO, Integer startRow, Integer pageSize);

    /**
     * 根据用户ID查询用户信息
     * @param id ID
     * @return   DO
     */
    SysUserDO queryById(Integer id);

    /**
     * 根据ID更新为不可用状态
     * @param id ID
     * @param updatedBy 更新人
     * @return   Integer
     */
    Integer modifyToUnUseById(Integer id , String updatedBy);

    /**
     * 分配用户菜单权限
     * @param userId        用户ID
     * @param menuList      菜单集合
     * @param updatedBy     更新人
     */
    void confirmUserMenu(Integer userId, List<Integer> menuList,String updatedBy);
}
