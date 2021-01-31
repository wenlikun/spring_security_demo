package com.benbird.bencenter.dto.req;

import com.benbird.bencenter.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/31
 * 描述: 人间烟火请求DTO
 * @author Admin
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GoddessReqDTO extends PaginationReqDTO {

    private String month;

    private Date startDate;

    private Date endDate;

    private String memo;

}
