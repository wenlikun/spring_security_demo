package com.benbird.bencenter.controller;

import com.benbird.bencenter.common.Result;
import com.benbird.bencenter.models.DO.SysUserDO;
import com.benbird.bencenter.service.UserInfoService;
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
 * 描述:
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping("/login")
    public Result<Object> login(@RequestBody SysUserDO userInfo){
        String token = userInfoService.login(userInfo.getUserName(), userInfo.getPassword());
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", "Bearer ");
        return Result.success(tokenMap);
    }

}
