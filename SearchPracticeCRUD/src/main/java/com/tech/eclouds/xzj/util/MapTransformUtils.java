package com.tech.eclouds.xzj.util;

/**
 * 类 名:
 * 描 述:
 * 作 者:  马国瑜
 * 创 建：2018/10/15 10:35
 * 版 本：v1.0.0
 * <p>
 * 历 史: (版本) 作者 时间 注释
 */


import org.apache.commons.lang.ArrayUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName: MapTransformUtils
 * @Description: TODO(map和bean之间相互转换)
 * @date 2018年1月24日 下午5:03:19
 * @author SunBo
 * @version v1.0
 * @since 2019/7/24
 */
public class MapTransformUtils {

    /**
     * Map转Bean（不进行下划线转驼峰）
     * @param map
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass)  {
        return mapToObject(map, beanClass, false);
    }

    /**
     * Map转Bean
     * @param map
     * @param beanClass
     * @param <T>
     * @param change true：下划线转驼峰 false: 保持原样
     * @return
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass, boolean change) {
        if (map == null) {
            return null;
        }

        Object obj = null;
        try {
            obj = beanClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        BeanInfo beanInfo = null;
        try {
            if (obj != null) { //Zoyo
                beanInfo = Introspector.getBeanInfo(obj.getClass());
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        if (beanInfo != null) { //Zoyo
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    try {
                        String propertyName = change ? StringTransformUtil.camel2Underline(property.getName(), false) : property.getName();
                        Object value = map.get(propertyName);
                        if (value != null) {
                            if (property.getPropertyType() == boolean.class || property.getPropertyType() == Boolean.class) {
                                switch (map.get(propertyName).toString()) {
                                    case "0":
                                        value = false;
                                        break;
                                    case "1":
                                        value = true;
                                        break;
                                    case "true":
                                        value = true;
                                        break;
                                    case "false":
                                        value = false;
                                        break;
                                    default:
                                        value = false;
                                }
                            }

                            if (property.getPropertyType().isEnum()) {
                                Method method = property.getPropertyType().getDeclaredMethod("valueOf", String.class);
                                value = method.invoke(null, value);
                            }
                            setter.invoke(obj, value);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return (T) obj;
    }

    /**
     * Bean转Map （全字段转换，不进行驼峰转换）
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj)  {
        return objectToMap(obj, false, false);
    }

    /**
     * Bean转Map （不进行驼峰转换）
     * @param obj
     * @param available true: 不包含null字段；false：全字段
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj, boolean available) {
        return objectToMap(obj, available, false);
    }

    /**
     * Bean转Map
     * @param obj
     * @param available true: 不包含null字段；false：全字段
     * @param change true: 驼峰转下划线；false：保持原样
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj, boolean available, boolean change) {
        DecimalFormat df = new DecimalFormat("#.000");

        if (obj == null) {
            return null;
        }
        Object value = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                try {
                    // 过滤class属性
                    if (!key.equals("class")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        value = getter.invoke(obj);
                        if (available && value == null) {
                            continue;
                        }
                        if (!(value instanceof Date) && !(value instanceof Timestamp)) {
                            if (value instanceof BigDecimal) {
                                BigDecimal bigDecimal = (BigDecimal) value;
                                value = df.format(bigDecimal.doubleValue());
                            }
                            if (value instanceof Enum) {
                                value = value.toString();
                            }
                        } else {
                            Date date = (Date) value;
                            value = date.getTime();
                        }
                        if (change) {
                            key = StringTransformUtil.camel2Underline(key, false);
                        }
                        map.put(key, value);
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return map;
    }
    /**
     * 获取实体属性
     * @param clazz
     * @return
     */
    public static Map<String, String> modelFields(Class clazz) {
        Map<String, String> fieldMap = new HashMap();
        Field[] fields = clazz.getDeclaredFields();
        Field[] parentFields = clazz.getSuperclass().getDeclaredFields();
        Field[] allFields = (Field[]) ArrayUtils.addAll(fields, parentFields);

        for (int index = 0; index < allFields.length; ++index) {
            Field field = allFields[index];
            fieldMap.put(field.getName(), field.getGenericType().getTypeName());
        }

        return fieldMap;
    }

    /**
     * map的key大小写转换
     * @param orgMap
     * @param lowerCase
     * @return
     */
    public static Map<String, Object> transformKeysCase(Map<String, Object> orgMap, boolean lowerCase) {
        return orgMap;
//        if (MapUtils.isEmpty(orgMap)) {
//            return orgMap;
//        }
//        Map<String, Object> resultMap = new HashMap<>();
//        for (String key : orgMap.keySet()) {
//            String newKey = lowerCase ? key.toLowerCase() : key.toUpperCase();
//            resultMap.put(newKey, orgMap.get(key));
//        }
//
//        return resultMap;

    }
}



