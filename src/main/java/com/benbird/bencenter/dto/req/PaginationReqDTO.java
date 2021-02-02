package com.benbird.bencenter.dto.req;

import com.benbird.bencenter.dto.BaseDTO;
import lombok.Setter;
import lombok.ToString;


/**
 * 分页参数信息
 * @author Admin
 */
@Setter
@ToString
public class PaginationReqDTO extends BaseDTO {

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 当前页显示条数
     */
    private Integer pageCount;

    /**
     * 获取开始索引
     *
     * @return 开始索引
     */
    public int getStartRow() {

        return (currentPage - 1) * pageCount;
    }

    /**
     * 当前页
     * @return Integer
     */
    public Integer getCurrentPage() {
        return currentPage == null ? 1 : currentPage;
    }

    /**
     * 当前页显示条数
     * @return Integer
     */
    public Integer getPageCount() {
        return pageCount == null ? 20 : pageCount;
    }
}
