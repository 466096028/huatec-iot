package com.huatec.iot.common.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: huatec-iot-api
 * @description: 正则枚举
 * @author: jiangtaohou
 * @create: 2023-05-09 15:28
 **/
public enum  RegexpEnums {
    INT_NATURAL_NUMBER("^-?(\\d)+$"),
    POSITIVE_INT_NATURAL_NUMBER("^?(\\d)+$"),
    FLOAT_NUMBER("^-?(\\d+)(\\.\\d+)?$"),
    POSITIVE_FLOAT_NUMBER("^?(\\d+)(\\.\\d+)?$"),
    DOUBLE_NUMBER("^-?(\\d+)(\\.\\d+)?$"),
    POSITIVE_DOUBLE_NUMBER("^?(\\d+)(\\.\\d+)?$");

    public String regexp;

    RegexpEnums(String regexp) {
        this.regexp = regexp;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public static boolean validateInfo(String content, String reg) {
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(content);

        return mat.matches();
    }

}
