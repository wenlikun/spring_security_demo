package com.benbird.bencenter.controller;

import com.benbird.bencenter.common.ExceptionUtil;
import com.benbird.bencenter.common.Result;
import com.benbird.bencenter.converter.LoginConverter;
import com.benbird.bencenter.dto.req.LoginReqDTO;
import com.benbird.bencenter.dto.resp.LoginRespDTO;
import com.benbird.bencenter.exception.BenbirdErrorCode;
import com.benbird.bencenter.exception.BenbirdException;
import com.benbird.bencenter.models.DO.SysUserDO;
import com.benbird.bencenter.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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


    @RequestMapping("/login")
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

}
