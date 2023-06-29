package com.huatec.iot.common.response;

import com.huatec.iot.common.enums.ResultCodeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

/**
 * @author ms.wang
 */
@ApiModel("多数据响应消息体")
public class MultiResponse<T> extends Response {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("返回对象")
    private Collection<T> data;

    public MultiResponse() {
    }

    public Collection<T> getData() {
        return this.data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public boolean isEmpty() {
        return this.data == null || this.data.size() == 0;
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public static <T> MultiResponse<T> of(Collection<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setCode(ResultCodeEnums.SUCCESS.code);
        response.setMessage(ResultCodeEnums.SUCCESS.message);
        return response;
    }
}
