package com.benbird.bencenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benbird.bencenter.models.DO.SysPermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 系统权限表
 * @author Admin
 */
public interface SysPermissionMapper extends BaseMapper<SysPermissionDO> {

    /**
     * 更新为不可用
     * @param userId        用户ID
     * @param updatedBy     更新人
     * @return              更新条数
     */
    Integer updateUpUnUse(@Param("userId") Integer userId, @Param("updatedBy") String updatedBy);

    /**
     * 新增数据
     * @param userId        用户ID
     * @param updatedBy     更新人
     * @param menuList      菜单集合
     * @return              更新条数
     */
    Integer insertPermission(@Param("userId") Integer userId,
                             @Param("updatedBy") String updatedBy,
                             @Param("menuList") List<Integer> menuList);
}
