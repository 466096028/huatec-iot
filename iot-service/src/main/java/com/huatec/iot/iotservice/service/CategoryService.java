package com.huatec.iot.iotservice.service;

import com.huatec.iot.iotservice.pojo.qry.CategorysQry;
import com.huatec.iot.iotservice.pojo.vo.CategorysVo;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
public interface CategoryService {

    /**
     * 获取分页列表
     *
     * @param categorysQry
     * @return
     */
    default List<CategorysVo> getPageList(CategorysQry categorysQry) {

        return null;
    }

    /**
     * 获取所有列表
     * @return
     */
    List<CategorysVo> allList();
}
