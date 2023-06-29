package com.huatec.iot.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-05-14 00:13
 **/
@Slf4j
public class FileUtils {
    /**
     * 检查目录是否存在
     * @param dirName
     */
    public static void checkDir(String dirName) {
        File file = new File(dirName);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            log.info("【文件工具类】检查目录是否存在-不存在，message：{}", "目录不存在");
            file.mkdir();
            log.info("【文件工具类】检查目录是否存在-创建目录，message：{}", "目录已创建");
        } else {
            log.info("【文件工具类】检查目录是否存在-存在，message：{}", "目录存在");
        }
    }
}
