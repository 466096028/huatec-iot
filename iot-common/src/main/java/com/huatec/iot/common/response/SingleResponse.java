package com.huatec.iot.common.response;

import com.huatec.iot.common.enums.ResultCodeEnums;

/**
 * @author ms.wang
 */
public class SingleResponse<T> extends Response {
    private T data;

    public SingleResponse() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(true);
        response.setCode(ResultCodeEnums.SUCCESS.code);
        response.setMessage(ResultCodeEnums.SUCCESS.message);
        response.setData(data);
        return response;
    }
    public static <T> SingleResponse<T> failure(Integer errCode, String errMessage) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(false);
        response.setCode(errCode);
        response.setMessage(errMessage);
        return response;
    }
}
