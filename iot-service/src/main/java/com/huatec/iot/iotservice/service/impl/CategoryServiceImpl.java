package com.huatec.iot.iotservice.service.impl;

import com.huatec.iot.common.constants.CommonConstants;
import com.huatec.iot.common.entity.Categorys;
import com.huatec.iot.iotservice.mapper.CategorysMapper;
import com.huatec.iot.iotservice.pojo.qry.CategorysQry;
import com.huatec.iot.iotservice.pojo.vo.CategorysVo;
import com.huatec.iot.iotservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategorysMapper categorysMapper;

    @Override
    public List<CategorysVo> getPageList(CategorysQry categorysQry) {
        Categorys categorys = new Categorys();

        List<Categorys> select = categorysMapper.select(categorys);
        log.info("【分类模块】分页查询数据库-参数：{}，结果：{}", categorys, select);

        List<CategorysVo> list = select.stream().map(info -> {
            CategorysVo categorysVo = new CategorysVo();
            BeanUtils.copyProperties(info, categorysVo);
            return categorysVo;
        }).collect(Collectors.toList());
        log.info("【分类模块】分页查询-结果：{}", list);

        return list;
    }

    @Override
    public List<CategorysVo> allList() {
        Categorys categorys = new Categorys();
        categorys.setIsDel(CommonConstants.DELETE_0);
        List<Categorys> select = categorysMapper.select(categorys);
        log.info("【分类模块】查询数据库所有数据-结果：{}", select);

        List<CategorysVo> list = select.stream().map(info -> {
            CategorysVo categorysVo = new CategorysVo();
            BeanUtils.copyProperties(info, categorysVo);
            return categorysVo;
        }).collect(Collectors.toList());
        log.info("【分类模块】查询所有数据-结果：{}", list);

        return list;
    }

}
