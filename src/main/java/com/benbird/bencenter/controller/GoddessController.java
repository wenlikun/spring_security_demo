package com.benbird.bencenter.controller;

import com.benbird.bencenter.common.Result;
import com.benbird.bencenter.dto.req.GoddessReqDTO;
import com.benbird.bencenter.dto.resp.GoddessRespDTO;
import com.benbird.bencenter.dto.resp.PageRespDTO;
import com.benbird.bencenter.service.GoddessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/31
 * 描述: 人间烟火控制层
 * @author Admin
 */
@Slf4j
@RestController
public class GoddessController {

    @Autowired
    private GoddessService goddessService;


    /**
     * 分页查询用户列表
     * @param goddessReqDTO 请求DTO
     * @return  分页结果
     */
    @RequestMapping(value = "/goddess/queryGoddessList",method = RequestMethod.GET)
    public Result<PageRespDTO<GoddessRespDTO>> queryGoddessList(@RequestBody GoddessReqDTO goddessReqDTO){


        return null;
    }



}
