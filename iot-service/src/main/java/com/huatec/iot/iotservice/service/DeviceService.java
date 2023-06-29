package com.huatec.iot.iotservice.service;

import com.huatec.iot.common.entity.Devices;
import com.huatec.iot.iotservice.pojo.dto.DevicesDto;
import com.huatec.iot.iotservice.pojo.qry.DevicesPageQry;
import com.huatec.iot.iotservice.pojo.qry.DevicesQry;
import com.huatec.iot.iotservice.pojo.vo.ConnectParamsVo;
import com.huatec.iot.iotservice.pojo.vo.DevicesProductsVo;
import com.huatec.iot.iotservice.pojo.vo.DevicesVo;
import com.huatec.iot.iotservice.pojo.vo.MqttConnectInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
public interface DeviceService {
    /**
     * 获取分页列表
     * @param devicesPageQry
     * @return
     */
    List<DevicesProductsVo> getPageList(DevicesPageQry devicesPageQry);

    /**
     * 获取所有列表
     * @return
     */
    List<DevicesVo> allList(DevicesQry devicesQry);

    /**
     * 获取详情
     * @param id
     * @return
     */
    DevicesVo getInfo(Integer id);

    Devices getInfoById(Integer id);

    /**
     * 删除数据
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 添加数据
     * @param devicesDto
     */
    void insert(DevicesDto devicesDto);

    /**
     * 修改数据
     * @param devicesDto
     */
    void update(DevicesDto devicesDto);

    /**
     * 修改状态
     * @param id
     * @param status
     */
    void updateStatus(Integer id, Byte status);

    /**
     * 连接参数
     * @param id
     * @return
     */
    ConnectParamsVo connectParams(Integer id);


    /**
     * 通过产品Key和设备Key修改设备数据
     * @param devices
     */
    void updateByProductKeyAndDeviceKey(Devices devices);

    Devices checkExists(Devices devices);

    Map<String, MqttConnectInfoVo> mqttConnectInfo(String userName);
}
