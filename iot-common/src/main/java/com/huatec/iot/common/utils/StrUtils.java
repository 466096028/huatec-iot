package com.huatec.iot.common.utils;

import cn.hutool.core.lang.hash.Hash;
import com.huatec.iot.common.domain.mqtt.entiry.ProductAndDevicePattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 14:15
 **/
@Slf4j
public class StrUtils {
    /**
     * 解析
     * @param compileStr
     * @return
     */
    public static ProductAndDevicePattern topicAnalysis(String topic, String compileStr) {
        // 转换字符串为正则
        String str = transitionSysTopic(compileStr, "(.*)");

        ProductAndDevicePattern productAndDevicePattern = new ProductAndDevicePattern();
        if (StringUtils.isNotBlank(topic) && StringUtils.isNotBlank(str)) {
            Pattern pattern = Pattern.compile(str);
            Matcher matcher = pattern.matcher(topic);
            if (matcher.find()) {
                if (StringUtils.isNotBlank(matcher.group(1))) {
                    productAndDevicePattern.setProductKey(matcher.group(1));
                }

                if (StringUtils.isNotBlank(matcher.group(2))) {
                    productAndDevicePattern.setDeviceKey(matcher.group(2));
                }
            }
        }

        return productAndDevicePattern;
    }

    /**
     * 转换系统主题
     * @param str
     * @param symbol
     * @return
     */
    public static String transitionSysTopic (String str, String symbol) {
        String replace = str.replace("${productKey}", symbol).replace("${deviceKey}", symbol);
        return replace;
    }

    /**
     *  转换系统主题
     * @param str
     * @param prodoctKey
     * @param deviceKey
     * @return
     */
    public static String transitionSysTopic1 (String str, String prodoctKey, String deviceKey) {
        String replace = str.replace("${productKey}", prodoctKey).replace("${deviceKey}", deviceKey);
        return replace;
    }

    /**
     * 转换MQTT系统主题
     * @param str
     * @return
     */
    public static String transitionMqttSysTopic (String str) {
        String replace = str.replace("$SYS", "(.*)").replace("${node}", "(.*)").replace("${clientid}", "(.*)");
        return replace;
    }

}
