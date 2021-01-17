package com.benbird.bencenter.controller;

import com.benbird.bencenter.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述:
 */
@RestController
public class BenController {

    @RequestMapping("/ben/findAll")
    public Result<Object> findAll(){
        return Result.success("查询成功");
    }


    @RequestMapping("/dashboard")
    public Result<Object> dashboard(){
        return Result.success("查询首页成功");
    }


    @RequestMapping("/dashboard/delete")
    public Result<Object> dashboardDelete(){
        return Result.success("删除首页成功");
    }
}
