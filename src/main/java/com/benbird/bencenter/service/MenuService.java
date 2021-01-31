package com.benbird.bencenter.service;

import com.benbird.bencenter.models.DO.SysMenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/29
 * 描述: 菜单服务
 * @author Admin
 */
public interface MenuService {

    /**
     * 查询个人拥有的权限
     * @param userId  用户ID
     * @return  List
     */
    List<SysMenuDO> selectUserMenu(@Param("userId") Integer userId);

    /**
     * 查询所有的权限
     * @return List
     */
    List<SysMenuDO> selectAllMenu();

    /**
     * 根据ID查询DO信息
     * @param id ID
     * @return   DO
     */
    SysMenuDO queryById(Integer id);

}
