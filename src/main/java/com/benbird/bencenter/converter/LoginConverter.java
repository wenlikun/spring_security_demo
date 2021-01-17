package com.benbird.bencenter.converter;

import com.benbird.bencenter.common.BenBirdConstant;
import com.benbird.bencenter.dto.resp.LoginRespDTO;
import com.benbird.bencenter.models.DO.SysUserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 数据转换类
 * @author Admin
 */
@Slf4j
public class LoginConverter {

    /**
     * 参数转换
     * @param sysUserDO 用户信息
     * @return          登录成功返回信息
     */
    public static LoginRespDTO converter(SysUserDO sysUserDO){
        LoginRespDTO loginRespDTO = new LoginRespDTO();
        BeanUtils.copyProperties(sysUserDO,loginRespDTO);
        loginRespDTO.setToken(BenBirdConstant.BEARER+sysUserDO.getToken());
        return loginRespDTO;
    }

}
