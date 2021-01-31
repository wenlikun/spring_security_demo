package com.benbird.bencenter.converter;

import com.benbird.bencenter.dto.req.UserInfoReqDTO;
import com.benbird.bencenter.dto.resp.UserInfoRspDTO;
import com.benbird.bencenter.models.DO.SysUserDO;
import com.benbird.bencenter.util.DateUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/28
 * 描述: 用户信息参数转换类
 * @author Admin
 */
@Slf4j
public class UserInfoConverter {

    /**
     * DTO转换为DO
     * @param userInfoReqDTO DTO
     * @return DO
     */
    public static SysUserDO converterDTOToDO(UserInfoReqDTO userInfoReqDTO){
        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setUserName(userInfoReqDTO.getUserName());
        sysUserDO.setUserPhone(userInfoReqDTO.getUserPhone());
        return sysUserDO;
    }

    /**
     * DO转换为DTO
     * @param list  DO集合
     * @return      DTO集合
     */
    public static List<UserInfoRspDTO> converterDOToRespDTO(List<SysUserDO> list){
        List<UserInfoRspDTO> respDTOList = Lists.newArrayList();
        for(SysUserDO sysUserDO:list){
            UserInfoRspDTO userInfoRspDTO = new UserInfoRspDTO();
            BeanUtils.copyProperties(sysUserDO,userInfoRspDTO);
            userInfoRspDTO.setLastLoginTime(DateUtil.format(sysUserDO.getLastLoginTime(),DateUtil.STANDARD_PATTERN));
            userInfoRspDTO.setCreatedAt(DateUtil.format(sysUserDO.getCreatedAt(),DateUtil.STANDARD_PATTERN));
            userInfoRspDTO.setUpdatedAt(DateUtil.format(sysUserDO.getUpdatedAt(),DateUtil.STANDARD_PATTERN));
            respDTOList.add(userInfoRspDTO);
        }
        return respDTOList;
    }

}
