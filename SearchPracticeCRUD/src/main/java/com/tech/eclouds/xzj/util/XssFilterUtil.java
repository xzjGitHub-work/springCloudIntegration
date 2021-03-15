package com.tech.eclouds.xzj.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @createTime 20190821 19:06
 * @description xss过滤工具类
 */
public class XssFilterUtil {


    private static final String EVENTS = "((?i)onload|onunload|onchange|onsubmit|onreset|onmouseenter|confirm"
            + "|onselect|onblur|onfocus|onkeydown|onkeypress|onkeyup|onerror|script|alert|prompt"
            + "|onclick|ondblclick|onmousedown|onmousemove|onmouseout|onfScripTocus|alscRiPtert|onmouseover|onmouseup)";

    private static final String XSS_HTML_TRANs_TAG = "&#x([a-zA-Z0-9]{2});";
    private static final String HTTPEVENTS = "((?i)(http://)|(https://)|href|location|(www\\.)|(\\.com)|(\\.cn)|src)";
    private static final String XSS_HTML_TAG = "(%3C)|(%3E)|[<>]|<|>";
    private static final String XSS_INJECTION = "((%22%20)|(%22\\s)|('%22)|(%22))\\w.*|(\\s|%20)"
            + EVENTS + ".*|(%3D)|(%7C)";
    private static final String XSS_CHART = "&|#|/|'|(%26)|(%23)|(%2f)|(%27)";
    private static final String XSS_REGEX = XSS_HTML_TAG + "|" + XSS_INJECTION;
    private static final String SQL_REGEX = "('.--)|(--)|(\\|)|(%7C)";
    private static final String SQL_EVENT = "((?i) avg|where|from|select|delete|update|insert|create|table)";
    private static final String Symbol = "((%22%20)|(%22\\s)|('%22)|(%22))\\w.*|'|\"";

    private static final Pattern XSS_HTTP_PARTEN = Pattern.compile("(http|https)://.*?/");
    private static final Pattern SGCC_PARTEN = Pattern.compile(".*(esgcc.com|esgmallt.com|sgmall.com).*");

    public static final String DEFAULTFILTER = "filterStringAscll";

    public static String filterString(String param) {
        if (param == null) {
            return null;
        }
        param = param.replaceAll(XSS_REGEX, "*");
        param = param.replaceAll(SQL_REGEX, "*");
        param = param.replaceAll(EVENTS, "*");
        param = param.replaceAll(SQL_EVENT, "-");
        return param;
    }

    public static String filterEventString(String param) {
        if (param == null) {
            return null;
        }
        param = param.replace("'", "&#039;");
        param = param.replace("\"", "$quot;");
        param = param.replace("<", " &lt;");
        param = param.replace(">", "&gt;");
        param = param.replace("&", "&amp;");
        param = param.replaceAll(XSS_REGEX, "*");
        param = param.replaceAll(SQL_REGEX, "*");
        param = param.replaceAll(EVENTS, "*");
        param = param.replaceAll(SQL_EVENT, "-");
        return param;
    }

    public static String filterStringAscll(String param) {
        if (param == null) {
            return null;
        }
        param = param.replace("'", "&#039;");
        param = param.replace("\"", "$quot;");
        param = param.replace("<", "");
        param = param.replace(">", "");
        param = param.replace("&", "");
        param = param.replace("#", "");
        param = param.replaceAll(XSS_REGEX, "");
        param = param.replaceAll(SQL_REGEX, "");
        param = param.replaceAll(EVENTS, "");
        param = param.replaceAll(SQL_EVENT, "");
        return param;
    }


    public static String filterEventScriptString(String param) {
        if (param == null) {
            return null;
        }
        param = param.replaceAll(EVENTS, "*");
        param = param.replaceAll(XSS_HTML_TAG, "*");
        return param;
    }

    public static String filterHttpString(String param) {
        if (param == null) {
            return null;
        }
        param = param.replaceAll(EVENTS, "*");
        param = param.replaceAll(XSS_HTML_TAG, "*");
        param = param.replaceAll(HTTPEVENTS, "*");
        return param;
    }

    public static String filterExtHttpLinkString(String param) {
        if (param == null) {
            return null;
        }
        Matcher matcher = XSS_HTTP_PARTEN.matcher(param);
        StringBuffer sbr = new StringBuffer();
        while (matcher.find()) {
            if (!SGCC_PARTEN.matcher(matcher.group()).matches()) {
                matcher.appendReplacement(sbr, "*");
            }
        }
        matcher.appendTail(sbr);
        param = sbr.toString();
        param = param.replaceAll(EVENTS, "*");
        param = param.replaceAll(XSS_HTML_TRANs_TAG, "*");
        return param;
    }

    public static boolean checkExtHttpLinkString(String param) {
        if (param == null) {
            return false;
        }
        Matcher matcher = XSS_HTTP_PARTEN.matcher(param);
        while (matcher.find()) {
            if (!SGCC_PARTEN.matcher(matcher.group()).matches()) {
                return true;
            }
        }
        return false;
    }

