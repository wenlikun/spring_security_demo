package com.benbird.bencenter.mapper;

import com.benbird.bencenter.dto.req.PaginationReqDTO;
import com.benbird.bencenter.models.DO.SysMenuDO;
import com.benbird.bencenter.models.DO.SysUserDO;
import com.benbird.bencenter.service.UserInfoService;
import com.benbird.bencenter.util.DateUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testPermission(){
        userInfoService.confirmUserMenu(3,Lists.newArrayList(3,4,5,6,7),"test");
    }

    @Test
    public void queryUser(){
        SysUserDO sysUserDO = new SysUserDO();
        //sysUserDO.setUserName("nick");
        //sysUserDO.setUserPhone("123");
        Integer integer = sysUserMapper.queryCount(sysUserDO);
        System.out.println(integer);
        PaginationReqDTO paginationReqDTO = new PaginationReqDTO();
        paginationReqDTO.setCurrentPage(3);
        paginationReqDTO.setPageCount(1);
        List<SysUserDO> list = sysUserMapper.queryPageList(sysUserDO,
                paginationReqDTO.getStartRow(), paginationReqDTO.getPageCount());
        System.out.println(list);

    }

    @Test
    public void add(){
        for(int i = 0; i < 1000 ; i++){
            SysUserDO sysUserDO = new SysUserDO();
            sysUserDO.setUserName("test"+i);
            sysUserDO.setUserPhone(new BCryptPasswordEncoder().encode("test"+i));
            sysUserDO.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            sysUserDO.setNickName("test"+i);
            sysUserDO.setUserPhone("1111"+i);
            sysUserDO.setUserEmail("test"+i);
            sysUserDO.setGender("M");
            sysUserDO.setDepartment("test");
            sysUserDO.setLastLoginTime(DateUtil.getCurrentDate());
            sysUserDO.setCreatedAt(DateUtil.getCurrentDate());
            sysUserDO.setUpdatedAt(DateUtil.getCurrentDate());
            sysUserDO.setUsableFlag("USABLE");
            sysUserDO.setCreatedBy("TEST");
            sysUserDO.setUpdatedBy("TEST");
            sysUserMapper.insert(sysUserDO);
        }
    }


    @Test
    public void query(){
        List<SysMenuDO> sysMenuMappers = sysMenuMapper.selectUserMenu(3);

        //List<SysMenuDO> sysMenuMappers1 = sysMenuMapper.selectAllMenu();
        List<SysMenuDO> targetList = Lists.newArrayList();
        checkMenu(sysMenuMappers,targetList);
        System.out.println(targetList);
    }

    public static void checkMenu(List<SysMenuDO> sourceList,List<SysMenuDO> targetList){
        if(!CollectionUtils.isEmpty(sourceList)){
            for(SysMenuDO sysMenuDO:sourceList){
                if(!"TRUE".equals(sysMenuDO.getHidden())){
                    SysMenuDO targetSysMenuDO = new SysMenuDO();
                    targetSysMenuDO.setId(sysMenuDO.getId());
                    targetSysMenuDO.setLabel(sysMenuDO.getMenuName());
                    targetList.add(targetSysMenuDO);
                    if(!CollectionUtils.isEmpty(sysMenuDO.getChildren())){
                        List<SysMenuDO> childrenList = Lists.newArrayList();
                        targetSysMenuDO.setChildren(childrenList);
                        checkMenu(sysMenuDO.getChildren(),childrenList);
                    }
                }
            }
        }
    }


}
