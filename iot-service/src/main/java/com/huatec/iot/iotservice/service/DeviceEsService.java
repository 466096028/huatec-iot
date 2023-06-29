package com.huatec.iot.iotservice.service;

import com.huatec.iot.common.pojo.MessageDto;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityDataQry;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilityDatasVo;

import java.util.List;
import java.util.Map;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
public interface DeviceEsService {

    void insertData(String indexName, MessageDto messageDto);

    List<ProductModuleAbilityDatasVo> searchData(ProductModuleAbilityDataQry productModuleAbilityDataQry);

    Map<String, Object> searchLatestData(String indexName);

    List<MessageDto> searchAllAbilityData(ProductModuleAbilityDataQry productModuleAbilityDataQry);

    void deleteIndex(String indexName);
}
