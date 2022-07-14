package com.xlf.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class PageResponse<T> implements Serializable {

    private static final long serialVersionUID = -5352370869975619116L;

    /**
     * 页号，从1开始
     **/
    private Integer pageNum;

    /**
     * 每页显示行数
     **/
    private Integer pageSize;

    /**
     * 总行数
     **/
    private Long total;

    /**
     * @since 1.0.2
     */
    private Boolean lastPage;

    /**
     * 实体记录
     */
    private List<T> list = Collections.emptyList();


    public PageResponse(final int pageNum, final int pageSize, final long total, final List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        this.lastPage = (this.pageNum - 1) * pageSize + list.size() >= total;
    }

    public static <T> PageResponse<T> empty() {
        final PageResponse<T> response = new PageResponse<>();
        response.pageNum = 0;
        response.pageSize = 0;
        response.lastPage = true;
        response.total = 0L;
        response.list = Collections.emptyList();
        return response;
    }
}