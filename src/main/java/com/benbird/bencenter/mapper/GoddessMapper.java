package com.benbird.bencenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benbird.bencenter.models.DO.GoddessDO;
import org.apache.ibatis.annotations.Param;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/30
 * 描述: 人间烟火Mapper
 * @author Admin
 */
public interface GoddessMapper extends BaseMapper<GoddessDO> {

    /**
     * 根据ID删除数据
     * @param id            主键
     * @param updatedBy     更新人
     * @return              Integer
     */
    Integer modifyToUnUseById(Integer id , String updatedBy);


    /**
     * 根据月份查询记录是否存在
     * @param month     月份
     * @return          Integer
     */
    Integer queryByMonth(@Param("month") String month);
}
