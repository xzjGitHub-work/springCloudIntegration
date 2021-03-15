package com.tech.eclouds.xzj.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * []
 *
 * @author zhangdi
 * @date 2020-06-11 10:05
 */
@Slf4j
public class ImageUtil {

    /**
     * 将网络图片进行Base64位编码
     *
     * @param imageUrl 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(String imageUrl) {
        if(StringUtils.isEmpty(imageUrl)){
            return null;
        }
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(new URL(imageUrl));
            String formatName = imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, formatName, outputStream);
        } catch (IOException e) {
            log.error("encodeImgageToBase64异常", e);
        }
        if(outputStream == null){
            return null;
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(outputStream.toByteArray()).replaceAll("[\\s*\t\n\r]", "");
    }

    /**
     * 将本地图片进行Base64位编码
     */
    public static String encodeImgageToBase64(File imageFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (IOException e) {
            log.error("encodeImgageToBase64异常", e);
        }
        if(outputStream == null){
            return null;
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(outputStream.toByteArray());
    }

    /**
     * 将Base64位编码进行转本地图片
     */
    public static void decodeBase64ToImage(String base64, String path, String imgName) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            FileOutputStream write = new FileOutputStream(new File(path
                    + imgName));
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            log.error("decodeBase64ToImage异常", e);
        }
    }
}