    public static String replaceXssString(String param) {
        if (StringUtils.isBlank(param)) {
            return "";
        }
        param = param.replace("'", "&#039;");
        param = param.replace("\"", "$quot;");
        param = param.replace("<", " &lt;");
        param = param.replace(">", "&gt;");
        param = param.replace("&", "&amp;");
        param = param.replace("'", "*");
        param = param.replace("\"", "*");
        param = param.replace("<", "*");
        param = param.replace(">", "*");
        param = param.replaceAll(XSS_REGEX, "*");
        param = param.replaceAll(SQL_REGEX, "*");
        param = param.replaceAll(EVENTS, "*");
        param = param.replaceAll(SQL_EVENT, "-");
        return param;
    }


    public static String replaceUrlXssString(String param) {
        if (StringUtils.isBlank(param)) {
            return "";
        }
        param = param.replace("'", "*");
        param = param.replace("\"", "*");
        param = param.replace("<", "*");
        param = param.replace(">", "*");
        param = param.replace(" ", "*");
        return param;
    }

    public static void eventScriptFilter(Object obj) {
        if (obj == null) {
            return;
        }
        Class cls = obj.getClass();
        Field[] fieldArr = cls.getDeclaredFields();
        if (fieldArr != null && fieldArr.length != 0) {
            for (int i = 0; i < fieldArr.length; i++) {
                Field field = fieldArr[i];
                if (field.getType() == String.class) {
                    String fieldName = field.getName();
                    String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    try {
                        Method getMethod = cls.getMethod(getMethodName);
                        String retVal = (String) getMethod.invoke(obj);
                        Method setMethod = cls.getMethod(setMethodName, String.class);
                        setMethod.invoke(obj, XssFilterUtil.filterEventScriptString(retVal));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void xssScriptFilter(Object obj) {
        if (obj == null) {
            return;
        }
        Class cls = obj.getClass();
        Field[] fieldArr = cls.getDeclaredFields();
        if (fieldArr != null && fieldArr.length != 0) {
            for (int i = 0; i < fieldArr.length; i++) {
                Field field = fieldArr[i];
                if (field.getType() == String.class) {
                    String fieldName = field.getName();
                    String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    try {
                        Method getMethod = cls.getMethod(getMethodName);
                        String retVal = (String) getMethod.invoke(obj);
                        Method setMethod = cls.getMethod(setMethodName, String.class);
                        setMethod.invoke(obj, XssFilterUtil.replaceXssString(retVal));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void filterStringAscll(Object obj) {
        if (obj == null) {
            return;
        }
        Class cls = obj.getClass();
        Field[] fieldArr = cls.getDeclaredFields();
        if (fieldArr != null && fieldArr.length != 0) {
            for (int i = 0; i < fieldArr.length; i++) {
                Field field = fieldArr[i];
                if (field.getType() == String.class) {
                    String fieldName = field.getName();
                    String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    try {
                        Method getMethod = cls.getMethod(getMethodName);
                        String retVal = (String) getMethod.invoke(obj);
                        Method setMethod = cls.getMethod(setMethodName, String.class);
                        setMethod.invoke(obj, XssFilterUtil.filterStringAscll(retVal));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String filterSymbolString(String param) {
        if (param == null) {
            return null;
        }
        return param = param.replaceAll(Symbol, "");
    }

    /**
     * 过滤脚本注入
     */
    public static void scriptFilter(Object obj) {
        if (obj == null) {
            return;
        }
        Class cls = obj.getClass();
        Field[] fieldArr = cls.getDeclaredFields();
        if (fieldArr != null && fieldArr.length != 0) {
            for (int i = 0; i < fieldArr.length; i++) {
                Field field = fieldArr[i];
                if (field.getType() == String.class) {
                    String fieldName = field.getName();
                    String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    try {
                        Method getMethod = cls.getMethod(getMethodName);
                        String retVal = (String) getMethod.invoke(obj);
                        Method setMethod = cls.getMethod(setMethodName, String.class);
                        setMethod.invoke(obj, XssFilterUtil.filterString(retVal));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void scriptEventFilter(Object obj) {
        if (obj == null) {
            return;
        }
        Class cls = obj.getClass();
        Field[] fieldArr = cls.getDeclaredFields();
        if (fieldArr != null && fieldArr.length != 0) {
            for (int i = 0; i < fieldArr.length; i++) {
                Field field = fieldArr[i];
                if (field.getType() == String.class) {
                    String fieldName = field.getName();
                    String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    try {
                        Method getMethod = cls.getMethod(getMethodName);
                        String retVal = (String) getMethod.invoke(obj);
                        Method setMethod = cls.getMethod(setMethodName, String.class);
                        setMethod.invoke(obj, XssFilterUtil.filterEventString(retVal));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @param entity
     * @param filterMethod
     * @outher hxye
     * <p>
     * 可过滤父类
     * ps:新版
     * </p>
     */
    public static void xssFilterEntity(Object entity, String filterMethod) {
        if (entity == null) {
            return;
        }
        Class selfCla = entity.getClass();
        Class superCla = selfCla.getSuperclass();
        if (superCla != null) {
            xssFilterFields(entity, superCla, filterMethod);
        }
        xssFilterFields(entity, selfCla, filterMethod);
    }

    /**
     * @param entity
     * @outher hxye
     * <p>
     * 可过滤父类
     * ps:新版
     * </p>
     */
    public static void xssFilterEntity(Object entity) {
        if (entity == null) {
            return;
        }
        Class selfCla = entity.getClass();
        Class superCla = selfCla.getSuperclass();
        if (superCla != null) {
            xssFilterFields(entity, superCla, XssFilterUtil.DEFAULTFILTER);
        }
        xssFilterFields(entity, selfCla, XssFilterUtil.DEFAULTFILTER);
    }

    /**
     * @param obj
     * @param cls
     * @param filterMethod
     */
    private static void xssFilterFields(Object obj, Class cls, String filterMethod) {
        Field[] fieldArr = cls.getDeclaredFields();
        if (fieldArr != null && fieldArr.length != 0) {
            for (int i = 0; i < fieldArr.length; i++) {
                Field field = fieldArr[i];
                if (field.getType() == String.class) {
                    String fieldName = field.getName();
                    String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    try {
                        Method getMethod = cls.getMethod(getMethodName);
                        String retVal = (String) getMethod.invoke(obj);
                        if (retVal != null) {
                            Method setMethod = cls.getMethod(setMethodName, String.class);
                            setMethod.invoke(obj, xssFilterField(retVal, filterMethod));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 过滤单个字段
     *
     * @param xssFieldMethodName 过滤方法
     * @param entityFieldVal     入参值
     * @return
     */
    private static String xssFilterField(String entityFieldVal, String xssFieldMethodName) {
        try {
            String filterVal = null;
            if (StringUtils.isNotEmpty(xssFieldMethodName) && StringUtils.isNotEmpty(entityFieldVal)) {
                Class<XssFilterUtil> xssCla = XssFilterUtil.class;
                XssFilterUtil xssFilterUtil = xssCla.newInstance();
                Method method = xssCla.getMethod(xssFieldMethodName, String.class);
                filterVal = (String) method.invoke(xssFilterUtil, entityFieldVal);
                return filterVal;
            }
            return filterVal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    /**
//     * 获取参数并过滤
//     *
//     * @param params
//     */
//    public static String getAllParam(String uscToken, String params) {
//        JSONObject retJson = new JSONObject();
//        JSONObject jsonObject = JSONObject.parseObject(params);
//        Set<String> set = jsonObject.keySet();
//        for (String key : set) {
//
//            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(jsonObject.getString(key))) {
//                if (key.equals("protocolContent")) {
//
//                    retJson.put(key, UeDitorXss.replaceHtmlCode(jsonObject.getString(key)));
//
//                } else {
//                    retJson.put(key, filterEventString(jsonObject.getString(key)));
//                }
//            }
//
//        }
//        if (StringUtils.isNotEmpty(uscToken)) {
//            JSONObject uscUser = JSONObject.parseObject(uscToken);
//            retJson.put("uscId", uscUser.getString("userId"));
//            retJson.put("uscAccount", uscUser.getString("loginAccount"));
//            retJson.put("uscUser", uscToken);
//        }
//
//        return retJson.toString();
//    }
//
//    public static String getAllParams(String params) {
//        JSONObject retJson = new JSONObject();
//        JSONObject jsonObject = JSONObject.parseObject(params);
//        Set<String> set = jsonObject.keySet();
//        for (String key : set) {
//
//            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(jsonObject.getString(key))) {
//                if (key.equals("protocolContent")) {
//
//                    retJson.put(key, UeDitorXss.replaceHtmlCode(jsonObject.getString(key)));
//
//                } else {
//                    retJson.put(key, filterEventString(jsonObject.getString(key)));
//                }
//            }
//
//        }
//
//        return retJson.toString();
//    }




    /*public static void main(String[] args) {
		String str1 = "http://www.baidu.com";
		String str2 ="incoming cn";
		System.out.println(filterHttpString(str1));
		System.out.println(filterHttpString(str2));
		String test = "currentPage=1&title=second'%2bwindow.%26%23%78%36%31%3b%26%23%78%36%63%3b%26%23%78%36%35%3b%26%23%78%37%32%3b%26%23%78%37%34%3b(/chris/)%2b'&content=%3Cp%3Easdafsdsg%3C%2Fp%3E%0D%0A&annoId=4194604D1E0E6142";
		String urlTest = URLDecoder.decode(test);
		System.out.println(urlTest.replaceAll(XSS_HTML_TRANs_TAG,"*"));


		String aaa="<alert>adf&adf#wer/avc' %26  %23  %2f  %27</alert>";
		System.out.println(filterString(aaa));
        System.out.println(replaceXssString("><img src=\"1\"onerror=\"alert(1)"));
    }*/


}
