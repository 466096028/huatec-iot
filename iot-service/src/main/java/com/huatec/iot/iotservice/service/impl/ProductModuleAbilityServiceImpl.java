package com.huatec.iot.iotservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.huatec.iot.common.domain.spec.SpecFactory;
import com.huatec.iot.common.entity.Devices;
import com.huatec.iot.common.entity.ProductModuleAbilitys;
import com.huatec.iot.common.entity.Products;
import com.huatec.iot.common.enums.*;
import com.huatec.iot.common.utils.UserUtils;
import com.huatec.iot.common.utils.VUtils;
import com.huatec.iot.iotservice.mapper.ProductModuleAbilitysMapper;
import com.huatec.iot.iotservice.pojo.dto.ProductModuleAbilitysDto;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityDataQry;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityQry;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilityDatasVo;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilitysVo;
import com.huatec.iot.iotservice.service.DeviceEsService;
import com.huatec.iot.iotservice.service.DeviceService;
import com.huatec.iot.iotservice.service.ProductModuleAbilityService;
import com.huatec.iot.iotservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
public class ProductModuleAbilityServiceImpl implements ProductModuleAbilityService {

    @Autowired
    ProductModuleAbilitysMapper productModuleAbilitysMapper;

    @Autowired
    SpecFactory specFactory;

    @Autowired
    DeviceEsService deviceEsService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    ProductService productService;

