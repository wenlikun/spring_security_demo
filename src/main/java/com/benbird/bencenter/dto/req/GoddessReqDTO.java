package com.benbird.bencenter.dto.req;

import com.benbird.bencenter.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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

    @NotBlank(message = "月份不能为空!")
    private String month;

    @NotNull(message = "开始日期不能为空!")
    private Date startDate;

    @NotNull(message = "结束日期不能为空!")
    private Date endDate;

    private String memo;

}
