package com.huatec.iot.iotservice.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huatec.iot.common.entity.*;
import com.huatec.iot.common.enums.DataTypeEnums;
import com.huatec.iot.common.enums.NetTypeEnums;
import com.huatec.iot.common.enums.NodeTypeEnums;
import com.huatec.iot.common.enums.UnitTypeEnums;
import com.huatec.iot.common.utils.UserUtils;
import com.huatec.iot.common.utils.VUtils;
import com.huatec.iot.iotservice.pojo.dto.DevicesDto;
import com.huatec.iot.iotservice.pojo.dto.ProductsDto;
import com.huatec.iot.iotservice.pojo.vo.ProductEnumsVo;
import com.huatec.iot.iotservice.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @program: huatec-iot-api
 * @description: 公共ServiceImpl
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Autowired
    ProductService productService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryAbilityService categoryAbilityService;

    @Autowired
    ProductModuleAbilityService productModuleAbilityService;


    @Override
    public ProductEnumsVo productEnums() {
        ProductEnumsVo productEnumsVo = new ProductEnumsVo();
        productEnumsVo.setNodeTypeList(NodeTypeEnums.NodeType.getList());
        productEnumsVo.setNetTypeList(NetTypeEnums.NetType.getList());
        productEnumsVo.setDataTypeList(DataTypeEnums.DataType.getList());
        productEnumsVo.setUnitTypeList(UnitTypeEnums.UnitType.getList());

        return productEnumsVo;
    }

    @Override
    public void initData(Integer userId) {
        try {

            Users users = new Users();
            if (userId == null || userId <= 0) {
                userId = UserUtils.getUserInfo().getUserId();
            }
            users.setUserId(userId);
            Optional<Users> info = Optional.ofNullable(userService.getInfo(users));
            log.info("【公共模块】初始化数据-查询用户信息，userId：{}，users：{}", users, info);
            if (info.isPresent()) {
                ProductModuleAbilitys productModuleAbilitys1 = new ProductModuleAbilitys();
                productModuleAbilitys1.setCreateUserId(userId);
                Integer count = productModuleAbilityService.getCount(productModuleAbilitys1);
                VUtils.isTure(count > 0).throwMessage("初始数据已存在");

                //初始化产品
                String productInitStr = "[{\"categoryId\":3,\"categoryKey\":\"Electric_Monitor\",\"categoryName\":\"电力监控\",\"dataFormat\":1,\"description\":\" 汽车总装车间，电能监测\",\"netType\":1,\"nodeType\":1,\"productName\":\"ElectricMonitorMainLine\"},{\"categoryId\":6,\"categoryKey\":\"Main_Line_Auto_Controller\",\"categoryName\":\"总线自动化控制\",\"dataFormat\":1,\"description\":\"汽车总装车间，自动化生产线自动化控制\",\"netType\":1,\"nodeType\":1,\"productName\":\"MainLineAutoController\"},{\"categoryId\":9,\"categoryKey\":\"Robot_Running_State_Monitor\",\"categoryName\":\"机器人运行状态监视器\",\"dataFormat\":1,\"description\":\"汽车总装生产线，工业6关节机器人状态监控\",\"netType\":1,\"nodeType\":1,\"productName\":\"RobotRunningStateMonitor\"}]";
                JSONArray objects = JSONObject.parseArray(productInitStr);
                for (Object item : objects) {
                    ProductsDto productsDto = JSONObject.parseObject(item.toString(), ProductsDto.class);
                    productsDto.setIsSystem((byte) 1);
                    productsDto.setCreateUserId(userId);
                    Products insert = productService.insert(productsDto);

                    // // 产品添加成功能，给产品创建一个默认的模型功能列表
                    // Optional<List<CategoryAbilitys>> listByCategoryId = Optional.ofNullable(categoryAbilityService.getListByCategoryId(productsDto.getCategoryId()));
                    // if (listByCategoryId.isPresent()) {
                    //     // 组装模型功能数据
                    //     List<ProductModuleAbilitys> ProductModuleAbilitysList = new ArrayList();
                    //     for (CategoryAbilitys categoryAbilitys : listByCategoryId.get()) {
                    //         ProductModuleAbilitys productModuleAbilitys = new ProductModuleAbilitys();
                    //         BeanUtils.copyProperties(categoryAbilitys, productModuleAbilitys);
                    //         productModuleAbilitys.setId(null);
                    //         productModuleAbilitys.setModuleId(0);
                    //         productModuleAbilitys.setIsRequired((byte) 1);
                    //         productModuleAbilitys.setCreateUserId(userId);
                    //         productModuleAbilitys.setProductId(insert.getId());
                    //         ProductModuleAbilitysList.add(productModuleAbilitys);
                    //     }
                    //     productModuleAbilityService.batchInsert(ProductModuleAbilitysList);
                    // }

                    //组装设备数据
                    DevicesDto devicesDto = new DevicesDto();
                    devicesDto.setProductId(insert.getId());
                    devicesDto.setProductKey(insert.getProductKey());
                    devicesDto.setIsSystem((byte) 1);
                    devicesDto.setCreateUserId(userId);

                    switch (insert.getCategoryId()) {
                        case 3:
                            devicesDto.setDeviceName("ElectricMonitorMainLine_1");
                            deviceService.insert(devicesDto);
                            break;
                        case 6:
                            devicesDto.setDeviceName("MainLineAutoController_1");
                            deviceService.insert(devicesDto);
                            break;
                        case 9:
                            List<String> strings = Arrays.asList("RobotRunningStateMonitor_1", "RobotRunningStateMonitor_2", "RobotRunningStateMonitor_3");
                            strings.forEach(item1 -> {
                                devicesDto.setDeviceName(item1);
                                deviceService.insert(devicesDto);
                            });
                            break;

                    }
                }
            }
        } catch (Exception e) {
            VUtils.isTure(true).throwMessage("初始化数据已存在");
        }
    }
}
