package com.tech.eclouds.xzj.util;

import java.util.regex.Pattern;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/8/26
 */
public class CharUtil {
    private static final String CHINESE_REG="^[\u4e00-\u9fa5]+$";
    private static final String CHS_ENG_REG="^[\u4e00-\u9fa50-9a-zA-Z ]+$";


    public static boolean isChinese(String str) {
        return Pattern.compile(CHINESE_REG).matcher(str).matches();
    }

    public static boolean onlyChsAndEng(String str) {
        return Pattern.compile(CHS_ENG_REG).matcher(str).matches();
    }


}
