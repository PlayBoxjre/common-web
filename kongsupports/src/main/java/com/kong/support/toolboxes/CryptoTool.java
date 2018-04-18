package com.kong.support.toolboxes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.*;
import java.util.Arrays;
import java.util.Enumeration;

public class CryptoTool {

    static Logger logger = LoggerFactory.getLogger(CryptoTool.class);


    public static void showSecurityProvider(){
        Provider[] providers = Security.getProviders();
        Arrays.stream(providers).forEach(provider -> {
          logger.info("Provider : {} -- {} ",provider.getName(),provider.getInfo());
            Enumeration<Object> keys = provider.keys();
            while (keys.hasMoreElements()){
                Object o = keys.nextElement();
                logger.info("\t {} = {} ",o,provider.get(o));
            }
        });

    }


    public static String base64Encoding(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(bytes);
        return encode;
    }

    public static byte[] base64Decoding(String encodedStr) throws IOException {
       return new BASE64Decoder().decodeBuffer(encodedStr);
    }

    public static String MD2(byte[] origin) throws NoSuchAlgorithmException {
        return messageDigest(origin,"MD2");
    }

    public static String  MD5(byte[] origin) throws NoSuchAlgorithmException {
        return messageDigest(origin,"MD5");
    }

    public static String SHA1(byte[] origin) throws NoSuchAlgorithmException {
        return messageDigest(origin,"SHA-1");
    }

    public static String SHA256(byte[] origin) throws NoSuchAlgorithmException {
        return messageDigest(origin,"SHA-256");
    }

    public static String SHA384(byte[] origin) throws NoSuchAlgorithmException {
        return messageDigest(origin,"SHA-384");
    }

    public static String SHA512(byte[] origin) throws NoSuchAlgorithmException {
        return messageDigest(origin,"SHA-512");
    }

    /**
     * HmacMD5
     * @param origin
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String HmacMD5(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        return hmac(origin,key,"HmacMD5");
    }
    public static String HmacMD2(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        return hmac(origin,key,"HmacMD2");
    }
    public static String HmacSHA1(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        return hmac(origin,key,"HmacSHA1");
    }
    public static String HmacSHA256(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        return hmac(origin,key,"HmacSHA256");
    }
    public static String HmacSHA384(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        return hmac(origin,key,"HmacSHA384");
    }
    public static String HmacSHA512(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        return hmac(origin,key,"HmacSHA512");
    }

    /**
     * Hmac（含有密钥的摘要算法，也有简称mac，密钥不同摘要也不同
     * @return
     */
    private static String hmac(byte[] origin,byte[] key,String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);////初始化KeyGenerator
       // SecretKey secretKey = keyGenerator.generateKey();////产生密钥
        SecretKey secretKeySpec = new SecretKeySpec(key, algorithm);//还原密钥
        Mac instance = Mac.getInstance(secretKeySpec.getAlgorithm());//实例化mac
        instance.init(secretKeySpec);
        byte[] bytes = instance.doFinal(origin);
        return hex(bytes);
    }




    // private

    private static String  messageDigest(byte[] origin,String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        return hex(digest.digest());
    }

    private static String hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b:
                bytes) {
            int i =  b&0xff;
            if (i<0xf){
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString();
    }


}
