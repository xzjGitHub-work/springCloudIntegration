package com.tech.eclouds.xzj.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;

/**
 * []
 *
 * @author zhangdi
 * @date 2020-01-14 11:11
 */
public class RsaUtil {
    // 加密数据和秘钥的编码方式
    public static final String UTF_8 = "UTF-8";

    // 填充方式
    public static final String AES_ALGORITHM = "AES/CFB/PKCS5Padding";
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final String RSA_ALGORITHM_NOPADDING = "RSA";

    public static final String PRIVATE_KEY_STR = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN3iktMPUXCrdrs5" +
            "4g0ufh3qC7mdpn6Lz7h3c/nBydzdi1c5cEJwWsHL0EBQboBmS8mq/HsDuIurS/7E" +
            "omAdIIyas7WR/4weGwl9FfIfNSsME6sU5EMRlhwdUUmsnFGo1SxvX4Js0gItOSxR" +
            "nKkeDw66/tXuv4AQu580JC0BK9iZAgMBAAECgYEAzyTaC55uWnBIvQuRxNqVtNmu" +
            "LpBGQjfGKaGjLRTEnqRZlIm0CuKXZ/sdpR0xvD9EG7a8VR+pJwM5RFRQfshSfBRB" +
            "KU7+zVQVwrpQ/DiH3oRp4UTgwOlP8LDnZh23Jw3BfBfldP5eGK77W6Ltgj+c+xEH" +
            "7Mabz7cZRoVrtC1eCzECQQD0SQrX+DamRJAFVXv1WQyrxsn8eJXxtC6rFXCjcWoC" +
            "kD0Q5vxEYhSH0j01GKG8B5/1LmIVRgwWa3eZxLFyhX09AkEA6IaJCPvDZjWixPam" +
            "BASRGtqfnkwUC+QlJpVo66fQ2sHmac6+Vdj+GRGu5VA6ZEEJmXZNHl1DT+Vo5wWW" +
            "Y9c2jQJAVLaryv9pvDkUlLTzWr751OwICcSrgvlQIcY6nINtFtx8QMtJelFk+0De" +
            "NvaGx9UBFJ50Jxp2gKPeAvYh7yv5pQJADTWPVVZQDF9hpsTIO5Qy1g6zYJ+7x80n" +
            "GyEDu9dlsveu6jXXdDMGhFAP2nELUCStse0Zhm8P4tsKcY1MtNFGBQJBAJfzj8/d" +
            "C2bfiNeRYDJ0IkcTrvNY79WPis3l0wTKItyVn6UOnDY1PkZfOrVDd5VjWTtJTUPy" +
            "xVu4U0krw++Z6pM=";

    /**
     * Description: 解密接收数据
     *
     * @param externalPublicKey
     * @param selfPrivateKey
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     * @throws DecoderException
     * @author wh.huang  DateTime 2018年11月15日 下午5:06:42
     */
    public static String decryptReceivedData(PublicKey externalPublicKey, PrivateKey selfPrivateKey, String receiveData) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException, DecoderException {

        @SuppressWarnings("unchecked")
        Map<String, String> receivedMap = (Map<String, String>) JSON.parse(receiveData);

        // receivedMap为请求方通过from urlencoded方式，请求过来的参数列表
        String inputSign = receivedMap.get("sign");

        // 用请求方提供的公钥验签，能配对sign，说明来源正确
        inputSign = decryptRSA(externalPublicKey, inputSign);

        // 校验sign是否一致
        String sign = sha256(receivedMap);
        if (!sign.equals(inputSign)) {
            // sign校验不通过，说明双方发送出的数据和对方收到的数据不一致
            return null;
        }

        // 解密请求方在发送请求时，加密data字段所用的对称加密密钥
        String key = receivedMap.get("key");
        String salt = receivedMap.get("salt");
        key = decryptRSA(selfPrivateKey, key);
        salt = decryptRSA(selfPrivateKey, salt);

        // 解密data数据
        String data = decryptAES(key, salt, receivedMap.get("data"));
        return data;
    }

