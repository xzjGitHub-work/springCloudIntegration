package com.tech.eclouds.xzj.util;


import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/24
 */
public class HostAddressUtil {

    /**
     * ip地址
     */
    private static String ipAddress;

    /**
     * 当前机器IP
     *
     * @return
     */
    public static String ipAddrdss() {
        if (StringUtils.isBlank(ipAddress)) {
            InetAddress addr = null;

            try {
                addr = InetAddress.getLocalHost();
                ipAddress = addr.getHostAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ipAddress;
    }


}

