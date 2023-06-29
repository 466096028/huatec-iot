package com.huatec.iot.iotservice.service.impl;

import com.huatec.iot.common.constants.CommonConstants;
import com.huatec.iot.common.domain.spec.SpecFactory;
import com.huatec.iot.common.entity.CategoryAbilitys;
import com.huatec.iot.common.enums.DataTypeEnums;
import com.huatec.iot.iotservice.mapper.CategoryAbilitysMapper;
import com.huatec.iot.iotservice.pojo.qry.CategoryAbilitysQry;
import com.huatec.iot.iotservice.pojo.vo.CategoryAbilitysVo;
import com.huatec.iot.iotservice.service.CategoryAbilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 21:55
 **/

@Service
@Slf4j
public class CategoryAbilityServiceImpl implements CategoryAbilityService {

    @Autowired
    CategoryAbilitysMapper categoryAbilitysMapper;

    @Autowired
    SpecFactory specFactory;

    @Override
    public List<CategoryAbilitysVo> allList(CategoryAbilitysQry categoryAbilitysQry) {
        CategoryAbilitys categoryAbilitys = new CategoryAbilitys();
        BeanUtils.copyProperties(categoryAbilitysQry, categoryAbilitys);
        categoryAbilitys.setIsDel(CommonConstants.DELETE_0);

        List<CategoryAbilitys> select = categoryAbilitysMapper.select(categoryAbilitys);
        log.info("【分类功能模块】查询数据库所有数据-结果：{}", select);

        List<CategoryAbilitysVo> list = select.stream().map(info -> {
            CategoryAbilitysVo categoryAbilitysVo = new CategoryAbilitysVo();
            BeanUtils.copyProperties(info, categoryAbilitysVo);
            categoryAbilitysVo.setDataTypeName(DataTypeEnums.getDesc(categoryAbilitysVo.getDataType()));
            String dataType = info.getDataType();
            categoryAbilitysVo.setDataSpecs(specFactory.specTransition(dataType, info.getDataSpecs()));

            return categoryAbilitysVo;
        }).collect(Collectors.toList());
        log.info("【分类功能模块】查询所有数据-结果：{}", list);

        return list;
    }

    /**
     * 通过分类获取功能列表
     * @param id
     * @return
     */
    @Override
    public List<CategoryAbilitys> getListByCategoryId(Integer id) {
        CategoryAbilitys categoryAbilitys = new CategoryAbilitys();
        categoryAbilitys.setCategoryId(id);
        List<CategoryAbilitys> select = categoryAbilitysMapper.select(categoryAbilitys);

        return select;
    }
}
