package com.benbird.bencenter.dto.resp;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回包装对象
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageRespDTO<T> implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -8859942173367694002L;

    /**
     * 总行数
     */
    private Integer totalSize;

    /**
     * 分页结果集
     */
    private List<T> results;
}
