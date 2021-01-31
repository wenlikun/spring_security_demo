package com.benbird.bencenter.models.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.benbird.bencenter.models.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/30
 * 描述: 人间烟火
 * @author Admin
 */
@Data
@TableName("goddess")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GoddessDO extends BaseDO {

    private String month;

    private Date startDate;

    private Date endDate;

    private String memo;


}
