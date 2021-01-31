package com.benbird.bencenter.dto.resp;

import com.benbird.bencenter.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/31
 * 描述: 人间烟火响应DTO
 * @author Admin
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GoddessRespDTO extends BaseDTO {


    private String month;

    private Date startDate;

    private Date endDate;

    private String memo;

}