    @Override
    public List<ProductModuleAbilitysVo> userModuleList(ProductModuleAbilityQry productModuleAbilityQry) {
        Integer userId = UserUtils.getUserInfo().getUserId();
        log.info("【产品模型功能模块】查询用户产品模型功能列表-参数，productModuleAbilityQry：{}，userId：{}", productModuleAbilityQry, userId);

        // 组装产品模型功能的查询条件
        Example example = new Example(ProductModuleAbilitys.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("moduleId", productModuleAbilityQry.getModuleId());
        criteria.andEqualTo("productId", productModuleAbilityQry.getProductId());
        criteria.andEqualTo("createUserId", userId);
        criteria.andEqualTo("isDel", DeleteEnums.UNDELETED.code);

        List<ProductModuleAbilitysVo> productModuleAbilitysVos = new ArrayList<>();
        Optional<List<ProductModuleAbilitys>> select = Optional.ofNullable(productModuleAbilitysMapper.selectByExample(example));
        log.info("【产品模型功能模块】查询用户产品模型功能列表-查询数据库结果，productModuleAbilityQry：{}，userId：{}，result：{}", productModuleAbilityQry, userId, select);

        Map<String, Object> map = new HashMap<>();
        // 获取设备详情数据
        if (productModuleAbilityQry.getDeviceId() != null && productModuleAbilityQry.getDeviceId() > 0) {
            Optional<Devices> devices = Optional.ofNullable(deviceService.getInfoById(productModuleAbilityQry.getDeviceId()));
            if (devices.isPresent()) {
                // 获取设备功能对应的最新一条数据
                map = deviceEsService.searchLatestData(devices.get().getProductKey() + "." + devices.get().getDeviceKey());
            }
        }

        if (select.isPresent()) {
            // 组装返回数据
            for (ProductModuleAbilitys item : select.get()) {
                ProductModuleAbilitysVo productModuleAbilitysVo = new ProductModuleAbilitysVo();
                BeanUtils.copyProperties(item, productModuleAbilitysVo);
                productModuleAbilitysVo.setAbilityTypeName(AbilityTypeEnums.getDesc(item.getAbilityType()));
                productModuleAbilitysVo.setDataTypeName(DataTypeEnums.getDesc(item.getDataType()));
                productModuleAbilitysVo.setDataSpecs(specFactory.specTransition(item.getDataType(), item.getDataSpecs()));
                if (!Objects.isNull(map)) {
                    productModuleAbilitysVo.setLatestData("");
                    if (!Objects.isNull(map.get(item.getAbilityIdentifier()))) {
                        productModuleAbilitysVo.setLatestData(specFactory.specValue(item.getDataType(), item.getDataSpecs(), String.valueOf(map.get(item.getAbilityIdentifier()))));
                    }

                    productModuleAbilitysVo.setLatestDataCreateDate(String.valueOf(Objects.isNull(map.get("createTime")) ? "" : map.get("createTime")));
                }
                productModuleAbilitysVos.add(productModuleAbilitysVo);
            }
        }

        log.info("【产品模型功能模块】查询用户产品模型功能列表-结果，productModuleAbilityQry：{}，userId：{}，productModuleAbilitysVos：{}", productModuleAbilityQry, userId, productModuleAbilitysVos);
        return productModuleAbilitysVos;
    }

    @Override
    public void batchInsert(List<ProductModuleAbilitys> productModuleAbilitys) {
        int i = productModuleAbilitysMapper.insertList(productModuleAbilitys);
        log.info("【产品模型功能模块】批量添加-参数，params：{}，status：{}", JSON.toJSONString(productModuleAbilitys), i);
    }


    @Override
    public void deleteByProductId(Integer productId) {
        Integer userId = UserUtils.getUserInfo().getUserId();
        log.info("【产品模型功能模块】通过用户ID删除数据-参数，productId：{}，userId：{}", productId, userId);
        Example example = new Example(ProductModuleAbilitys.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("createUserId", userId);

        int i = productModuleAbilitysMapper.deleteByExample(example);
        log.info("【产品模型功能模块】通过用户ID删除数据-结果，params：{}，status：{}", JSON.toJSONString(criteria), i);
    }

    @Override
    public List<ProductModuleAbilityDatasVo> getDataList(ProductModuleAbilityDataQry productModuleAbilityDataQry) {
        log.info("【产品模型功能模块】获取产品模型功能数据列表-参数，productModuleAbilityDataPageQry：{}", productModuleAbilityDataQry);
        Optional<Devices> devices = Optional.ofNullable(deviceService.getInfoById(productModuleAbilityDataQry.getDeviceId()));
        List<ProductModuleAbilityDatasVo> productModuleAbilityDatasVos = new ArrayList<>();
        if (devices.isPresent()) {
            productModuleAbilityDataQry.setIndexName(devices.get().getProductKey() + "." + devices.get().getDeviceKey());
            productModuleAbilityDatasVos = deviceEsService.searchData(productModuleAbilityDataQry);
        } else {
            log.info("【产品模型功能模块】获取产品模型功能数据列表-参数，productModuleAbilityDataPageQry：{}，error：{}", productModuleAbilityDataQry, "设备不存在");
        }

        return productModuleAbilityDatasVos;
    }

    @Override
    public List<ProductModuleAbilitys> getList(ProductModuleAbilityQry productModuleAbilityQry) {
        Integer userId = UserUtils.getUserInfo().getUserId();
        log.info("【产品模型功能模块】查询模型功能列表-参数，productModuleAbilityQry：{}，userId：{}", productModuleAbilityQry, userId);

        Example example = new Example(ProductModuleAbilitys.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("moduleId", productModuleAbilityQry.getModuleId());
        criteria.andEqualTo("productId", productModuleAbilityQry.getProductId());
        criteria.andEqualTo("createUserId", userId);
        criteria.andEqualTo("isDel", DeleteEnums.UNDELETED.code);

        List<ProductModuleAbilitys> productModuleAbilitys = new ArrayList<>();
        Optional<List<ProductModuleAbilitys>> select = Optional.ofNullable(productModuleAbilitysMapper.selectByExample(example));
        if (select.isPresent()) {
            productModuleAbilitys = select.get();
        }
        log.info("【产品模型功能模块】查询模型功能列表-查询数据库结果，productModuleAbilityQry：{}，userId：{}，result：{}", productModuleAbilityQry, userId, productModuleAbilitys);

        return productModuleAbilitys;
    }

    @Override
    public void insert(ProductModuleAbilitysDto productModuleAbilitysDto) {
        // 查询产品是否存在
        Optional<Products> proInfo = Optional.ofNullable(productService.getInfoById(productModuleAbilitysDto.getProductId()));
        VUtils.isTure(!proInfo.isPresent()).throwMessage("产品不存在");

        // 查询key是否重复
        ProductModuleAbilitys pma = new ProductModuleAbilitys();
        pma.setAbilityIdentifier(productModuleAbilitysDto.getAbilityIdentifier());
        pma.setProductId(productModuleAbilitysDto.getProductId());
        Optional<ProductModuleAbilitys> pmaCheck = Optional.ofNullable(checkExists(pma));
        if (pmaCheck.isPresent()) {
            VUtils.isTure(true).throwMessage("存在重复的功能标识符");
        }

        ProductModuleAbilitys productModuleAbilitys = new ProductModuleAbilitys();
        BeanUtils.copyProperties(productModuleAbilitysDto, productModuleAbilitys);
        productModuleAbilitys.setCreateUserId(UserUtils.getUserInfo().getUserId());
        log.info("【产品模型功能模块】添加数据-参数：params：{}", productModuleAbilitys);
        specFactory.checkSpec(productModuleAbilitys.getDataType(), productModuleAbilitys.getDataSpecs());

        int insert = 0;
        try {
            insert = productModuleAbilitysMapper.insertSelective(productModuleAbilitys);
            log.info("【产品模型功能模块】添加数据-结果：result：{}", insert);
        } catch (Exception e) {
        }

        VUtils.isTure(insert == 0).throwMessage("添加失败");
    }

    @Override
    public void update(ProductModuleAbilitysDto productModuleAbilitysDto) {

        // 查询key是否重复
        ProductModuleAbilitys pma = new ProductModuleAbilitys();
        pma.setAbilityIdentifier(productModuleAbilitysDto.getAbilityIdentifier());
        pma.setProductId(productModuleAbilitysDto.getProductId());
        Optional<ProductModuleAbilitys> pmaCheck = Optional.ofNullable(checkExists(pma));
        if (pmaCheck.isPresent()) {
            VUtils.isTure(!pmaCheck.get().getId().equals(productModuleAbilitysDto.getId())).throwMessage("存在重复的功能标识符");
        }

        Example example = new Example(ProductModuleAbilitys.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", productModuleAbilitysDto.getId());
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());
        log.info("【产品模型功能模块】修改数据-参数，params：{}", JSON.toJSONString(criteria));

        ProductModuleAbilitys productModuleAbilitys = new ProductModuleAbilitys();
        BeanUtils.copyProperties(productModuleAbilitysDto, productModuleAbilitys);
        specFactory.checkSpec(productModuleAbilitys.getDataType(), productModuleAbilitys.getDataSpecs());

        int update = 0;
        try {
            update = productModuleAbilitysMapper.updateByExampleSelective(productModuleAbilitys, example);
            log.info("【产品模型功能模块】修改数据-结果，result：{}", update);
        } catch (Exception e) {
        }

        VUtils.isTure(update == 0).throwMessage("修改失败");
    }

    @Override
    public ProductModuleAbilitys checkExists(ProductModuleAbilitys productModuleAbilitys) {
        log.info("【产品模型功能模块】检查模型功能数据是否存在-参数：params：{}", productModuleAbilitys);

        try {
            productModuleAbilitys.setCreateUserId(UserUtils.getUserInfo().getUserId());
            Optional<ProductModuleAbilitys> productModuleAbilitys1 = Optional.ofNullable(productModuleAbilitysMapper.selectOne(productModuleAbilitys));
            if (productModuleAbilitys1.isPresent()) {
                log.info("产品模型功能模块】检查模型功能数据是否存在-结果：params：{}，result：{}", productModuleAbilitys1.get());
                return productModuleAbilitys1.get();
            } else {
                log.info("产品模型功能模块】检查模型功能数据是否存在-结果：params：{}，result：{}", "没有找到设备");
            }

        } catch (Exception e) {
            log.error("产品模型功能模块】检查模型功能数据是否存在-异常：exception：{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer getCount(ProductModuleAbilitys productModuleAbilitys) {
        log.info("【产品模型功能模块】查询数量-参数：params：{}", productModuleAbilitys);

        try {
            if (productModuleAbilitys.getCreateUserId() == null || productModuleAbilitys.getCreateUserId() == 0) {
                productModuleAbilitys.setCreateUserId(UserUtils.getUserInfo().getUserId());
            }

            Optional<Integer> integer = Optional.ofNullable(productModuleAbilitysMapper.selectCount(productModuleAbilitys));
            if (integer.isPresent()) {
                log.info("产品模型功能模块】查询数量-结果：params：{}，result：{}", integer.get());
                return integer.get().intValue();
            } else {
                log.info("产品模型功能模块】查询数量-结果：params：{}，result：{}", "没有找到设备");
            }
        } catch (Exception e) {
            log.error("产品模型功能模块】查询数量-异常：exception：{}", e.getMessage());
        }
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        Example example = new Example(ProductModuleAbilitys.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());

        int i = productModuleAbilitysMapper.deleteByExample(example);
        log.info("【产品模型功能模块】删除数据，params：{}，result：{}", JSON.toJSONString(criteria), i);

        VUtils.isTure(i == 0).throwMessage("删除失败");
    }

    @Override
    public ProductModuleAbilitysVo getInfo(Integer id) {
        Example example = new Example(ProductModuleAbilitys.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("createUserId", UserUtils.getUserInfo().getUserId());

        ProductModuleAbilitys productModuleAbilitys = productModuleAbilitysMapper.selectOneByExample(example);
        log.info("【产品模型功能模块】获取详情-查询一条数据库数据，params：{}，result：{}", JSON.toJSONString(criteria), productModuleAbilitys);
        ProductModuleAbilitysVo productModuleAbilitysVo = null;
        if (!Objects.isNull(productModuleAbilitys)) {
            productModuleAbilitysVo = new ProductModuleAbilitysVo();
            BeanUtils.copyProperties(productModuleAbilitys, productModuleAbilitysVo);
            productModuleAbilitysVo.setAbilityTypeName(AbilityTypeEnums.getDesc(productModuleAbilitys.getAbilityType()));
            productModuleAbilitysVo.setDataTypeName(DataTypeEnums.getDesc(productModuleAbilitys.getDataType()));

            productModuleAbilitysVo.setDataSpecs(specFactory.specTransition(productModuleAbilitys.getDataType(), productModuleAbilitys.getDataSpecs()));
        }
        return productModuleAbilitysVo;
    }
}
