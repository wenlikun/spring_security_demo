package com.benbird.bencenter.dto.req;

import com.benbird.bencenter.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页参数信息
 * @author Admin
 */
@Setter
@Getter
@ToString
public class PaginationReqDTO extends BaseDTO {

    /**
     * 当前页
     */
    @Min(value = 1, message = "当前页不能小于1")
    @NotNull(message = "当前页不能为空")
    private Integer currentPage;

    /**
     * 当前页显示条数
     */
    @NotNull(message = "每页显示记录数不能为空")
    @Max(value = 100, message = "每页显示记录数不能超过100")
    @Min(value = 1, message = "每页显示记录数不能小于1")
    private Integer pageCount;

    /**
     * 获取开始索引
     *
     * @return 开始索引
     */
    public int getStartRow() {

        return (currentPage - 1) * pageCount;
    }

}
