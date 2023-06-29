package com.huatec.iot.common.response;

import com.huatec.iot.common.enums.ResultCodeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ms.wang
 */
@ApiModel("结果基类")
public class Response {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("状态消息")
    private String message;

    public Response() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response [success=" + this.success + ", errCode=" + this.code + ", errMessage=" + this.message + "]";
    }

    public static Response buildSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(ResultCodeEnums.SUCCESS.code);
        response.setMessage(ResultCodeEnums.SUCCESS.message);
        return response;
    }

    public static Response buildFailure(Integer errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(errCode);
        response.setMessage(errMessage);
        return response;
    }

}
