package com.huatec.iot.iotservice.service;

import com.huatec.iot.common.entity.CategoryAbilitys;
import com.huatec.iot.iotservice.pojo.qry.CategoryAbilitysQry;
import com.huatec.iot.iotservice.pojo.vo.CategoryAbilitysVo;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 21:55
 **/
public interface CategoryAbilityService {

    /**
     * 获取所有数据
     * @return
     */
    public List<CategoryAbilitysVo> allList(CategoryAbilitysQry categoryAbilitysQry);

    /**
     * 通过分类ID获取功能
     * @param id
     * @return
     */
    public List<CategoryAbilitys> getListByCategoryId(Integer id);
}
