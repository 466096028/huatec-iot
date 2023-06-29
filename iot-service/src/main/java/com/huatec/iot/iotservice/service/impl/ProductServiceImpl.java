package com.huatec.iot.iotservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.huatec.iot.common.domain.spec.SpecFactory;
import com.huatec.iot.common.entity.*;
import com.huatec.iot.common.enums.*;
import com.huatec.iot.common.utils.UserUtils;
import com.huatec.iot.common.utils.UuidUtils;
import com.huatec.iot.common.utils.VUtils;
import com.huatec.iot.iotservice.mapper.CategorysMapper;
import com.huatec.iot.iotservice.mapper.DevicesMapper;
import com.huatec.iot.iotservice.mapper.ProductsMapper;
import com.huatec.iot.iotservice.pojo.dto.ProductsDto;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityQry;
import com.huatec.iot.iotservice.pojo.qry.ProductsPageQry;
import com.huatec.iot.iotservice.pojo.vo.ProductDeviceCountVo;
import com.huatec.iot.iotservice.pojo.vo.ProductsVo;
import com.huatec.iot.iotservice.pojo.vo.ThingModelVo;
import com.huatec.iot.iotservice.service.CategoryAbilityService;
import com.huatec.iot.iotservice.service.ProductModuleAbilityService;
import com.huatec.iot.iotservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductsMapper productsMapper;

    @Autowired
    DevicesMapper devicesMapper;

    @Autowired
    CategorysMapper categorysMapper;

    @Autowired
    CategoryAbilityService categoryAbilityService;

    @Autowired
    ProductModuleAbilityService productModuleAbilityService;

    @Autowired
    SpecFactory specFactory;


    @Override
    public List<ProductsVo> getPageList(ProductsPageQry productsPageQry) {
        Example example = new Example(Products.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id desc");
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());
        if (StringUtils.isNotBlank(productsPageQry.getProductName())) {
            criteria.andLike("productName", "%" + productsPageQry.getProductName() + "%");
        }

        List<Products> select = productsMapper.selectByExample(example);
        log.info("【产品模块】分页查询数据库-参数，params：{}，result：{}", JSON.toJSONString(criteria), select);

        List<ProductsVo> list = select.stream().map(info -> {
            ProductsVo productsVo = new ProductsVo();
            BeanUtils.copyProperties(info, productsVo);
            productsVo.setNodeTypeName(NodeTypeEnums.getDesc(productsVo.getNodeType()));
            productsVo.setNetTypeName(NetTypeEnums.getDesc(productsVo.getNetType()));

            return productsVo;
        }).collect(Collectors.toList());
        log.info("【产品模块】分页查询-结果，list：{}", list);

        return list;
    }

    @Override
    public List<ProductsVo> allList() {
        Products products = new Products();
        products.setStatus((byte) StatusEnums.ENABLE_STATUS.getCode());
        products.setCreateUserId(UserUtils.getUserInfo().getUserId());
        List<Products> select = productsMapper.select(products);
        log.info("【产品模块】查询数据库所有数据-结果，params：{}，result：{}", products, select);

        List<ProductsVo> list = select.stream().map(info -> {
            ProductsVo productsVo = new ProductsVo();
            BeanUtils.copyProperties(info, productsVo);
            productsVo.setNodeTypeName(NodeTypeEnums.getDesc(productsVo.getNodeType()));
            productsVo.setNetTypeName(NetTypeEnums.getDesc(productsVo.getNetType()));
            return productsVo;
        }).collect(Collectors.toList());
        log.info("【产品模块】查询所有数据-结果，result{}", list);

        return list;
    }

    @Override
    public ProductsVo getInfo(Integer id) {
        Integer userId = UserUtils.getUserInfo().getUserId();
        Example example = new Example(Products.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("createUserId", userId);

        Products products = productsMapper.selectOneByExample(example);
        log.info("【产品模块】查询一条数据库数据-参数，params：{}，result：{}", JSON.toJSONString(criteria), products);

        ProductsVo productsVo = null;
        if (!Objects.isNull(products)) {
            productsVo = new ProductsVo();
            BeanUtils.copyProperties(products, productsVo);
            productsVo.setNodeTypeName(NodeTypeEnums.getDesc(productsVo.getNodeType()));
            productsVo.setNetTypeName(NetTypeEnums.getDesc(productsVo.getNetType()));
            productsVo.setDataFormatName(DataFormatEnums.getDesc(productsVo.getDataFormat()));

            Example example1 = new Example(Devices.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("productId", id);
            criteria1.andEqualTo("createUserId", userId);

            int count = devicesMapper.selectCountByExample(example1);
            productsVo.setDeviceCount(count);
        }
        return productsVo;
    }

    @Override
    public void deleteById(Integer id) {

        ProductDeviceCountVo deviceCount = getDeviceCount(id);
        log.info("【产品模块】删除数据，params：{}，result：{}", id, "产品下存在设备，不能删除");
        VUtils.isTure(deviceCount.getDeviceTotal() > 0).throwMessage("产品下存在设备，不能删除");

        Example example = new Example(Products.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());

        Optional<Products> products = Optional.ofNullable(productsMapper.selectOneByExample(example));
        if (products.isPresent()) {
            VUtils.isTure(products.get().getIsSystem() != null && products.get().getIsSystem() == 1).throwMessage("系统添加的产品不能删除");
        }

        int i = productsMapper.deleteByExample(example);
        log.info("【产品模块】删除数据，params：{}，result：{}", JSON.toJSONString(criteria), i);

        productModuleAbilityService.deleteByProductId(id);
        log.info("【产品模块】删除模块功能数据，productId：{}，userId：{}", id, UserUtils.getUserInfo().getUserId());

        VUtils.isTure(i == 0).throwMessage("删除失败");
    }

    @Override
    public Products insert(ProductsDto productsDto) {
        Optional<Categorys> categorys = Optional.ofNullable(categorysMapper.selectByPrimaryKey(productsDto.getCategoryId()));
        VUtils.isTure(!categorys.isPresent()).throwMessage("分类不存在");

        Products products = new Products();
        BeanUtils.copyProperties(productsDto, products);
        products.setCategoryKey(categorys.get().getCategoryKey());
        products.setCategoryName(categorys.get().getCategoryName());
        products.setProductKey(UuidUtils.generateProductId());
        products.setDataFormat((byte) DataFormatEnums.FORMAT_JSON.getCode());

        if (products.getCreateUserId() == null || products.getCreateUserId() <= 0) {
            products.setCreateUserId(UserUtils.getUserInfo().getUserId());
        }
        Integer userId = products.getCreateUserId();

        Products checkData = new Products();
        checkData.setProductName(productsDto.getProductName());
        checkData.setCreateUserId(productsDto.getCreateUserId());
        Optional<Products> checkResult = Optional.ofNullable(checkExists(checkData));
        VUtils.isTure(checkResult.isPresent()).throwMessage("产品名称已存在");


        log.info("【产品模块】添加数据-参数：params：{}", products);

        int insert = 0;
        try {
            insert = productsMapper.insertSelective(products);
            log.info("【产品模块】添加数据-结果：result：{}", insert);
            if (insert > 0) {
                // 产品添加成功能，给产品创建一个默认的模型功能列表
                Optional<List<CategoryAbilitys>> listByCategoryId = Optional.ofNullable(categoryAbilityService.getListByCategoryId(productsDto.getCategoryId()));
                log.info("【产品模块】查询分类功能列表-参数，categoryId：{}，result：{}", productsDto.getCategoryId(), listByCategoryId);

                if (listByCategoryId.isPresent()) {
                    // 组装模型功能数据
                    List<ProductModuleAbilitys> ProductModuleAbilitysList = new ArrayList();
                    for (CategoryAbilitys categoryAbilitys : listByCategoryId.get()) {
                        ProductModuleAbilitys productModuleAbilitys = new ProductModuleAbilitys();
                        BeanUtils.copyProperties(categoryAbilitys, productModuleAbilitys);
                        productModuleAbilitys.setId(null);
                        productModuleAbilitys.setModuleId(0);
                        productModuleAbilitys.setIsRequired((byte) 1);

                        productModuleAbilitys.setCreateUserId(userId);
                        productModuleAbilitys.setProductId(products.getId());
                        ProductModuleAbilitysList.add(productModuleAbilitys);
                    }

                    productModuleAbilityService.batchInsert(ProductModuleAbilitysList);
                }
            }
        } catch (Exception e) {
        }
        VUtils.isTure(insert == 0).throwMessage("添加失败");

        return products;
    }

    @Override
    public void update(ProductsDto productsDto) {
        Products checkData = new Products();
        checkData.setProductName(productsDto.getProductName());
        Optional<Products> checkResult = Optional.ofNullable(checkExists(checkData));
        if (checkResult.isPresent()) {
            VUtils.isTure(!productsDto.getId().equals(checkResult.get().getId())).throwMessage("产品名称已存在");
        }

        Example example = new Example(Products.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", productsDto.getId());
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());
        log.info("【产品模块】修改数据-参数，params：{}", JSON.toJSONString(criteria));

        Products products = new Products();
        BeanUtils.copyProperties(productsDto, products);
        products.setCategoryId(null);
        int update = 0;
        try {
            update = productsMapper.updateByExampleSelective(products, example);
            log.info("【产品模块】修改数据-结果，result：{}", update);
        } catch (Exception e) {
        }

        VUtils.isTure(update == 0).throwMessage("修改失败");
    }

    @Override
    public ProductDeviceCountVo getDeviceCount(Integer productId) {
        Integer userId = UserUtils.getUserInfo().getUserId();
        Devices devices = new Devices();
        devices.setCreateUserId(userId);
        if (productId != null && productId > 0) {
            devices.setProductId(productId);
        }
        log.info("【产品模块】查询产品下的设备数-参数，params：{}", devices);

        ProductDeviceCountVo productDeviceCountVo = new ProductDeviceCountVo();
        List<Devices> devices1 = devicesMapper.select(devices);
        if (!Objects.isNull(devices1)) {
            devices1.stream().forEach(info -> {
                productDeviceCountVo.setDeviceTotal(productDeviceCountVo.getDeviceTotal() + 1);
                if (info.getDeviceStatus() > 0) {
                    productDeviceCountVo.setActivityDeviceCount(productDeviceCountVo.getActivityDeviceCount() + 1);
                }
                if (info.getDeviceStatus() == DeviceStatusEnums.ACTIVATED_ONLINE_STATUS.code) {
                    productDeviceCountVo.setOnLineDeviceCount(productDeviceCountVo.getOnLineDeviceCount() + 1);
                }
            });
        }
        log.info("【产品模块】查询产品下的设备数-结果，result：{}", productDeviceCountVo);
        return productDeviceCountVo;
    }

    @Override
    public ThingModelVo getThingModel(Integer id, Integer moduleId) {
        Integer userId = UserUtils.getUserInfo().getUserId();
        Example example = new Example(Products.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("createUserId", userId);

        // 查询产品数据
        Optional<Products> products = Optional.ofNullable(productsMapper.selectOneByExample(example));
        log.info("【产品模块】查询物模型TSL-检索数据库，params：{}，result：{}", JSON.toJSONString(criteria), products);

        // 定义物模型格式数据
        ThingModelVo thingModelVo = new ThingModelVo();
        if (products.isPresent()) {
            // 组装模块功能查询条件
            ProductModuleAbilityQry productModuleAbilityQry = new ProductModuleAbilityQry();
            productModuleAbilityQry.setProductId(id);
            productModuleAbilityQry.setModuleId(moduleId);

            // 查询模块下的功能列表
            Optional<List<ProductModuleAbilitys>> list = Optional.ofNullable(productModuleAbilityService.getList(productModuleAbilityQry));

            ThingModelVo.Profile profile = new ThingModelVo.Profile();
            profile.setProductKey(products.get().getProductKey());
            profile.setVersion("1.0");
            thingModelVo.setProfile(profile);

            if (list.isPresent()) {
                ArrayList<ThingModelVo.Properties> propertiesList = new ArrayList();

                // 循环模型功能列表
                list.get().forEach(item -> {
                    ThingModelVo.Properties properties = new ThingModelVo.Properties();
                    properties.setIdentifier(item.getAbilityIdentifier());
                    properties.setName(item.getAbilityName());
                    properties.setRequired(item.getIsRequired() == 1 ? true : false);

                    ThingModelVo.DataType dataType = new ThingModelVo.DataType();
                    dataType.setType(item.getDataType());
                    // 将规格的值进行转换
                    Object o = specFactory.specValueTransition(item.getDataType(), item.getDataSpecs());
                    dataType.setSpecs(o);
                    properties.setDataType(dataType);
                    propertiesList.add(properties);
                });
                thingModelVo.setProperties(propertiesList);
            }
        }

        return thingModelVo;
    }

    @Override
    public Products getInfoById(Integer productId) {
        Integer userId = UserUtils.getUserInfo().getUserId();
        Example example = new Example(Products.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", productId);
        criteria.andEqualTo("createUserId", userId);

        Products products = productsMapper.selectOneByExample(example);
        log.info("【产品模块】通过产品ID及用户ID查询一条数据库数据-参数，params：{}，result：{}", JSON.toJSONString(criteria), products);

        return products;
    }

    @Override
    public Products checkExists(Products products) {
        log.info("【产品模块】检查产品数据是否存在-参数：params：{}", products);

        try {
            if (products.getCreateUserId() != null && products.getCreateUserId() == -1) {
                products.setCreateUserId(null);
            }else if (products.getCreateUserId() == null || products.getCreateUserId() <= 0) {
                products.setCreateUserId(UserUtils.getUserInfo().getUserId());
            }

            Optional<Products> products1 = Optional.ofNullable(productsMapper.selectOne(products));
            if (products1.isPresent()) {
                log.info("【产品模块】检查产品数据是否存在-结果：params：{}，result：{}", products1.get());
                return products1.get();
            } else {
                log.info("【产品模块】检查产品数据是否存在-结果：params：{}，result：{}", "没有找到产品");
            }
        } catch (Exception e) {
            log.error("【产品模块】检查产品数据是否存在-异常：exception：{}", e.getMessage());
        }
        return null;
    }
}
