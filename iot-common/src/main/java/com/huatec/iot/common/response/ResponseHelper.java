package com.huatec.iot.common.response;

import com.github.pagehelper.Page;

import java.util.Collection;

/**
 * @author ms.wang
 */
public class ResponseHelper {

    private ResponseHelper() {
    }

    public static Response buildSuccess() {
        return Response.buildSuccess();
    }

    public static <T> SingleResponse<T> buildSingle(T data) {
        return SingleResponse.of(data);
    }

    public static <T> MultiResponse<T> buildMulti(Collection<T> data) {
        return MultiResponse.of(data);
    }

    public static <T> PageResponse<T> buildPage(Collection<T> data) {
        if (data == null) {
            return new PageResponse<>();
        }
        if (data instanceof Page) {
            Page<T> page = (Page<T>) data;
            return PageResponse.of(page, (int) page.getTotal(), page.getPageSize(), page.getPageNum());
        } else {
            return PageResponse.of(data, data.size(), data.size(), 1);
        }
    }

    public static <T> PageDataResponse<T> buildPage(Collection<T> data, Integer count) {
        if (data == null) {
            return new PageDataResponse<>();
        }
        if (data instanceof Page) {
            Page<T> page = (Page<T>) data;
            return PageDataResponse.of(page, (int) page.getTotal(), page.getPageSize(), page.getPageNum(),count);
        } else {
            return PageDataResponse.of(data, data.size(), data.size(), 1,count);
        }
    }

    public static Response buildFailure(Integer errCode, String errMessage) {
        return Response.buildFailure(errCode, errMessage);
    }


}
