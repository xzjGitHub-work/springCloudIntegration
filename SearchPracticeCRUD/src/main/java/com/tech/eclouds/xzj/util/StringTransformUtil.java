package com.tech.eclouds.xzj.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/23
 */
public class StringTransformUtil {

    private static String MATCH = "[A-Z]";

    private static String FUNCY_PREFIX = "#FUNCY#";

    /**
     * 下划线转换为驼峰
     *
     * @param line             下划线字符串
     * @param firstIsUpperCase 首字母是否转换为大写
     * @return
     */
    public static String underline2Camel(String line, boolean firstIsUpperCase) {
        String str = "";

        if (StringUtils.isBlank(line)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder();
            String[] strArr;

            String firstChar = line.substring(0, 1);

            strArr = line.toLowerCase().split("_");
            for (String s : strArr) {
                sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
            }
            str = sb.toString();
//            if (!firstIsUpperCase) {
            str = firstChar + str.substring(1);
//            }

            return str;
        }
    }

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line, boolean upperCase) {
        return line;
//        if (StringUtils.isEmpty(line)) {
//            return "";
//        }
//
//        StringBuilder result = new StringBuilder();
//        if (line != null && line.length() > 0) {
//            // 将第一个字符处理成大写
//            result.append(line.substring(0, 1).toUpperCase());
//            // 循环处理其余字符
//            for (int i = 1; i < line.length(); i++) {
//                String s = line.substring(i, i + 1);
//                // 在大写字母前添加下划线
//                if (Pattern.matches(MATCH, s)) {
//                    result.append("_");
//                }
//                // 其他字符直接转成大写
//                result.append(s);
//            }
//        }
//
//        if (upperCase) {
//            return result.toString().toUpperCase();
//        } else {
//            return result.toString().toLowerCase();
//        }

    }


    public static String getValueToSQLString(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            String sqlVal = StringEscapeUtils.escapeSql((String) value);

            if (StringUtils.startsWith(sqlVal, FUNCY_PREFIX)) {
                return sqlVal.replace(FUNCY_PREFIX, "");
            } else {
                return new StringBuffer("'").append(sqlVal).append("'").toString();
            }
        }

        if (value instanceof Number) {
            return value.toString();
        }

        if (value instanceof Date) {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            return new StringBuffer("'").append(formatter.format(value)).append("'").toString();
            return ((Date) value).getTime() + "";
        }


        if (value instanceof Boolean) {
            return value.toString();
        }

        if (value.getClass().isEnum()) {
            return new StringBuffer("'").append(value.toString()).append("'").toString();
        }

        return new StringBuffer("'").append(JSONObject.toJSONString(value)).append("'").toString();
    }


    public static String getFuncySqlValue(String value) {
        return FUNCY_PREFIX + value;
    }
}
