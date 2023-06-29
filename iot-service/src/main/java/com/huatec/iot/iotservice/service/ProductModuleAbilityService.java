package com.huatec.iot.iotservice.service;

import com.huatec.iot.common.entity.ProductModuleAbilitys;
import com.huatec.iot.iotservice.pojo.dto.ProductModuleAbilitysDto;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityDataQry;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityQry;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilityDatasVo;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilitysVo;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
public interface ProductModuleAbilityService {

    /**
     * 查询用户产品模型功能列表
     * @param productModuleAbilityQry
     * @return
     */
    List<ProductModuleAbilitysVo> userModuleList(ProductModuleAbilityQry productModuleAbilityQry);

    /**
     * 批量添加
     * @param productModuleAbilitys
     */
    void batchInsert(List<ProductModuleAbilitys> productModuleAbilitys);

    /**
     * 删除用户某产品功能数据
     * @param productId
     */
    void deleteByProductId(Integer productId);

    /**
     * 获取产品模型功能数据列表
     * @param productModuleAbilityDataQry
     * @return
     */
    List<ProductModuleAbilityDatasVo> getDataList(ProductModuleAbilityDataQry productModuleAbilityDataQry);

    /**
     * 查询用户产品模型功能列表
     * @param productModuleAbilityQry
     * @return
     */
    List<ProductModuleAbilitys> getList(ProductModuleAbilityQry productModuleAbilityQry);

    /**
     * 添加
     * @param productModuleAbilitysDto
     */
    void insert(ProductModuleAbilitysDto productModuleAbilitysDto);

    /**
     * 修改
     * @param productModuleAbilitysDto
     */
    void update(ProductModuleAbilitysDto productModuleAbilitysDto);

    /**
     * 检查某条数据是否存在
     * @param productModuleAbilitys
     * @return
     */
    ProductModuleAbilitys checkExists(ProductModuleAbilitys productModuleAbilitys);

    /**
     * 删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 获取详情
     * @param id
     * @return
     */
    ProductModuleAbilitysVo getInfo(Integer id);

    Integer getCount(ProductModuleAbilitys productModuleAbilitys1);
}
