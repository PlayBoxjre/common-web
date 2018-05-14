package com.kong.support.toolboxes;

import com.kong.support.exceptions.CryptoExceptions;
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
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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

    private static final String PUBLIC_KEY = "public_key";
    private static final String PRIVATE_KEY = "private_key";

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
            byte[] bs = key;
            if (bs.length != 16) {
                bs = Arrays.copyOf(bs, 16);// 处理数组长度为16
            }
            DESKeySpec dks = new DESKeySpec(bs);
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


    public static RSA RSA() {
        return new RSA();
    }







    // private
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




    private static String  messageDigest(byte[] origin,String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        return hex(digest.digest());
    }

    private static String hex(byte[] bytes){
      return StringTool.hex(bytes);
    }
    /** AES密钥 */
    private static SecretKeySpec aesKey(byte[] key) {
        byte[] bs = key;
        if (bs.length != 16) {
            bs = Arrays.copyOf(bs, 16);// 处理数组长度为16
        }
        return new SecretKeySpec(bs, "AES");
    }
// inner class
    public static class RSA{

    public RSA() {
    }



    public  RSA initRSAKey() throws NoSuchAlgorithmException {
        KeyPairGenerator kpGenerator = KeyPairGenerator.getInstance("RSA");
        kpGenerator.initialize(2048);

        KeyPair keyPair = kpGenerator.generateKeyPair();
        // 存储公钥
        publicKey= keyPair.getPublic();
        // 存储密钥
        privateKey = keyPair.getPrivate();
        return this;
    }

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public String getPublicKey() {
        return base64Encoding(publicKey.getEncoded());
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * 生成的是base64编码字符串
     * @return
     */
    public String getPrivateKey() {
        return base64Encoding(privateKey.getEncoded());
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }
    /**
     * 通过字符串生成私钥
     */
    PrivateKey getPrivateKey(String privateKeyData) throws CryptoExceptions {
        PrivateKey privateKey = null;
        try {
            byte[] decodeKey = base64Decoding(privateKeyData); //将字符串Base64解码
            PKCS8EncodedKeySpec x509= new PKCS8EncodedKeySpec(decodeKey);//创建x509证书封装类
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");//指定RSA
            privateKey = keyFactory.generatePrivate(x509);//生成私钥
        } catch (Exception e) {
            String mes = "rsa 生成私钥失败";
            throw new CryptoExceptions(mes.hashCode(),mes);
        }
        return  privateKey;
    }



    /**
     * 通过字符串生成公钥
     */
    PublicKey getPublicKey(String publicKeyData) throws CryptoExceptions {
        PublicKey publicKey = null;
        try {
            byte[] decodeKey = base64Decoding(publicKeyData);
            X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodeKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey =  keyFactory.generatePublic(x509);
        } catch (Exception e) {
            String mes = "rsa 生成公钥失败";
            throw new CryptoExceptions(mes.hashCode(),mes);
        }
        return publicKey;
    }
    /**
     * 公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public  String encryptByPublicKey(String data, String  key) throws CryptoExceptions {
       PublicKey publicKey = getPublicKey(key);
        try {

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 模长
            return base64Encoding(cipher.doFinal(data.getBytes()));
        }catch(Exception e){
                 e.printStackTrace();
                 String mesg = "rsa public 加密失败";
                 throw new CryptoExceptions(mesg.hashCode(),mesg);
                 }

    }



    /**
     * 私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public  String encryptByPrivateKey(String data,
                                             String  key) throws CryptoExceptions {
        PrivateKey privateKey =   getPrivateKey(key);
    try {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // 模长
        return base64Encoding(cipher.doFinal(data.getBytes()));
    }catch (Exception e){
        e.printStackTrace();
        String mesg = "rsa private 加密失败";
        throw new CryptoExceptions(mesg.hashCode(),mesg);

    }
    }
    /**
     * 私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] decryptByPrivateKey(String data,
                                      String   key) throws Exception {
        PrivateKey privateKey = getPrivateKey(key);
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // 模长
            return cipher.doFinal(base64Decoding(data));

        }catch (Exception e) {
            e.printStackTrace();
            String mesg = "rsa 解密失败";
            throw new CryptoExceptions(mesg.hashCode(), mesg);
        }
    }

    /**
     * 公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] decryptByPublicKey(String data,
                                     String   key) throws Exception {
       PublicKey publicKey =   getPublicKey(key);
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(base64Decoding(data));
        }catch (Exception e) {
            e.printStackTrace();
            String mesg = "rsa public 解密失败";
            throw new CryptoExceptions(mesg.hashCode(), mesg);
        }
    }

}
}
