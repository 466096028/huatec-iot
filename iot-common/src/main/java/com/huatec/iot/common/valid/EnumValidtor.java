package com.huatec.iot.common.valid;

import com.huatec.iot.common.annotation.EnumAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-14 16:50
 **/
public class EnumValidtor implements ConstraintValidator<EnumAnnotation, Object> {

    Class<?>[] cls; //枚举类

    @Override
    public void initialize(EnumAnnotation constraintAnnotation) {
        cls = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (cls.length > 0) {
            for (Class<?> cl : cls) {
                try {
                    if (cl.isEnum()) {
                        //枚举类验证
                        Object[] objs = cl.getEnumConstants();
                        Method method = cl.getMethod("getCode");
                        for (Object obj : objs) {
                            Object code = method.invoke(obj, null);
                            String s = String.valueOf(value);
                            if (s.equals(code.toString())) {
                                return true;
                            }
                        }
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
