package com.benbird.bencenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benbird.bencenter.common.ExceptionUtil;
import com.benbird.bencenter.common.ParamValidate;
import com.benbird.bencenter.common.Result;
import com.benbird.bencenter.converter.GoddessConverter;
import com.benbird.bencenter.dto.req.GoddessReqDTO;
import com.benbird.bencenter.dto.resp.GoddessRespDTO;
import com.benbird.bencenter.dto.resp.PageRespDTO;
import com.benbird.bencenter.exception.BenbirdErrorCode;
import com.benbird.bencenter.exception.BenbirdException;
import com.benbird.bencenter.models.DO.GoddessDO;
import com.benbird.bencenter.service.GoddessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 分页查询人间烟火列表
     * @param goddessReqDTO 请求DTO
     * @return  分页结果
     */
    @PostMapping(value = "/goddess/queryGoddessList")
    public Result<PageRespDTO<GoddessRespDTO>> queryGoddessList(@RequestBody GoddessReqDTO goddessReqDTO){
        log.info("分页查询人间烟火列表服务开始,请求参数:{}",goddessReqDTO);
        Result<PageRespDTO<GoddessRespDTO>> result;
        try{
            GoddessDO goddessDO = GoddessConverter.converterReqDTOToDO(goddessReqDTO);
            Page<GoddessDO> page = goddessService.queryPageList(goddessDO, goddessReqDTO.getCurrentPage(),
                    goddessReqDTO.getPageCount());
            ParamValidate.validatePageCount(page);
            List<GoddessRespDTO> list = GoddessConverter.converterDOToRespDTO(page.getRecords());
            PageRespDTO<GoddessRespDTO> pageRespDTO = new PageRespDTO<>();
            pageRespDTO.setResults(list);
            pageRespDTO.setTotalSize((int) page.getTotal());
            result = Result.success(pageRespDTO);
        }catch (Exception e){
            log.error("分页查询人间烟火列表服务异常",e);
            result = ExceptionUtil.doExceptionService(e);
        }
        log.info("分页查询人间烟火列表服务结束,相应结果:{}",result);
        return result;
    }

    /**
     * 新增人间烟火
     * @param goddessReqDTO 请求DTO
     * @return              新增结果
     */
    @PostMapping("/goddess/addGoddess")
    public Result<Boolean> addGoddess(@RequestBody GoddessReqDTO goddessReqDTO){
        log.info("新增人间烟火,请求参数:{}",goddessReqDTO);
        Result<Boolean> result;
        try{
            ParamValidate.validateParams(goddessReqDTO);
            ParamValidate.validateDate(goddessReqDTO.getStartDate(),goddessReqDTO.getEndDate());
            // 检查唯一索引
            Integer count = goddessService.queryByMonth(goddessReqDTO.getMonth());
            if(count > 0){
                throw new BenbirdException(BenbirdErrorCode.DATA_IS_ALREADY_EXISTS);
            }
            GoddessDO goddessDO = GoddessConverter.converterReqDTOToDO(goddessReqDTO);
            count = goddessService.addGoddess(goddessDO);
            result = Result.success(count > 0);
        }catch (Exception e){
            log.info("新增人间烟火,服务异常:",e);
            result = ExceptionUtil.doExceptionService(e);
        }
        log.info("新增人间烟火,响应结果:{}",result);
        return result;
    }

    /**
     * 修改人间烟火
     * @param goddessReqDTO 请求DTO
     * @return              修改结果
     */
    @PutMapping("/goddess/modifyGoddess")
    public Result<Boolean> modifyGoddess(@RequestBody GoddessReqDTO goddessReqDTO){
        log.info("修改人间烟火,请求参数:{}",goddessReqDTO);
        Result<Boolean> result;
        try{
            ParamValidate.validateParams(goddessReqDTO);
            ParamValidate.validateId(goddessReqDTO.getId());
            ParamValidate.validateDate(goddessReqDTO.getStartDate(),goddessReqDTO.getEndDate());
            GoddessDO goddessDO = goddessService.queryById(goddessReqDTO.getId());
            // 月份是数据库唯一索引，不支持修改
            if(!goddessDO.getMonth().equals(goddessReqDTO.getMonth())){
                throw new BenbirdException(BenbirdErrorCode.CANT_UPDATE_MONTH);
            }
            goddessDO = GoddessConverter.converterReqDTOToDO(goddessReqDTO);
            Integer count = goddessService.modifyById(goddessDO);
            result = Result.success(count > 0);
        }catch (Exception e){
            log.info("修改人间烟火,服务异常:",e);
            result = ExceptionUtil.doExceptionService(e);
        }
        log.info("修改人间烟火,响应结果:{}",result);
        return result;
    }

    /**
     * 根据主键删除人间烟火
     * @param id    主键
     * @return      删除结果
     */
    @DeleteMapping("/goddess/deleteGoddess/{id}")
    public Result<Boolean> deleteGoddess(@PathVariable Integer id , @RequestParam("updatedBy") String updatedBy){
        log.info("根据主键删除人间烟火服务开始,ID:{},updatedBy:{}",id,updatedBy);
        Result<Boolean> result;
        try{
            ParamValidate.validateId(id);
            goddessService.queryById(id);
            Integer count = goddessService.modifyToUnUseById(id, updatedBy);
            result = Result.success(count > 0);
        }catch (Exception e){
            log.error("根据主键删除人间烟火服务异常:",e);
            result = ExceptionUtil.doExceptionService(e);
        }
        log.info("根据主键删除人间烟火服务结束,响应结果:{}",result);
        return result;
    }

}
