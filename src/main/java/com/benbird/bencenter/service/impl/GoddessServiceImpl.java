package com.benbird.bencenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benbird.bencenter.common.ParamValidate;
import com.benbird.bencenter.mapper.GoddessMapper;
import com.benbird.bencenter.models.DO.GoddessDO;
import com.benbird.bencenter.query.BaseQuery;
import com.benbird.bencenter.service.GoddessService;
import com.benbird.bencenter.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/30
 * 描述: 人间烟火服务
 * @author Admin
 */
@Service
@Slf4j
public class GoddessServiceImpl extends ServiceImpl<GoddessMapper , GoddessDO> implements GoddessService {

    @Autowired
    private GoddessMapper goddessMapper;

    /**
     * 分页查询数据
     * @param goddessDO     查询DO
     * @param pageNo        当前页
     * @param pageSize      页容量
     * @return              分页数据
     */
    @Override
    public Page<GoddessDO> queryPageList(GoddessDO goddessDO, Integer pageNo, Integer pageSize) {
        Page<GoddessDO> page = new Page<>(pageNo, pageSize);
        QueryWrapper<GoddessDO> query = BaseQuery.query();
        if(null != goddessDO && StringUtils.isNotEmpty(goddessDO.getMonth())){
            query.eq("MONTH", goddessDO.getMemo());
        }
        Page<GoddessDO> list = goddessMapper.selectPage(page,query);
        log.info("人间烟火分页查询数据,请求信息{},{},响应信息{}",page,query,list.getRecords());
        return list;
    }

    /**
     * 新增DO数据
     * @param goddessDO     DO数据
     * @return              新增结果
     */
    @Override
    public Integer addGoddess(GoddessDO goddessDO) {
        goddessDO.setCreatedAt(DateUtil.getCurrentDate());
        goddessDO.setCreatedBy(goddessDO.getUpdatedBy());
        int count = goddessMapper.insert(goddessDO);
        log.info("人间烟火新增DO数据,请求DO{},响应结果{}",goddessDO,count);
        return count;
    }

    /**
     * 根据ID修改DO数据
     * @param goddessDO     DO数据
     * @return              修改结果
     */
    @Override
    public Integer modifyById(GoddessDO goddessDO) {
        int count = goddessMapper.updateById(goddessDO);
        log.info("人间烟火根据ID修改DO数据,请求DO{},响应结果{}",goddessDO,count);
        return count;
    }

    /**
     * 根据ID查询数据
     * @param id            ID
     * @return              DO
     */
    @Override
    public GoddessDO queryById(Integer id) {
        GoddessDO goddessDO = goddessMapper.selectById(id);
        log.info("人间烟火根据ID查询数据:{},{}",id,goddessDO);
        ParamValidate.validateUsable(goddessDO);
        return goddessDO;
    }

    /**
     * 根据ID删除数据
     * @param id            主键
     * @param updatedBy     更新人
     * @return              Integer
     */
    @Override
    public Integer modifyToUnUseById(Integer id, String updatedBy) {
        return goddessMapper.modifyToUnUseById(id, updatedBy);
    }
}
