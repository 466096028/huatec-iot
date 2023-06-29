package com.huatec.iot.iotservice.service.impl;

import com.huatec.iot.common.entity.Topics;
import com.huatec.iot.common.enums.AuthorizationTypeEnums;
import com.huatec.iot.common.enums.StatusEnums;
import com.huatec.iot.common.enums.TopicTypeEnums;
import com.huatec.iot.iotservice.mapper.TopicsMapper;
import com.huatec.iot.iotservice.pojo.qry.TopicsQry;
import com.huatec.iot.iotservice.pojo.vo.TopicsVo;
import com.huatec.iot.iotservice.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: huatec-iot-api
 * @description: 通信TopicServiceImpl
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicsMapper topicsMapper;

    @Override
    public List<TopicsVo> allList(TopicsQry topicsQry) {
        Topics topics = new Topics();
        topics.setStatus((byte) StatusEnums.ENABLE_STATUS.code);
        List<Topics> select = topicsMapper.select(topics);
        log.info("【通信Topic模块】查询数据库所有数据-结果：topicsQry：{}，result：{}", topicsQry, select);

        List<TopicsVo> list = select.stream().map(info -> {
            TopicsVo topicsVo = new TopicsVo();
            BeanUtils.copyProperties(info, topicsVo);
            topicsVo.setAuthorizationTypeName(AuthorizationTypeEnums.getDesc(topicsVo.getAuthorizationType()));
            topicsVo.setTypeName(TopicTypeEnums.getDesc(topicsVo.getType()));
            if (StringUtils.isNotBlank(topicsQry.getProductKey())) {
                topicsVo.setTopicUrl(topicsVo.getTopicUrl().replace("${productKey}", topicsQry.getProductKey()));
            }
            if (StringUtils.isNotBlank(topicsQry.getDeviceKey())) {
                topicsVo.setTopicUrl(topicsVo.getTopicUrl().replace("${deviceKey}", topicsQry.getDeviceKey()));
            }

            return topicsVo;
        }).collect(Collectors.toList());
        log.info("【通信Topic模块】查询所有数据-结果，list：{}", list);

        return list;
    }

}
