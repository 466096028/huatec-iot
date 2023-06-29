package com.huatec.iot.iotservice.service;

import com.huatec.iot.iotservice.pojo.qry.ByteVParamsQry;

import java.util.List;
import java.util.Map;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
public interface ByteVService {

    Map abilitylatestData(ByteVParamsQry byteVParamsQry);

    List deviceLastestData(ByteVParamsQry byteVParamsQry);

    List multifunctionalLastestTwentyData(ByteVParamsQry byteVParamsQry);
}
