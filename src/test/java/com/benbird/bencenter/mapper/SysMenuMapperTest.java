package com.benbird.bencenter.mapper;

import com.benbird.bencenter.models.DO.SysMenuDO;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述:
 */
@SpringBootTest
public class SysMenuMapperTest {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Test
    public void query(){
        List<SysMenuDO> sysMenuMappers = sysMenuMapper.selectUserMenu(2);
        System.out.println(sysMenuMappers);
        List<SysMenuDO> sysMenuMappers1 = sysMenuMapper.selectAllMenu();
        System.out.println(sysMenuMappers1);
    }

}
