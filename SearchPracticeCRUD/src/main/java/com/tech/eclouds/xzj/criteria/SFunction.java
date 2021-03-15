package com.tech.eclouds.xzj.criteria;

import java.io.Serializable;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/21
 */
@FunctionalInterface
public interface SFunction<T, R> extends Serializable {
    R apply(T t);
}
