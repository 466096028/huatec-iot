package com.huatec.iot.iotservice.service;

import com.huatec.iot.iotservice.pojo.vo.ProductEnumsVo;

/**
 * @program: huatec-iot-api
 * @description: 公共Service
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
public interface CommonService {

    ProductEnumsVo productEnums();

    void initData(Integer userId);
}
