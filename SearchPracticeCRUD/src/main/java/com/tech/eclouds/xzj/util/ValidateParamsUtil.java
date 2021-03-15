package com.tech.eclouds.xzj.util;

import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * [判断参数是否为空]
 *
 * @author zhangdi
 * @date 2019-10-22 11:03
 */
public class ValidateParamsUtil {

    /**
     * 检测是否为空
     *
     * @param objects 参数
     * @return 结果
     */
    public static boolean isEmpty(Object... objects) {
        if (objects == null) {
            return true;
        }

        for (Object object : objects) {
            if (object == null) {
                return true;
            }
            if (object instanceof String && StringUtils.isEmpty((String) object)) {
                return true;
            }
            if (object instanceof Collection && CollectionUtils.isEmpty((Collection<?>) object)) {
                return true;
            }
            if (object instanceof Map && MapUtils.isEmpty((Map<?, ?>) object)) {
                return true;
            }
        }
        return false;
    }
}
