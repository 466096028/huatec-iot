package com.huatec.iot.common.response;

import com.huatec.iot.common.enums.ResultCodeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;
import java.util.Collections;

/**
 * @author ms.wang
 * @date 2021/10/27
 */
@ApiModel("分页消息体")
public class PageDataResponse<T> extends Response {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("总数")
    private int totalCount;
    @ApiModelProperty("分页大小")
    private int pageSize;
    @ApiModelProperty("分页索引")
    private int pageIndex;
    @ApiModelProperty("返回对象")
    private Collection<T> data;
    @ApiModelProperty("返回对象")
    private Integer count;
    public PageDataResponse() {
        this.pageSize = 10;
        this.pageIndex = 1;
        this.totalCount = 0;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return Math.max(this.pageSize, 1);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = Math.max(pageSize, 1);

    }

    public int getPageIndex() {
        return Math.max(this.pageIndex, 1);
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = Math.max(pageIndex, 1);

    }

    public Collection<T> getData() {
        return this.data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public int getTotalPages() {
        return this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
    }

    public boolean isEmpty() {
        return this.data == null || this.data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public static <T> PageDataResponse<T> of(int pageSize, int pageIndex, Integer count) {
        PageDataResponse<T> response = new PageDataResponse<>();
        response.setSuccess(true);
        response.setData(Collections.emptyList());
        response.setTotalCount(0);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        response.setCount(count);
        return response;
    }

    public static <T> PageDataResponse<T> of(Collection<T> data, int totalCount, int pageSize, int pageIndex, Integer count) {
        PageDataResponse<T> response = new PageDataResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        response.setCode(ResultCodeEnums.SUCCESS.code);
        response.setMessage(ResultCodeEnums.SUCCESS.message);
        response.setCount(count);
        return response;
    }

}
