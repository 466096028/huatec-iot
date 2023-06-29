package com.huatec.iot.iotservice.service;

import com.huatec.iot.iotservice.pojo.qry.TopicsQry;
import com.huatec.iot.iotservice.pojo.vo.TopicsVo;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 通信TopicService
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
public interface TopicService {

    /**
     * 获取所有列表
     *
     * @return
     */
    List<TopicsVo> allList(TopicsQry topicsQry);
}
