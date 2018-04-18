package com.kong.support.toolbox;


import com.kong.support.toolboxes.CryptoTool;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CryptoToolTest {
    Logger logger = LoggerFactory.getLogger(CryptoToolTest.class);

    @Test
    public void showSecurityInfoTest(){
        CryptoTool.showSecurityProvider();
    }
    @Test
    public void base64Test() throws IOException {
        String src = "œœ®®†¥";
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
    public void hmacTest() throws NoSuchAlgorithmException, InvalidKeyException {
        String src = "我是你哥哥";
        String key = "1234551";
        String s = CryptoTool.HmacMD5(src.getBytes(), key.getBytes());
        logger.info("hamc : {} key【{}】\n\t{}\n\tsize {}",src,key,s,s.length());

    }


}
