package com.benbird.bencenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benbird.bencenter.models.DO.SysUserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 系统用户表
 * @author Admin
 */
public interface SysUserMapper extends BaseMapper<SysUserDO> {


    /**
     * 查询总记录数
     * @param sysUserDO DO
     * @return  Integer
     */
    Integer queryCount(@Param("sysUserDO") SysUserDO sysUserDO);

    /**
     * 分页查询结果
     * @param sysUserDO DO
     * @param startRow  开始行
     * @param pageSize  页容量
     * @return List
     */
    List<SysUserDO> queryPageList(@Param("sysUserDO")SysUserDO sysUserDO,
                                  @Param("startRow") Integer startRow,
                                  @Param("pageSize") Integer pageSize);

    /**
     * 根据ID更新为不可用状态
     * @param id ID
     * @param updatedBy 更新人
     * @return   Integer
     */
    Integer modifyToUnUseById(@Param("id") Integer id,@Param("updatedBy") String updatedBy);

}
