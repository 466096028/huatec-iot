package com.huatec.iot.common.annotation;

import com.huatec.iot.common.valid.JsonValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @program: huatec-iot-api
 * @description: json注解
 * @author: jiangtaohou
 * @create: 2023-04-13 09:44
 **/

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {JsonValidtor.class})
@Documented
public @interface JsonAnnotation {
    String message() default "字符串必须为json格式";

    String jsonValue() default "";

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};

    // 指定多个时使用
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        JsonAnnotation[] value();
    }
}
