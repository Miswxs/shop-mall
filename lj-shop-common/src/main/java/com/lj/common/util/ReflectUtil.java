package com.lj.common.util;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类
 */
public class ReflectUtil {

    public static Object getFieldValue(Object obj , String fieldName ){
        if(obj == null){
            return null ;
        }
        Field targetField = getTargetField(obj.getClass(), fieldName);
        try {
            return FieldUtils.readField(targetField, obj, true ) ;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static Field getTargetField(Class<?> targetClass, String fieldName) {
        Field field = null;
        try {
            if (targetClass == null) {
                return field;
            }
            if (Object.class.equals(targetClass)) {
                return field;
            }
            field = FieldUtils.getDeclaredField(targetClass, fieldName, true);
            if (field == null) {
                field = getTargetField(targetClass.getSuperclass(), fieldName);
            }
        } catch (Exception e) {
        }
        return field;
    }

    public static void setFieldValue(Object obj , String fieldName , Object value ){
        if(null == obj){return;}
        Field targetField = getTargetField(obj.getClass(), fieldName);
        try {
            FieldUtils.writeField(targetField, obj, value) ;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public  static Map<String,Field> getFiledMap(Class cls){
        if(null == cls) {
            return null;
        }
        Field[] fields= cls.getDeclaredFields();
        Map<String,Field> fileMap = new HashMap<>();
        for(Field field:fields){
            fileMap.put(field.getName(),field);
        }
        return fileMap;
    }

}
