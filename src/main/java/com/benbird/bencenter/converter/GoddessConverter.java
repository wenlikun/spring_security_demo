package com.benbird.bencenter.converter;

import com.benbird.bencenter.dto.req.GoddessReqDTO;
import com.benbird.bencenter.dto.resp.GoddessRespDTO;
import com.benbird.bencenter.models.DO.GoddessDO;
import com.benbird.bencenter.util.DateUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 项目名: bencenter
 * 创建时间: 2021/2/1
 * 描述: 人间烟火转换层
 *
 * @author wenlikun
 */
@Slf4j
public class GoddessConverter {

    /**
     * DTO转换为DO
     * @param goddessReqDTO DTO
     * @return              DO
     */
    public static GoddessDO converterReqDTOToDO(GoddessReqDTO goddessReqDTO){
        GoddessDO goddessDO = new GoddessDO();
        if(null == goddessReqDTO){
            return goddessDO;
        }
        goddessDO.setMonth(goddessReqDTO.getMonth());
        goddessDO.setStartDate(goddessReqDTO.getStartDate());
        goddessDO.setEndDate(goddessReqDTO.getEndDate());
        goddessDO.setMemo(goddessReqDTO.getMemo());
        goddessDO.setUpdatedBy(goddessReqDTO.getUpdatedBy());
        goddessDO.setUpdatedAt(DateUtil.getCurrentDate());
        goddessDO.setId(goddessReqDTO.getId());
        return goddessDO;
    }

    /**
     * DO转换为DTO
     * @param list      DO集合
     * @return          DTO集合
     */
    public static List<GoddessRespDTO> converterDOToRespDTO(List<GoddessDO> list){
        List<GoddessRespDTO> respDTOList = Lists.newArrayList();
        for(GoddessDO goddessDO : list){
            GoddessRespDTO goddessRespDTO = new GoddessRespDTO();
            BeanUtils.copyProperties(goddessDO,goddessRespDTO);
            respDTOList.add(goddessRespDTO);
        }
        return respDTOList;
    }

}
