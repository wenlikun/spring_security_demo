package com.benbird.bencenter.controller;

import com.benbird.bencenter.common.BenBirdConstant;
import com.benbird.bencenter.common.ExceptionUtil;
import com.benbird.bencenter.common.ParamValidate;
import com.benbird.bencenter.common.Result;
import com.benbird.bencenter.converter.LoginConverter;
import com.benbird.bencenter.converter.UserInfoConverter;
import com.benbird.bencenter.dto.req.LoginReqDTO;
import com.benbird.bencenter.dto.req.UserInfoReqDTO;
import com.benbird.bencenter.dto.req.UserMenuReqDTO;
import com.benbird.bencenter.dto.resp.LoginRespDTO;
import com.benbird.bencenter.dto.resp.MenuTreeRespDTO;
import com.benbird.bencenter.dto.resp.PageRespDTO;
import com.benbird.bencenter.dto.resp.UserInfoRspDTO;
import com.benbird.bencenter.exception.BenbirdErrorCode;
import com.benbird.bencenter.exception.BenbirdException;
import com.benbird.bencenter.models.DO.SysMenuDO;
import com.benbird.bencenter.models.DO.SysUserDO;
import com.benbird.bencenter.service.MenuService;
import com.benbird.bencenter.service.UserInfoService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述: 用户操作
 * @author Admin
 */
