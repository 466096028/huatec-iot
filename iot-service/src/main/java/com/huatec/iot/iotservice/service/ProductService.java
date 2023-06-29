package com.huatec.iot.iotservice.service;

import com.huatec.iot.common.entity.Products;
import com.huatec.iot.iotservice.pojo.dto.ProductsDto;
import com.huatec.iot.iotservice.pojo.qry.ProductsPageQry;
import com.huatec.iot.iotservice.pojo.vo.ProductDeviceCountVo;
import com.huatec.iot.iotservice.pojo.vo.ProductsVo;
import com.huatec.iot.iotservice.pojo.vo.ThingModelVo;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
public interface ProductService {
    /**
     * 获取分页列表
     * @param productsPageQry
     * @return
     */
    List<ProductsVo> getPageList(ProductsPageQry productsPageQry);

    /**
     * 获取所有列表
     * @return
     */
    List<ProductsVo> allList();

    /**
     * 获取详情
     * @param id
     * @return
     */
    ProductsVo getInfo(Integer id);

    /**
     * 删除数据
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 添加数据
     * @param productsDto
     */
    Products insert(ProductsDto productsDto);

    /**
     * 修改数据
     * @param productsDto
     */
    void update(ProductsDto productsDto);

    /**
     * 获取设备数
     * @return
     */
    ProductDeviceCountVo getDeviceCount(Integer productId);

    /**
     * 获取物模型TSL
     * @param id
     * @param moduleId
     * @return
     */
    ThingModelVo getThingModel(Integer id, Integer moduleId);

    Products getInfoById(Integer productId);

    Products checkExists(Products products);
}