    /**
     * Description: 加密数据组织示例
     *
     * @param externalPublicKey
     * @param selfPrivateKey
     * @return 加密后的待发送数据
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @author wh.huang DateTime 2018年11月15日 下午5:20:11
     */
    public static String encryptSendData(PublicKey externalPublicKey, PrivateKey selfPrivateKey, JSONObject sendData) throws NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException, UnsupportedEncodingException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException {

        // 随机生成对称加密的密钥和IV (IV就是加盐的概念，加密的偏移量)
        String aesKeyWithBase64 = genRandomAesSecretKey();
        String aesIVWithBase64 = genRandomIV();

        // 用接收方提供的公钥加密key和salt，接收方会用对应的私钥解密
        String key = encryptRSA(externalPublicKey, aesKeyWithBase64);
        String salt = encryptRSA(externalPublicKey, aesIVWithBase64);

        // 组织业务数据信息，并用上面生成的对称加密的密钥和IV进行加密
        String cipherData = encryptAES(aesKeyWithBase64, aesIVWithBase64, sendData.toJSONString());

        // 组织请求的key、value对
        Map<String, String> requestMap = new TreeMap<String, String>();
        requestMap.put("key", key);
        requestMap.put("salt", salt);
        requestMap.put("data", cipherData);
        requestMap.put("source", "由接收方提供"); // 添加来源标识

        // 计算sign，并用请求方的私钥加签，接收方会用请求方发放的公钥验签
        String sign = sha256(requestMap);
        requestMap.put("sign", encryptRSA(selfPrivateKey, sign));

        // 注意：请务必以form urlencoded方式，否则base64转码后的个别字符可能会被转成空格，对方接收后将无法正常处理
        JSONObject json = new JSONObject();
        json.putAll(requestMap);
        return json.toString();
    }

    /**
     * Description: 获取随机的对称加密的密钥
     *
     * @return 对称秘钥字符
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @author wh.huang  DateTime 2018年11月15日 下午5:25:53
     */
    private static String genRandomAesSecretKey() throws NoSuchAlgorithmException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        String keyWithBase64 = Base64.encodeBase64(secretKey.getEncoded()).toString();

        return keyWithBase64;

    }

    private static String genRandomIV() {
        SecureRandom r = new SecureRandom();
        byte[] iv = new byte[16];
        r.nextBytes(iv);
        String ivParam = Base64.encodeBase64(iv) + "";
        return ivParam;
    }

    /**
     * 对称加密数据
     *
     * @param keyWithBase64
     * @param plainText
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    private static String encryptAES(String keyWithBase64, String ivWithBase64, String plainText)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        byte[] keyWithBase64Arry = keyWithBase64.getBytes();
        byte[] ivWithBase64Arry = ivWithBase64.getBytes();
        SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(keyWithBase64Arry), "AES");
        IvParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(ivWithBase64Arry));

        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        return Base64.encodeBase64(cipher.doFinal(plainText.getBytes(UTF_8))).toString();
    }

    /**
     * 对称解密数据
     *
     * @param keyWithBase64
     * @param cipherText
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    private static String decryptAES(String keyWithBase64, String ivWithBase64, String cipherText)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        byte[] keyWithBase64Arry = keyWithBase64.getBytes();
        byte[] ivWithBase64Arry = ivWithBase64.getBytes();
        byte[] cipherTextArry = cipherText.getBytes();
        SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(keyWithBase64Arry), "AES");
        IvParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(ivWithBase64Arry));

        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return new String(cipher.doFinal(Base64.decodeBase64(cipherTextArry)), UTF_8);
    }

    /**
     * 非对称加密，根据公钥和原始内容产生加密内容
     *
     * @param key
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    private static String encryptRSA(Key key, String plainText)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64(cipher.doFinal(plainText.getBytes(UTF_8))).toString();
    }

    /**
     * 根据私钥和加密内容产生原始内容
     *
     * @param key
     * @param content
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws DecoderException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    private static String decryptRSA(Key key, String content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, DecoderException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] contentArry = content.getBytes();
        return new String(cipher.doFinal(Base64.decodeBase64(contentArry)), UTF_8);
    }

    /**
     * 计算sha256值
     *
     * @param paramMap
     * @return 签名后的所有数据，原始数据+签名
     */
    private static String sha256(Map<String, String> paramMap) {
        Map<String, String> params = new TreeMap<String, String>(paramMap);

        StringBuilder concatStr = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if ("sign".equals(entry.getKey())) {
                continue;
            }
            concatStr.append(entry.getKey() + "=" + entry.getValue() + "&");
        }

        return DigestUtils.md5Hex(concatStr.toString());
    }

    /**
     * 创建RSA的公钥和私钥示例 将生成的公钥和私钥用Base64编码后打印出来
     *
     * @throws NoSuchAlgorithmException
     */
    public static void createKeyPairs() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    /**
     * Description:默认的RSA解密方法 一般用来解密 参数 小数据
     *
     * @param privateKeyStr
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @author wh.huang  DateTime 2018年12月14日 下午3:43:11
     */
    public static String decryptRSADefault(String privateKeyStr, String data) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM_NOPADDING);
        byte[] privateKeyArray = privateKeyStr.getBytes();
        byte[] dataArray = data.getBytes();
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyArray));
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM_NOPADDING);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.decodeBase64(dataArray)), UTF_8);
    }

}