@Slf4j
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private MenuService menuService;

    /**
     * 登录
     * @param loginReqDTO 登录请求DTO
     * @return  登录返回结果
     */
    @RequestMapping("/user/login")
    public Result<LoginRespDTO> login(@RequestBody LoginReqDTO loginReqDTO){
        log.info("登录请求服务开始:{}",loginReqDTO);
        Result<LoginRespDTO> result;
        try {
            SysUserDO sysUserDO = userInfoService.login(loginReqDTO.getUserName(), loginReqDTO.getPassword());
            if (sysUserDO == null) {
                throw new BenbirdException(BenbirdErrorCode.USER_INFO_IS_ERROR);
            }
            LoginRespDTO loginRespDTO = LoginConverter.converter(sysUserDO);
            result = Result.success(loginRespDTO);
        }catch (Exception e){
            log.error("登录请求服务出现异常:", e);
            result = ExceptionUtil.doExceptionService(e);
        }
        log.info("登录服务处理结束,响应结果为:{}",result);
        return result;
    }

    /**
     * 登出
     * @param loginReqDTO 登出请求DTO
     * @return  登出处理结果
     */
    @RequestMapping("/user/logOut")
    public Result<Boolean> logOut(@RequestBody LoginReqDTO loginReqDTO){
        Result<Boolean> result = new Result<>();
        log.info("退出登录服务:{}",loginReqDTO);
        result.setData(true);
        return result;
    }

    /**
     * 分页查询用户列表
     * @param userInfoReqDTO 请求DTO
     * @return  分页结果
     */
    @RequestMapping("/user/queryUserList")
    public Result<PageRespDTO<UserInfoRspDTO>> queryUserList(@RequestBody UserInfoReqDTO userInfoReqDTO){
        Result<PageRespDTO<UserInfoRspDTO>> result;
        log.info("查询用户列表服务开始,请求参数:{}",userInfoReqDTO);
        try {
            SysUserDO sysUserDO = UserInfoConverter.converterDTOToDO(userInfoReqDTO);
            Integer count = userInfoService.queryCount(sysUserDO);
            ParamValidate.validatePageCount(count);
            List<SysUserDO> list = userInfoService.queryPageList(
                    sysUserDO, userInfoReqDTO.getStartRow(), userInfoReqDTO.getPageCount());
            List<UserInfoRspDTO> respDTOList = UserInfoConverter.converterDOToRespDTO(list);
            result = Result.success(new PageRespDTO<>(count,respDTOList));
        }catch (Exception e){
            log.error("查询用户列表服务异常:",e);
            result = ExceptionUtil.doExceptionService(e);
        }
        log.info("查询用户列表服务结束,响应结果:{}",result);
        return result;
    }

    /**
     * 删除用户信息
     * @param id 数据库主键
     * @param userName 用户名
     * @return 删除结果
     */
    @RequestMapping(value = "/user/delete/{id}",method = RequestMethod.DELETE)
    public Result<Boolean> deleteUser(@PathVariable Integer id,@RequestParam String userName){
        log.info("删除用户服务开始,请求用户ID{}",id);
        Result<Boolean> result;
        try {
            ParamValidate.validateId(id);
            ParamValidate.validateStringNull(userName);
            SysUserDO sysUserDO = userInfoService.queryById(id);
            if(userName.equals(sysUserDO.getUserName())){
                throw new BenbirdException(BenbirdErrorCode.NOT_DELETE_LOGIN_USER);
            }
            Integer count = userInfoService.modifyToUnUseById(id, userName);
            result = Result.success(count > 0);
        }catch (Exception e){
            log.error("删除用户服务结束异常",e);
            result = ExceptionUtil.doExceptionService(e);
        }
        log.info("删除用户服务结束,响应结果为{}",result);
        return result;
    }

    /**
     * 查询菜单权限服务
     * @param id 用户ID
     * @return   菜单权限列表
     */
    @RequestMapping(value = "/user/menuTree" , method = RequestMethod.GET)
    public Result<List<MenuTreeRespDTO>> queryMenuTree(@RequestParam Integer id){
        log.info("查询菜单权限服务开始,ID:{}",id);
        Result<List<MenuTreeRespDTO>> result;
        try {
            ParamValidate.validateId(id);
            List<SysMenuDO> sysMenuDOList;
            if(0 == id){
               sysMenuDOList = menuService.selectAllMenu();
            }else {
                sysMenuDOList = menuService.selectUserMenu(id);
            }
            List<MenuTreeRespDTO> list = Lists.newArrayList();
            filterMenu(sysMenuDOList,list);
            result = Result.success(list);
        }catch (Exception e){
            log.error("查询菜单权限服务异常",e);
            result = ExceptionUtil.doExceptionService(e);
        }
        log.info("查询菜单权限服务结束,响应结果:{}",result);
        return result;
    }

    /**
     * 确认用户菜单权限服务
     * @param userMenuReqDTO 用户请求DTO
     * @return  Result
     */
    @RequestMapping(value = "/user/confirmMenu",method = RequestMethod.POST)
    public Result<Boolean> confirmUserMenu(@RequestBody UserMenuReqDTO userMenuReqDTO){
        log.info("确认用户菜单权限服务开始,请求参数:{}",userMenuReqDTO);
        Result<Boolean> result;
        try {
            ParamValidate.validateParams(userMenuReqDTO);
            // 校验用户和菜单信息是否存在
            userInfoService.queryById(userMenuReqDTO.getId());
            userMenuReqDTO.getMenuList().forEach(id -> menuService.queryById(id));
            // 删除原有数据，新增新数据
            userInfoService.confirmUserMenu(userMenuReqDTO.getId(),userMenuReqDTO.getMenuList(),
                    userMenuReqDTO.getUpdatedBy());
            result = Result.success(Boolean.TRUE);
        }catch (Exception e){
            log.error("确认用户菜单权限服务异常",e);
            result = ExceptionUtil.doExceptionService(e);
        }
        log.info("确认用户菜单权限服务结束,响应结果:{}",result);
        return result;
    }


    /**
     * 过滤菜单路径
     * @param sourceList 数据来源集合
     * @param targetList 目标集合
     */
    public void filterMenu(List<SysMenuDO> sourceList, List<MenuTreeRespDTO> targetList){
        if(!CollectionUtils.isEmpty(sourceList)){
            for(SysMenuDO sysMenuDO:sourceList){
                if(BenBirdConstant.TRUE.equals(sysMenuDO.getHidden())){
                    continue;
                }
                MenuTreeRespDTO menuTreeRespDTO = new MenuTreeRespDTO();
                menuTreeRespDTO.setId(sysMenuDO.getId());
                menuTreeRespDTO.setLabel(sysMenuDO.getMenuName());
                targetList.add(menuTreeRespDTO);
                if(CollectionUtils.isEmpty(sysMenuDO.getChildren())){
                    continue;
                }
                List<MenuTreeRespDTO> childrenList = Lists.newArrayList();
                menuTreeRespDTO.setChildren(childrenList);
                filterMenu(sysMenuDO.getChildren(),childrenList);
            }
        }
    }

}
