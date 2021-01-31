package com.benbird.bencenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.benbird.bencenter.models.DO.GoddessDO;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/30
 * 描述: 人间烟火服务
 * @author Admin
 */
public interface GoddessService extends IService<GoddessDO> {

    /**
     * 分页查询数据
     * @param goddessDO     查询DO
     * @param pageNo        当前页
     * @param pageSize      页容量
     * @return              分页数据
     */
    Page<GoddessDO> queryPageList(GoddessDO goddessDO , Integer pageNo , Integer pageSize);

    /**
     * 新增DO数据
     * @param goddessDO     DO数据
     * @return              新增结果
     */
    Integer addGoddess(GoddessDO goddessDO);

    /**
     * 根据ID修改DO数据
     * @param goddessDO     DO数据
     * @return              修改结果
     */
    Integer modifyById(GoddessDO goddessDO);

    /**
     * 根据ID查询数据
     * @param id            ID
     * @return              DO
     */
    GoddessDO queryById(Integer id);
}
