package com.benbird.bencenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benbird.bencenter.common.BenBirdConstant;
import com.benbird.bencenter.models.DO.GoddessDO;
import com.benbird.bencenter.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/31
 * 描述:
 */
@SpringBootTest
public class GoddessServiceTest {

    @Autowired
    private GoddessService goddessService;
    @Test
    public void query(){
        Page<GoddessDO> goddessDOPage = goddessService.queryPageList(null, 2, 10);
        System.out.println(goddessDOPage);
    }

    @Test
    public void add(){
        Date currentDate = DateUtil.getCurrentDate();
        Date date = DateUtil.plusMonths(currentDate, -1000);
        for(int i = 0 ; i < 1000 ; i++){
            GoddessDO goddessDO = new GoddessDO();
            Date month = DateUtil.plusMonths(date, i);
            goddessDO.setMonth(DateUtil.format(month,DateUtil.DATE_MONTH_FORMAT));
            Date monthBegDay = DateUtil.getMonthBegDay(month);
            goddessDO.setStartDate(DateUtil.formatToDate(monthBegDay,DateUtil.DATE_SHOW_FORMAT));
            goddessDO.setEndDate(DateUtil.formatToDate(DateUtil.getLastDayOfMonth(month),DateUtil.DATE_SHOW_FORMAT));
            goddessDO.setMemo("这是备注");
            goddessDO.setUsableFlag(BenBirdConstant.USABLE);
            goddessDO.setCreatedAt(currentDate);
            goddessDO.setUpdatedAt(currentDate);
            goddessDO.setCreatedBy(BenBirdConstant.SYSTEM_NAME);
            goddessDO.setUpdatedBy(BenBirdConstant.SYSTEM_NAME);
            goddessService.addGoddess(goddessDO);
        }
    }


}
