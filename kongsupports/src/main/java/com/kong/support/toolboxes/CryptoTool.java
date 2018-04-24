package com.kong.support.toolboxes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 加解密 数据安全工具
 * <pre> 1.单向加密算法
 *
 *  单向加密算法主要用来验证数据传输的过程中，是否被篡改过。
 *
 *      BASE64 严格地说，属于编码格式，而非加密算法
 *
 *      MD5(Message Digest algorithm 5，信息摘要算法)
 *
 *      SHA(Secure Hash Algorithm，安全散列算法)
 *
 *      HMAC(Hash Message Authentication Code，散列消息鉴别码
 * </pre>
 * <pre> 2.对称和非对称加密算法
 *    对称和非对称加密算法主要采用公钥和私钥的形式，来对数据加密。
 *      DES(Data Encryption Standard，数据加密算法)
 *
 *      PBE(Password-based encryption，基于密码验证)
 *
 *      RSA(算法的名字以发明者的名字命名：Ron Rivest, AdiShamir 和Leonard Adleman)
 *
 *      DH(Diffie-Hellman算法，密钥一致协议)
 *
 *      DSA(Digital Signature Algorithm，数字签名)
 *
 *      ECC(Elliptic Curves Cryptography，椭圆曲线密码编码学)</pre>
 */
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
    public static String HmacMD5(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return hmac(origin,key,"HmacMD5");
    }
    public static String HmacMD2(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return hmac(origin,key,"HmacMD2");
    }
    public static String HmacSHA1(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return hmac(origin,key,"HmacSHA1");
    }
    public static String HmacSHA256(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return hmac(origin,key,"HmacSHA256");
    }
    public static String HmacSHA384(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return hmac(origin,key,"HmacSHA384");
    }
    public static String HmacSHA512(byte[] origin,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return hmac(origin,key,"HmacSHA512");
    }





    /**
     * Hmac（含有密钥的摘要算法，也有简称mac，密钥不同摘要也不同
     * @return
     */
    private static String hmac(byte[] origin,byte[] key,String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        Key secretKeySpec = generateSecretKey(key,algorithm);
        Mac instance = Mac.getInstance(secretKeySpec.getAlgorithm());//实例化mac
        instance.init(secretKeySpec);
        byte[] bytes = instance.doFinal(origin);
        return hex(bytes);
    }




    /**
     * 生成密钥
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Key generateSecretKey( String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        return generateSecretKey("00000000".getBytes(),algorithm);
    }

        /**
         * 生成密钥
         * @param key
         * @param algorithm
         * @return
         * @throws NoSuchAlgorithmException
         */
    public static Key generateSecretKey(byte[] key, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        SecretKey secretKeySpec = null;

        Objects.requireNonNull(key,"参数不能为null");
        if ("DES".equals(algorithm)) {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
            secretKeySpec = keyFactory.generateSecret(dks);
        }else  if ("AES".equals(algorithm)){
            secretKeySpec = aesKey(key);
        }else{
            SecureRandom secureRandom = null;
            if (key == null){
                secureRandom = new SecureRandom();
            }else {
                secureRandom = new SecureRandom(key);
            }
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(secureRandom);

            secretKeySpec = keyGenerator.generateKey();

            //还原密钥
        }
        return secretKeySpec;
    }

    // ## DES AES ...

    /**
     * DES 加密
     * @param data
     * @param key
     * @return
     */
    public static byte[] DESDecrypt(byte[] data,String key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
       return decrypt(data,key,"DES");
    }

    public static byte[] DESEncrypt(byte[] data,String key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
       return encrypt(data,key,"DES");
    }


    public static byte[] AESDecrypt(byte[] data,String key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(data,key,"AES");
    }

    public static byte[] AESEncrypt(byte[] data,String key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        return encrypt(data,key,"AES");
    }


    private static byte[] decrypt(byte[] data,String key,String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key des = generateSecretKey(key.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE,des);
        return cipher.doFinal(data);
    }

    private static byte[] encrypt(byte[] data,String key,String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key des = generateSecretKey(key.getBytes(),algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE,des);
        return cipher.doFinal(data);
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
    /** AES密钥 */
    private static SecretKeySpec aesKey(byte[] key) {
        byte[] bs = key;
        if (bs.length != 16) {
            bs = Arrays.copyOf(bs, 16);// 处理数组长度为16
        }
        return new SecretKeySpec(bs, "AES");
    }

}
