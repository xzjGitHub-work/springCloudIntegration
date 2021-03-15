package com.tech.eclouds.xzj.util;


import java.math.BigDecimal;

/**
 * @author SunBo
 * @version v1.0
 * @since 2020/8/13
 */
public class Expression {


    public static double positiveNormalization(BigDecimal val, BigDecimal max, BigDecimal min) {
        try {
            return val.subtract(min).divide(max.subtract(min), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            return 0d;
        }
    }

    public static double negativeNormalization(BigDecimal val, BigDecimal max, BigDecimal min) {
        try {
            return max.subtract(val).divide(max.subtract(min), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            return 0d;
        }
    }

}
