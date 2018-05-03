package com.kong.support.toolbox;


import com.kong.support.exceptions.CryptoExceptions;
import com.kong.support.toolboxes.CryptoTool;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

public class CryptoToolTest {
    Logger logger = LoggerFactory.getLogger(CryptoToolTest.class);

    @Test
    public void showSecurityInfoTest(){
        CryptoTool.showSecurityProvider();
    }
    @Test
    public void base64Test() throws IOException {
        String src = "-";
        String encode = CryptoTool.base64Encoding(src.getBytes(Charset.forName("UTF-8")));
        logger.info("{} encode [{}] " ,src, encode);
        byte[] bytes = CryptoTool.base64Decoding(encode);
        assert src.equals(new String(bytes,Charset.forName("UTF-8")));
    }

    @Test
    public void mdTest() throws NoSuchAlgorithmException {
        String src = "3";
       String md2= CryptoTool.MD2(src.getBytes());
        logger.info("{} \n size : {} ",md2,md2.length());

        String md5 = CryptoTool.MD5(src.getBytes());
        logger.info("{} \n size : {} ",md5,md5.length());

    }


    @Test
    public void shaTest() throws NoSuchAlgorithmException {
        String src = "hhhe23132";
        String sha1 = CryptoTool.SHA1(src.getBytes());
        logger.info("sha1 : 【{}】: 【{}】\n size = [{}]",src,sha1,sha1.length());

        String sha256 = CryptoTool.SHA256(src.getBytes());
        logger.info("sha1 : 【{}】: 【{}】\n size = [{}]",src,sha256,sha256.length());

        String sha384 = CryptoTool.SHA384(src.getBytes());
        logger.info("sha1 : 【{}】: 【{}】\n size = [{}]",src,sha384,sha384.length());

        String sha512 = CryptoTool.SHA512(src.getBytes());
        logger.info("sha1 : 【{}】: 【{}】\n size = [{}]",src,sha512,sha512.length());
    }

    @Test
    public void hmacTest() throws Exception {
        String src = "我是你哥哥";
        String key = "12345d51";
        String s = CryptoTool.HmacSHA1(src.getBytes(), key.getBytes());
        logger.info("hamc : {} key【{}】\n\t{}\n\tsize {}",src,key,s.toUpperCase(),s.length());

    }

    @Test
    public void generateKeyTest() throws Exception {
        String src = "我是你哥哥";
        String key = "12341234af";
        SecretKey s = (SecretKey) CryptoTool.generateSecretKey("1567877787".getBytes(),"AES");
        logger.info(" : {} key【{}】\n\t{}\n\tsize {}", src, key, s.getEncoded(), s.getFormat());

        byte[] bytes = CryptoTool.AESEncrypt(src.getBytes(), key);
        logger.info("des 加密 {}",CryptoTool.base64Encoding(bytes));
        byte[] bytes1 = CryptoTool.AESDecrypt(bytes, key);
        logger.info("src {} == {} ",src,new String(bytes1));

        assert src.equals(new String(bytes1));

        byte[] bytes2 = CryptoTool.AESEncrypt(src.getBytes(), key);
        logger.info("aes 加密 {}",CryptoTool.base64Encoding(bytes2));
        byte[] bytes3 = CryptoTool.AESDecrypt(bytes, key);
        logger.info("src {} == {} ",src,new String(bytes3));

        assert src.equals(new String(bytes3));


    }



    @Test
    public void rsaTest() throws NoSuchAlgorithmException {
        CryptoTool.RSA rsa = CryptoTool.RSA().initRSAKey();
        String privateKey = rsa.getPrivateKey();
        String publicKey = rsa.getPublicKey();
       // logger.info("public : {}",publicKey);
        //logger.info("private : {}",privateKey);


        String data = "hello ~ world !!! 我交孔翔";
        try {
            //公钥加密，私钥解密
            String publicEn = rsa.encryptByPublicKey(data, publicKey);
            logger.info("en pul: {}",publicEn);
            byte[] rsult = rsa.decryptByPrivateKey(publicEn, privateKey);
            logger.info("r {}",new String(rsult));


            Assert.assertEquals(new String(rsult),data);
            // 私钥加密 公钥解密
            String privateEn = rsa.encryptByPrivateKey(data,privateKey);
            logger.info("en pri {}",privateEn);
            byte[] bypublicKey = rsa.decryptBypublicKey(privateEn, publicKey);

            Assert.assertEquals(new String(bypublicKey),data);


        } catch (CryptoExceptions cryptoExceptions) {
            cryptoExceptions.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    }
