package com.benbird.bencenter.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benbird.bencenter.common.BenBirdConstant;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 基础查询
 * @author Admin
 */
public class BaseQuery<T> {

    /**
     * 基础查询语句
     * @param <T>   范型
     * @return      QueryWrapper
     */
    public static <T> QueryWrapper<T> query(){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USABLE_FLAG" , BenBirdConstant.USABLE);
        return queryWrapper;
    }

}
