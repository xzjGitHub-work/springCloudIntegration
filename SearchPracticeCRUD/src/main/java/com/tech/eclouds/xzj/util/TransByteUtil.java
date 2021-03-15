package com.tech.eclouds.xzj.util;

import java.text.DecimalFormat;

/**
 * []
 *
 * @author zhangdi
 * @date 2020-03-05 10:48
 */
public class TransByteUtil {

    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS <= 0) {
            fileSizeString = "0B";
        } else if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
