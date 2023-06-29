package com.huatec.iot.common.pojo;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-03-29 10:24
 **/
@FunctionalInterface
public interface ThrowExceptionFunction {
	/**
	 * 抛出异常信息
	 *
	 * @param message 异常信息
	 * @return void
	 **/
	void throwMessage(String message);
}
