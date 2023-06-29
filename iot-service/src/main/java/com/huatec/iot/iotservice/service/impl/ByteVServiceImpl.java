package com.huatec.iot.iotservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huatec.iot.common.domain.spec.SpecFactory;
import com.huatec.iot.common.entity.Devices;
import com.huatec.iot.common.entity.ProductModuleAbilitys;
import com.huatec.iot.common.entity.Products;
import com.huatec.iot.common.enums.DeleteEnums;
import com.huatec.iot.common.pojo.DeviceMessageDto;
import com.huatec.iot.common.pojo.MessageDto;
import com.huatec.iot.iotservice.mapper.ProductModuleAbilitysMapper;
import com.huatec.iot.iotservice.pojo.qry.ByteVParamsQry;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityDataQry;
import com.huatec.iot.iotservice.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
@Service
@Slf4j
public class ByteVServiceImpl implements ByteVService {

    @Autowired
    ProductModuleAbilitysMapper productModuleAbilitysMapper;

    @Autowired
    SpecFactory specFactory;

    @Autowired
    DeviceEsService deviceEsService;

    @Autowired
    DeviceService deviceService;
    //
    @Autowired
    ProductService productService;


    @Override
    public Map abilitylatestData(ByteVParamsQry byteVParamsQry) {
        log.info("【bytev数据对接模块】指定功能的最新一条数据-参数，byteVParamsQry：{}", byteVParamsQry);

        Map<String, String> map1 = new HashMap<>();

        try {
            byteVParamsQry = verifyByteVParamsQry(byteVParamsQry);

            Products productsParam = new Products();
            productsParam.setProductKey(byteVParamsQry.getProductKey());
            productsParam.setCreateUserId(-1);
            Optional<Products> products = Optional.ofNullable(productService.checkExists(productsParam));
            if (products.isPresent()) {
                Integer productId = products.get().getId();

                Devices devicesParam = new Devices();
                devicesParam.setProductId(productId);
                devicesParam.setDeviceKey(byteVParamsQry.getDeviceKey());
                devicesParam.setCreateUserId(-1);
                Optional<Devices> devices = Optional.ofNullable(deviceService.checkExists(devicesParam));
                if (devices.isPresent()) {
                    // 获取设备功能对应的最新一条数据
                    Map<String, Object> map = deviceEsService.searchLatestData(devices.get().getProductKey() + "." + devices.get().getDeviceKey());
                    log.info("【bytev数据对接模块】指定功能的最新一条数据-数据库查询结果，indexName：{}，result：{}", devices.get().getProductKey() + "." + devices.get().getDeviceKey(), map);

                    if (StringUtils.isNotBlank(byteVParamsQry.getAbilityIdentifier())) {
                        // 组装产品模型功能的查询条件
                        Example example = new Example(ProductModuleAbilitys.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("productId", productId);
                        criteria.andEqualTo("abilityIdentifier", byteVParamsQry.getAbilityIdentifier());
                        criteria.andEqualTo("isDel", DeleteEnums.UNDELETED.code);
                        Optional<List<ProductModuleAbilitys>> select = Optional.ofNullable(productModuleAbilitysMapper.selectByExample(example));
                        log.info("【bytev数据对接模块】指定功能的最新一条数据-查询数据库结果，criteria：{}，result：{}", JSON.toJSONString(criteria), select);
                        if (select.isPresent()) {
                            // 组装返回数据
                            for (ProductModuleAbilitys item : select.get()) {
                                if (!Objects.isNull(map)) {
                                    if (!Objects.isNull(map.get(item.getAbilityIdentifier()))) {
                                        String s = specFactory.specValue(item.getDataType(), item.getDataSpecs(), String.valueOf(map.get(item.getAbilityIdentifier())));
                                        map1.put("value", s);
                                    }
                                }
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
        }

        log.info("【bytev数据对接模块】指定功能的最新一条数据-结果，byteVParamsQry：{}，list：{}", byteVParamsQry, map1);

        return map1;
    }


    @Override
    public List deviceLastestData(ByteVParamsQry byteVParamsQry) {
        log.info("【bytev数据对接模块】设备下所有功能的最新数据列表-参数，byteVParamsQry：{}", byteVParamsQry);

        List<Map<String, String>> list = new ArrayList<>();

        try {
            byteVParamsQry = verifyByteVParamsQry(byteVParamsQry);
            Products productsParam = new Products();
            productsParam.setProductKey(byteVParamsQry.getProductKey());
            productsParam.setCreateUserId(-1);
            Optional<Products> products = Optional.ofNullable(productService.checkExists(productsParam));
            if (products.isPresent()) {
                Integer productId = products.get().getId();

                Devices devicesParam = new Devices();
                devicesParam.setProductId(productId);
                devicesParam.setDeviceKey(byteVParamsQry.getDeviceKey());
                devicesParam.setCreateUserId(-1);
                Optional<Devices> devices = Optional.ofNullable(deviceService.checkExists(devicesParam));
                if (devices.isPresent()) {
                    // 获取设备功能对应的最新一条数据
                    Map<String, Object> map = deviceEsService.searchLatestData(devices.get().getProductKey() + "." + devices.get().getDeviceKey());
                    log.info("【bytev数据对接模块】设备下所有功能的最新数据列表-数据库查询结果，indexName：{}，result：{}", devices.get().getProductKey() + "." + devices.get().getDeviceKey(), map);

                    // 组装产品模型功能的查询条件
                    Example example = new Example(ProductModuleAbilitys.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("productId", productId);
                    criteria.andEqualTo("isDel", DeleteEnums.UNDELETED.code);
                    Optional<List<ProductModuleAbilitys>> select = Optional.ofNullable(productModuleAbilitysMapper.selectByExample(example));
                    log.info("【bytev数据对接模块】设备下所有功能的最新数据列表-查询数据库结果，criteria：{}，result：{}", JSON.toJSONString(criteria), select);
                    if (select.isPresent()) {
                        List<String> abilityIdentifierList = new ArrayList<>();
                        if (StringUtils.isNotBlank(byteVParamsQry.getAbilityIdentifier())) {
                            abilityIdentifierList = Arrays.asList(byteVParamsQry.getAbilityIdentifier().split(","));
                        }

                        // 组装返回数据
                        for (ProductModuleAbilitys item : select.get()) {
                            if (!Objects.isNull(map)) {
                                if (!Objects.isNull(map.get(item.getAbilityIdentifier()))) {
                                    if ((!Objects.isNull(abilityIdentifierList) && abilityIdentifierList.contains(item.getAbilityIdentifier())) || StringUtils.isBlank(byteVParamsQry.getAbilityIdentifier())) {
                                        HashMap<String, String> hashMap = new HashMap<>();
                                        String s = specFactory.specValueShow(item.getDataType(), item.getDataSpecs(), String.valueOf(map.get(item.getAbilityIdentifier())));
                                        String s1 = specFactory.specValue(item.getDataType(), item.getDataSpecs(), String.valueOf(map.get(item.getAbilityIdentifier())));
                                        hashMap.put("content", item.getAbilityName() + " " + s);
                                        hashMap.put("name", item.getAbilityName());
                                        hashMap.put("value", s1);
                                        hashMap.put("time", (String) map.get("createTime"));

                                        list.add(hashMap);
                                    }
                                }
                            }
                        }
                    }
                }

            }

        } catch (Exception e) {
        }
        log.info("【bytev数据对接模块】设备下所有功能的最新数据列表-结果，byteVParamsQry：{}，list：{}", byteVParamsQry, list);

        return list;
    }

    @Override
    public List multifunctionalLastestTwentyData(ByteVParamsQry byteVParamsQry) {
        log.info("【bytev数据对接模块】指定多设备多功能的最新20条数据-参数，byteVParamsQry：{}", byteVParamsQry);

        List<Map<String, String>> list = new ArrayList<>();

        try {
            byteVParamsQry = verifyByteVParamsQry(byteVParamsQry);
            Products productsParam = new Products();
            productsParam.setProductKey(byteVParamsQry.getProductKey());
            productsParam.setCreateUserId(-1);
            Optional<Products> products = Optional.ofNullable(productService.checkExists(productsParam));
            if (products.isPresent()) {
                Integer productId = products.get().getId();

                if (StringUtils.isNotBlank(byteVParamsQry.getDeviceKey())) {
                    String[] split = byteVParamsQry.getDeviceKey().split(",");
                    for (String s : split) {
                        Devices devicesParam = new Devices();
                        devicesParam.setProductId(productId);
                        devicesParam.setDeviceKey(s);
                        devicesParam.setCreateUserId(-1);
                        Optional<Devices> devices = Optional.ofNullable(deviceService.checkExists(devicesParam));
                        if (devices.isPresent()) {
                            ProductModuleAbilityDataQry productModuleAbilityDataQry = new ProductModuleAbilityDataQry();
                            productModuleAbilityDataQry.setIndexName(devices.get().getProductKey() + "." + devices.get().getDeviceKey());
                            productModuleAbilityDataQry.setTimeType(-1);
                            List<MessageDto> allList = deviceEsService.searchAllAbilityData(productModuleAbilityDataQry);

                            log.info("【bytev数据对接模块】指定多设备多功能的最新20条数据-数据库查询结果，productModuleAbilityDataQry：{}，result：{}", productModuleAbilityDataQry, allList);

                            if (StringUtils.isNotBlank(byteVParamsQry.getAbilityIdentifier()) && !Objects.isNull(allList)) {

                                // 组装产品模型功能的查询条件
                                Example example = new Example(ProductModuleAbilitys.class);
                                Example.Criteria criteria = example.createCriteria();
                                criteria.andEqualTo("productId", productId);
                                criteria.andEqualTo("isDel", DeleteEnums.UNDELETED.code);
                                criteria.andIn("abilityIdentifier", Arrays.asList(byteVParamsQry.getAbilityIdentifier().split(",")));

                                Optional<List<ProductModuleAbilitys>> select = Optional.ofNullable(productModuleAbilitysMapper.selectByExample(example));
                                log.info("【bytev数据对接模块】指定多设备多功能的最新20条数据-查询数据库结果，criteria：{}，result：{}", JSON.toJSONString(criteria), select);
                                if (select.isPresent()) {
                                    // 组装返回数据
                                    allList.forEach(item1 -> {
                                        DeviceMessageDto deviceMessageDto = JSONObject.parseObject(item1.getMessage(), DeviceMessageDto.class);
                                        JSONObject jsonObject = JSONObject.parseObject(deviceMessageDto.getParams());
                                        for (ProductModuleAbilitys item : select.get()) {

                                            HashMap<String, String> hashMap = new HashMap<>();
                                            Date createTime = item1.getCreateTime();
                                            // hashMap.put("x", String.valueOf(createTime.getTime() / 1000));
                                            hashMap.put("x", "");
                                            String string = jsonObject.getString(item.getAbilityIdentifier());
                                            hashMap.put("y", StringUtils.isNotBlank(string) ? string : "0");
                                            if (split.length > 1) {
                                                hashMap.put("series", devices.get().getDeviceName() + " " + item.getAbilityName());
                                            } else {
                                                hashMap.put("series", item.getAbilityName());
                                            }

                                            list.add(hashMap);
                                        }
                                    });
                                }
                            }
                        }

                    }
                }

            }
        } catch (Exception e) {
        }
        log.info("【bytev数据对接模块】指定多个功能的最新20条数据-结果，byteVParamsQry：{}，list：{}", byteVParamsQry, list);

        return list;
    }

    /**
     * 校验输入
     *
     * @param byteVParamsQry
     * @return
     */
    public ByteVParamsQry verifyByteVParamsQry(ByteVParamsQry byteVParamsQry) {
        if (StringUtils.isNotBlank(byteVParamsQry.getProductKey())) {
            byteVParamsQry.setProductKey(byteVParamsQry.getProductKey().replaceAll("\\s*", ""));
        }
        if (StringUtils.isNotBlank(byteVParamsQry.getDeviceKey())) {
            byteVParamsQry.setDeviceKey(byteVParamsQry.getDeviceKey().replaceAll("\\s*", ""));
        }
        if (StringUtils.isNotBlank(byteVParamsQry.getAbilityIdentifier())) {
            byteVParamsQry.setAbilityIdentifier(byteVParamsQry.getAbilityIdentifier().replaceAll("\\s*", ""));
        }
        return byteVParamsQry;
    }
}
