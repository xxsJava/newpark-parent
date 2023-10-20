package com.newpark.core.utils;/**
 * @Author xxs18
 * @Description
 * @Date 2023/10/18 12:44
 **/

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xxs18
 * @Description map 工具类
 * @Date 2023/10/18 12:44
 **/
public class MapUtils {

    public static Map<String, Object> objectToMap(Object object){
        Map<String,Object> dataMap = new HashMap<>();
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                dataMap.put(field.getName(),field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }

}
