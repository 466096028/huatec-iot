package com.huatec.iot.iotservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.huatec.iot.common.config.MqttConfig;
import com.huatec.iot.common.entity.Devices;
import com.huatec.iot.common.entity.ProductModuleAbilitys;
import com.huatec.iot.common.entity.Products;
import com.huatec.iot.common.entity.Users;
import com.huatec.iot.common.enums.*;
import com.huatec.iot.common.utils.UserUtils;
import com.huatec.iot.common.utils.UuidUtils;
import com.huatec.iot.common.utils.VUtils;
import com.huatec.iot.iotservice.mapper.DevicesMapper;
import com.huatec.iot.iotservice.mapper.ProductsMapper;
import com.huatec.iot.iotservice.pojo.dto.DevicesDto;
import com.huatec.iot.iotservice.pojo.qry.DevicesPageQry;
import com.huatec.iot.iotservice.pojo.qry.DevicesQry;
import com.huatec.iot.iotservice.pojo.qry.TopicsQry;
import com.huatec.iot.iotservice.pojo.vo.*;
import com.huatec.iot.iotservice.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DevicesMapper devicesMapper;

    @Autowired
    ProductsMapper productsMapper;

    @Autowired
    TopicService topicService;

    @Autowired
    MqttConfig mqttConfig;

    @Autowired
    DeviceEsService deviceEsService;

    @Autowired
    UserService userService;

    @Autowired
    CommonService commonService;

    @Override
    public List<DevicesProductsVo> getPageList(DevicesPageQry devicesPageQry) {
        Example example = new Example(Devices.class);
        example.setOrderByClause("id desc");
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(devicesPageQry.getDeviceKey())) {
            criteria.andLike("deviceKey", "%" + devicesPageQry.getDeviceKey() + "%");
        }
        if (StringUtils.isNotBlank(devicesPageQry.getDeviceName())) {
            criteria.andLike("deviceName", "%" + devicesPageQry.getDeviceName() + "%");
        }
        if (devicesPageQry.getProductId() != null && devicesPageQry.getProductId() != 0) {
            criteria.andEqualTo("productId", devicesPageQry.getProductId());
        }
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());

        List<Devices> select = devicesMapper.selectByExample(example);
        log.info("【设备模块】分页查询数据库-参数，params：{}，result：{}", JSON.toJSONString(criteria), select);


        List<DevicesProductsVo> list = select.stream().map(info -> {
            DevicesProductsVo devicesProductsVo = new DevicesProductsVo();
            DevicesVo devicesVo = new DevicesVo();
            BeanUtils.copyProperties(info, devicesVo);
            devicesVo.setDeviceStatusName(DeviceStatusEnums.getDesc(devicesVo.getDeviceStatus()));
            devicesProductsVo.setDevicesVo(devicesVo);

            Products products = productsMapper.selectByPrimaryKey(info.getProductId());
            if (!Objects.isNull(products)) {
                ProductsVo productsVo = new ProductsVo();
                BeanUtils.copyProperties(products, productsVo);
                productsVo.setNodeTypeName(NodeTypeEnums.getDesc(productsVo.getNodeType()));
                productsVo.setNetTypeName(NetTypeEnums.getDesc(productsVo.getNetType()));
                devicesProductsVo.setProductsVo(productsVo);
            }

            return devicesProductsVo;
        }).collect(Collectors.toList());
        log.info("【设备模块】分页查询-结果，result：{}", list);

        return list;
    }

    @Override
    public List<DevicesVo> allList(DevicesQry devicesQry) {
        Devices devices = new Devices();
        BeanUtils.copyProperties(devicesQry, devices);
        devices.setStatus((byte) StatusEnums.ENABLE_STATUS.getCode());
        devices.setCreateUserId(UserUtils.getUserInfo().getUserId());
        List<Devices> select = devicesMapper.select(devices);
        log.info("【设备模块】查询数据库所有数据，params：{}，result：{}", devicesQry, select);

        List<DevicesVo> list = select.stream().map(info -> {
            DevicesVo devicesVo = new DevicesVo();
            BeanUtils.copyProperties(info, devicesVo);

            return devicesVo;
        }).collect(Collectors.toList());
        log.info("【设备模块】查询所有数据-结果，result：{}", list);

        return list;
    }

    @Override
    public DevicesVo getInfo(Integer id) {
        Example example = new Example(Devices.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());

        Devices devices = devicesMapper.selectOneByExample(example);
        log.info("【设备模块】获取设备详情-查询一条数据库数据，params：{}，result：{}", JSON.toJSONString(criteria), devices);
        DevicesVo devicesVo = null;
        if (!Objects.isNull(devices)) {
            devicesVo = new DevicesVo();
            BeanUtils.copyProperties(devices, devicesVo);
            devicesVo.setDeviceStatusName(DeviceStatusEnums.getDesc(devicesVo.getDeviceStatus()));

            Optional<Products> products = Optional.ofNullable(productsMapper.selectByPrimaryKey(devicesVo.getProductId()));
            if (products.isPresent()) {
                devicesVo.setNodeType(products.get().getNodeType());
                devicesVo.setNodeTypeName(NodeTypeEnums.getDesc(products.get().getNodeType()));
                devicesVo.setProductName(products.get().getProductName());
            }
        }

        return devicesVo;
    }

    @Override
    public Devices getInfoById(Integer id) {
        Example example = new Example(Devices.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());

        Devices devices = devicesMapper.selectOneByExample(example);
        return devices;
    }

    @Override
    public void deleteById(Integer id) {
        Devices devices = new Devices();
        devices.setId(id);
        Optional<Devices> devices1 = Optional.ofNullable(checkExists(devices));
        VUtils.isTure(!devices1.isPresent()).throwMessage("设备不存在");

        Example example = new Example(Devices.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());

        Optional<Devices> devices2 = Optional.ofNullable(devicesMapper.selectOneByExample(example));
        if (devices2.isPresent()) {
            VUtils.isTure(devices2.get().getIsSystem() != null && devices2.get().getIsSystem() == 1).throwMessage("系统添加的设备不能删除");
        }

        int i = devicesMapper.deleteByExample(example);
        log.info("【设备模块】删除数据，params：{}，result：{}", JSON.toJSONString(criteria), i);
        if (i > 0) {
            String indexName = devices1.get().getProductKey() + "." + devices1.get().getDeviceKey();
            deviceEsService.deleteIndex(indexName);
        }

        VUtils.isTure(i == 0).throwMessage("删除失败");
    }

    @Override
    public void insert(DevicesDto devicesDto) {
        Devices devices = new Devices();
        BeanUtils.copyProperties(devicesDto, devices);

        if (StringUtils.isBlank(devicesDto.getDeviceKey())) {
            devices.setDeviceKey(UuidUtils.generateDeviceId());
        }
        devices.setDeviceKey(devices.getDeviceKey().toLowerCase());
        if (devices.getCreateUserId() == null || devices.getCreateUserId() <= 0) {
            devices.setCreateUserId(UserUtils.getUserInfo().getUserId());
        }

        log.info("【设备模块】添加数据-参数：params：{}", devices);

        // 查询key是否重复
        Devices checkDevices = new Devices();
        checkDevices.setDeviceKey(devices.getDeviceKey());
        Optional<Devices> checkResult = Optional.ofNullable(checkExists(checkDevices));
        VUtils.isTure(checkResult.isPresent()).throwMessage("存在重复的设备key");

        int insert = 0;
        try {
            insert = devicesMapper.insertSelective(devices);
            log.info("【设备模块】添加数据-结果：result：{}", insert);
        } catch (Exception e) {
        }

        VUtils.isTure(insert == 0).throwMessage("添加失败");
    }

    @Override
    public void update(DevicesDto devicesDto) {

        Example example = new Example(Devices.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", devicesDto.getId());
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());
        log.info("【设备模块】修改数据-参数，params：{}", JSON.toJSONString(criteria));

        Devices devices = new Devices();
        devices.setDeviceName(devicesDto.getDeviceName());

        int update = 0;
        try {
            update = devicesMapper.updateByExampleSelective(devices, example);
            log.info("【设备模块】修改数据-结果，result：{}", update);
        } catch (Exception e) {
        }

        VUtils.isTure(update == 0).throwMessage("修改失败");
    }

    @Override
    public void updateStatus(Integer id, Byte status) {
        Example example = new Example(Devices.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());
        log.info("【设备模块】修改状态数据-参数，params：{}", JSON.toJSONString(criteria));

        Devices devices = new Devices();
        devices.setStatus(status);

        int i = devicesMapper.updateByExampleSelective(devices, example);
        log.info("【设备模块】修改状态数据-参数，params：{}，result：{}", devices, i);

        VUtils.isTure(i == 0).throwMessage("修改失败");
    }

    @Override
    public ConnectParamsVo connectParams(Integer id) {
        Devices devices1 = new Devices();
        devices1.setId(id);
        devices1.setCreateUserId(UserUtils.getUserInfo().getUserId());
        Devices devices = devicesMapper.selectOne(devices1);
        log.info("【设备模块】连接信息-参数，params：{}，result：{}", devices1, devices);

        ConnectParamsVo connectParamsVo = new ConnectParamsVo();
        connectParamsVo.setClientId(MqttEnums.CONNECT_CLIENT_ID.getCode().replace("${productKey}", devices.getDeviceKey()).replace("${deviceKey}", devices.getProductKey()));
        connectParamsVo.setUsername(mqttConfig.getUsername());
        connectParamsVo.setPasswd(mqttConfig.getPassword());
        connectParamsVo.setMqttUrl(mqttConfig.getHostUrl());
        connectParamsVo.setPort(mqttConfig.getPort());
        log.info("【设备模块】连接信息-结果，result：{}", connectParamsVo);

        return connectParamsVo;
    }

    @Override
    public void updateByProductKeyAndDeviceKey(Devices devices) {
        log.info("【设备模块】通过产品Key和设备Key修改设备数据-参数：params：{}", devices);

        int update = 0;
        try {
            Devices devices1 = new Devices();
            devices1.setProductKey(devices.getProductKey());
            devices1.setDeviceKey(devices.getDeviceKey());
            Devices devicesInfo = devicesMapper.selectOne(devices1);
            VUtils.isTure(Objects.isNull(devicesInfo)).throwMessage("产品Key和设备Key不存在");

            if (devicesInfo.getActivationTime() != null) {
                devices.setActivationTime(null);
            }
            devices.setId(devicesInfo.getId());

            update = devicesMapper.updateByPrimaryKeySelective(devices);
            log.info("【设备模块】通过产品Key和设备Key修改设备数据-结果：params：{}，result：{}", devices, update);
        } catch (Exception e) {
            log.error("【设备模块】通过产品Key和设备Key修改设备数据-异常：exception：{}", e.getMessage());
        }

        VUtils.isTure(update == 0).throwMessage("修改失败");
    }


    @Override
    public Devices checkExists(Devices devices) {
        log.info("【设备模块】检查设备数据是否存在-参数：params：{}", devices);

        try {
            // 判断是不是mq的校验，这时候不用传用户ID
            if (devices.getCreateUserId() != null && devices.getCreateUserId() == -1) {
                devices.setCreateUserId(null);
            } else if (devices.getCreateUserId() == null || devices.getCreateUserId() <= 0) {
                devices.setCreateUserId(UserUtils.getUserInfo().getUserId());
            }

            Optional<Devices> devices1 = Optional.ofNullable(devicesMapper.selectOne(devices));
            if (devices1.isPresent()) {
                log.info("【设备模块】检查设备数据是否存在-结果：params：{}，result：{}", devices1.get());
                return devices1.get();
            } else {
                log.info("【设备模块】检查设备数据是否存在-结果：params：{}，result：{}", "没有找到设备");
            }

        } catch (Exception e) {
            log.error("【设备模块】检查设备数据是否存在-异常：exception：{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, MqttConnectInfoVo> mqttConnectInfo(String userName) {
        Map<String, MqttConnectInfoVo> map = new HashMap<>();
        Users users = new Users();
        users.setUserName(userName);
        Users info = userService.getInfo(users);
        log.info("【设备模块】获取系统MQTT连接信息-获取用户信息，userName：{}，result：{}", userName, info);
        if (!Objects.isNull(info)) {
            Integer userId = info.getUserId();
            try {
                commonService.initData(userId);
            } catch (Exception e) {
            }
            Example example = new Example(Devices.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("createUserId", userId);
            criteria.andEqualTo("isSystem", 1);

            try {
                for (int i = 0; i < 10; i++) {
                    Optional<List<Devices>> devices = Optional.ofNullable(devicesMapper.selectByExample(example));
                    if (devices.isPresent()) {
                        log.info("【设备模块】获取系统MQTT连接信息-查询数据库，params：{}，count：{}，result：{}", JSON.toJSONString(criteria), i, devices.get());
                        devices.get().forEach(item -> {
                            MqttConnectInfoVo mqttConnectInfoVo = new MqttConnectInfoVo();
                            mqttConnectInfoVo.setClientid(MqttEnums.CONNECT_CLIENT_ID.getCode().replace("${productKey}", item.getDeviceKey()).replace("${deviceKey}", item.getProductKey()));
                            mqttConnectInfoVo.setUsername(mqttConfig.getUsername());
                            mqttConnectInfoVo.setPasswd(mqttConfig.getPassword());
                            mqttConnectInfoVo.setMqttHostUrl(mqttConfig.getHostUrl());
                            mqttConnectInfoVo.setPort(mqttConfig.getPort().toString());
                            mqttConnectInfoVo.setProductKey(item.getProductKey());
                            mqttConnectInfoVo.setDeviceName(item.getDeviceName());
                            mqttConnectInfoVo.setDeviceSecret("");
                            mqttConnectInfoVo.setPublishTopic(TopicEnums.CLIENT_PROPERTY_POST_EVENT.getCode().replace("${productKey}", item.getProductKey()).replace("${deviceKey}", item.getDeviceKey()));
                            mqttConnectInfoVo.setSubscribeTopic(TopicEnums.CLIENT_PROPERTY_SET_SERVICE.getCode().replace("${productKey}", item.getProductKey()).replace("${deviceKey}", item.getDeviceKey()));

                            map.put(item.getDeviceName(), mqttConnectInfoVo);
                        });
                        break;
                    } else {
                        sleep(200);
                        log.info("【设备模块】获取系统MQTT连接信息-参数，params：{}，count：{}，result：{}", JSON.toJSONString(criteria), i, "没有找到对应的设备");
                    }
                }

            } catch (Exception e) {
            }
        }

        return map;
    }
}
