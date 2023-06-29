package com.huatec.iot.common.exception;

public final class MqttException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    private boolean success;
    /**
     * 错误明细，内部调试错误
     * <p>
     * 和 {@link CommonResult#getDetailMessage()} 一致的设计
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public MqttException() {
    }

    public MqttException(String message) {
        this.message = message;
    }

    public MqttException(String message, Integer code) {
        this.message = message;
        this.code = code;
        this.success = false;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }


    public MqttException setMessage(String message) {
        this.message = message;
        return this;
    }

    public MqttException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
}
