package com.benbird.bencenter.service.impl;

import com.benbird.bencenter.common.ParamValidate;
import com.benbird.bencenter.mapper.SysMenuMapper;
import com.benbird.bencenter.models.DO.SysMenuDO;
import com.benbird.bencenter.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/29
 * 描述: 菜单服务
 * @author Admin
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 查询个人拥有的权限
     * @param userId  用户ID
     * @return  List
     */
    @Override
    public List<SysMenuDO> selectUserMenu(Integer userId) {
        return sysMenuMapper.selectUserMenu(userId);
    }

    /**
     * 查询所有的权限
     * @return List
     */
    @Override
    public List<SysMenuDO> selectAllMenu() {
        return sysMenuMapper.selectAllMenu();
    }

    /**
     * 根据ID查询DO信息
     * @param id ID
     * @return   DO
     */
    @Override
    public SysMenuDO queryById(Integer id) {
        SysMenuDO sysMenuDO = sysMenuMapper.queryById(id);
        log.info("根据ID查询结果为:{},{}",id,sysMenuDO);
        ParamValidate.validateUsable(sysMenuDO);
        return sysMenuDO;
    }
}
