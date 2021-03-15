package com.tech.eclouds.xzj.util;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/9/2
 */
public class PinYinUtils {

    /**
     * 获取汉字首字母的方法。如： 张三 --> ZS
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     *
     * @param str 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static String getInitials(String str, boolean upperCase) {
        String result = null;
        if (null != str && !"".equals(str)) {
            char[] charArray = str.replaceAll(" ", "").toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (null != stringArray) {
                    sb.append(stringArray[0].charAt(0));
                } else {
                    sb.append(ch);
                }
            }
            if (sb.length() > 0) {
                if (upperCase) {
                    result = sb.toString().toUpperCase();
                } else {
                    result = sb.toString().toLowerCase();
                }

            }
        }
        return result;
    }

    public static String getFull(String str, boolean upperCase) {
        String result = null;
        if (null != str && !"".equals(str)) {
            char[] charArray = str.replaceAll(" ", "").toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (null != stringArray) {
                    // 把第几声这个数字给去掉
                    sb.append(stringArray[0].replaceAll("\\d", ""));
                } else {
                    sb.append(ch);
                }
            }
            if (sb.length() > 0) {
                if (upperCase) {
                    result = sb.toString().toUpperCase();
                } else {
                    result = sb.toString().toLowerCase();
                }
            }
        }
        return result;
    }

}
