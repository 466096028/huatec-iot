package com.huatec.iot.iotservice.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huatec.iot.common.pojo.DeviceMessageDto;
import com.huatec.iot.common.pojo.MessageDto;
import com.huatec.iot.common.utils.DateUtils;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityDataQry;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilityDatasVo;
import com.huatec.iot.iotservice.service.DeviceEsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @program: huatec-iot-api
 * @description: 设备ES服务
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/

@Service
@Slf4j
public class DeviceEsServiceImpl implements DeviceEsService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public void insertData(String indexName, MessageDto messageDto) {

        log.info("【设备ES模块】添加数据-参数：indexName：{}，messageDto：{}", indexName, messageDto);
        try {
            if (StringUtils.isNotBlank(indexName)) {
                IndexRequest indexRequest = new IndexRequest(indexName);
                indexRequest.source(JSON.toJSONString(messageDto), XContentType.JSON);

                IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
                RestStatus status = index.status();
                log.info("【设备ES模块】添加数据-结果：indexName：{}，messageDto：{}，status：{}", indexName, messageDto, status);
            } else {
                log.info("【设备ES模块】添加数据-失败：indexName：{}，messageDto：{}, error：{}", indexName, messageDto, "索引名称为空");
            }
        } catch (Exception e) {
            log.error("【设备ES模块】添加数据-参数：indexName：{}，messageDto：{}, exception：{}", indexName, messageDto, e.getMessage());
        }
    }


    @Override
    public List<ProductModuleAbilityDatasVo> searchData(ProductModuleAbilityDataQry productModuleAbilityDataQry) {
        log.info("【设备ES模块】搜索数据-参数：productModuleAbilityDataPageQry：{}", productModuleAbilityDataQry);

        List<ProductModuleAbilityDatasVo> productModuleAbilityDatasVos = new ArrayList<>();
        try {
            // 组装ES查询数据
            if (StringUtils.isNotBlank(productModuleAbilityDataQry.getIndexName())) {
                SearchRequest searchRequest = new SearchRequest();
                searchRequest.indices(productModuleAbilityDataQry.getIndexName());
                // 构建dsl语句
                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                // 构建查询条件
                BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
                RangeQueryBuilder createTimeQueryBuilder = QueryBuilders.rangeQuery("createTime");
                if (productModuleAbilityDataQry.getTimeType() > 0) {
                    createTimeQueryBuilder.gte(DateUtils.getCurrentTime() - productModuleAbilityDataQry.getTimeType() * 1000).lt(DateUtils.getCurrentTime());
                } else {
                    if (productModuleAbilityDataQry.getStartDateTime() != null) {
                        createTimeQueryBuilder.gte(productModuleAbilityDataQry.getStartDateTime().getTime());
                    }
                    if (productModuleAbilityDataQry.getEndDateTime() != null) {
                        createTimeQueryBuilder.lt(productModuleAbilityDataQry.getEndDateTime().getTime());
                    }
                }
                boolQueryBuilder.filter(createTimeQueryBuilder);
                searchSourceBuilder.query(boolQueryBuilder);
                searchSourceBuilder.size(100);
                searchSourceBuilder.sort(new FieldSortBuilder("createTime").order(SortOrder.DESC));
                searchRequest.source(searchSourceBuilder);

                SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

                log.info("【设备ES模块】搜索数据-查询数据库：createTimeQueryBuilder：{}，result：{}", createTimeQueryBuilder, search);
                for (SearchHit hit : search.getHits().getHits()) {
                    String value = "0";
                    MessageDto messageDto = JSONObject.parseObject(hit.getSourceAsString(), MessageDto.class);
                    ProductModuleAbilityDatasVo productModuleAbilityDatasVo = new ProductModuleAbilityDatasVo();
                    if (messageDto.getCreateTime() != null) {
                        productModuleAbilityDatasVo.setKey(DateUtils.dateToString1(messageDto.getCreateTime()));
                    }

                    if (StringUtils.isNotBlank(messageDto.getMessage())) {
                        DeviceMessageDto deviceMessageDto = JSONObject.parseObject(messageDto.getMessage(), DeviceMessageDto.class);
                        JSONObject jsonObject = JSONObject.parseObject(deviceMessageDto.getParams());
                        value = jsonObject.getString(productModuleAbilityDataQry.getAbilityIdentifier());
                        if (value == null) {
                            value = "0";
                        }
                    }
                    productModuleAbilityDatasVo.setValue(value);
                    productModuleAbilityDatasVos.add(productModuleAbilityDatasVo);
                }
                log.info("【设备ES模块】搜索数据-成功：productModuleAbilityDataPageQry：{}，messageDto：{}，productModuleAbilityDatasVos：{}", productModuleAbilityDatasVos);
            } else {
                log.info("【设备ES模块】搜索数据-失败：productModuleAbilityDataPageQry：{}, error：{}", productModuleAbilityDataQry, "索引名称为空");
            }
        } catch (Exception e) {
            log.error("【设备ES模块】搜索数据-异常：productModuleAbilityDataPageQry：{}, exception：{}", productModuleAbilityDataQry, e.getMessage());
        }

        return productModuleAbilityDatasVos;
    }

    @Override
    public Map<String, Object> searchLatestData(String indexName) {
        log.info("【设备ES模块】搜索最新数据-参数：indexName：{}", indexName);
        Map<String, Object> map = new HashMap<>();
        try {
            if (StringUtils.isNotBlank(indexName)) {
                SearchRequest searchRequest = new SearchRequest();
                searchRequest.indices(indexName);

                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                searchSourceBuilder.query();
                searchSourceBuilder.sort(new FieldSortBuilder("createTime").order(SortOrder.DESC));
                searchSourceBuilder.size(1);
                searchRequest.source(searchSourceBuilder);

                SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                for (SearchHit hit : search.getHits().getHits()) {
                    MessageDto messageDto = JSONObject.parseObject(hit.getSourceAsString(), MessageDto.class);

                    if (StringUtils.isNotBlank(messageDto.getMessage())) {
                        DeviceMessageDto deviceMessageDto = JSONObject.parseObject(messageDto.getMessage(), DeviceMessageDto.class);
                        map = JSONObject.parseObject(deviceMessageDto.getParams(), Map.class);
                    }
                    if (messageDto.getCreateTime() != null) {
                        map.put("createTime", DateUtils.dateToString1(messageDto.getCreateTime()));
                    }
                }
                log.info("【设备ES模块】搜索最新数据-成功：indexName：{}，map：{}", indexName, map);
            } else {
                log.info("【设备ES模块】搜索最新数据-失败：indexName：{}, error：{}", indexName, "索引名称为空");
            }
        } catch (Exception e) {

            log.error("【设备ES模块】搜索最新数据-异常：indexName：{}, exception：{}", indexName, e.getMessage());
        }

        return map;
    }


    @Override
    public List<MessageDto> searchAllAbilityData(ProductModuleAbilityDataQry productModuleAbilityDataQry) {
        log.info("【设备ES模块】搜索所有功能数据-参数：productModuleAbilityDataPageQry：{}", productModuleAbilityDataQry);

        List<MessageDto> list = new ArrayList<>();
        try {
            // 组装ES查询数据
            if (StringUtils.isNotBlank(productModuleAbilityDataQry.getIndexName())) {
                SearchRequest searchRequest = new SearchRequest();
                searchRequest.indices(productModuleAbilityDataQry.getIndexName());
                // 构建dsl语句
                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                // 构建查询条件
                BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
                RangeQueryBuilder createTimeQueryBuilder = QueryBuilders.rangeQuery("createTime");
                if (productModuleAbilityDataQry.getTimeType() > 0) {
                    createTimeQueryBuilder.gte(DateUtils.getCurrentTime() - productModuleAbilityDataQry.getTimeType() * 1000).lt(DateUtils.getCurrentTime());
                } else if (productModuleAbilityDataQry.getTimeType() == -1) {
                    searchSourceBuilder.size(20);
                } else {
                    if (productModuleAbilityDataQry.getStartDateTime() != null) {
                        createTimeQueryBuilder.gte(productModuleAbilityDataQry.getStartDateTime().getTime());
                    }
                    if (productModuleAbilityDataQry.getEndDateTime() != null) {
                        createTimeQueryBuilder.lt(productModuleAbilityDataQry.getEndDateTime().getTime());
                    }
                }
                boolQueryBuilder.filter(createTimeQueryBuilder);
                searchSourceBuilder.query(boolQueryBuilder);
                searchSourceBuilder.sort(new FieldSortBuilder("createTime").order(SortOrder.DESC));
                searchRequest.source(searchSourceBuilder);

                SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

                log.info("【设备ES模块】搜索所有功能数据-查询数据库：createTimeQueryBuilder：{}，result：{}", createTimeQueryBuilder, search);
                for (SearchHit hit : search.getHits().getHits()) {
                    MessageDto messageDto = JSONObject.parseObject(hit.getSourceAsString(), MessageDto.class);
                    list.add(messageDto);
                }
                log.info("【设备ES模块】搜索所有功能数据-成功：productModuleAbilityDataPageQry：{}，list：{}", productModuleAbilityDataQry, list);
            } else {
                log.info("【设备ES模块】搜索所有功能数据-失败：productModuleAbilityDataPageQry：{}, error：{}", productModuleAbilityDataQry, "索引名称为空");
            }
        } catch (Exception e) {
            log.error("【设备ES模块】搜索所有功能数据-异常：productModuleAbilityDataPageQry：{}, exception：{}", productModuleAbilityDataQry, e.getMessage());
        }

        return list;
    }

    @Override
    public void deleteIndex(String indexName) {
        log.error("【设备ES模块】删除es索引-参数：indexName：{}", indexName);
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(indexName);
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            boolean acknowledged = delete.isAcknowledged();
            log.error("【设备ES模块】删除es索引-结果：indexName：{}, result：{}", indexName, acknowledged);
        } catch (Exception e) {
            log.error("【设备ES模块】删除es索引-参数：indexName：{}, exception：{}", indexName, e.getMessage());
        }

    }
}
