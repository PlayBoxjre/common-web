package com.kong.support.toolbox;

import com.kong.support.exceptions.ClassFormatException;
import com.kong.support.exceptions.CryptoExceptions;
import com.kong.support.exceptions.socket.DataParserException;
import com.kong.support.exceptions.socket.DecodingException;
import com.kong.support.exceptions.socket.EncodingException;
import com.kong.support.exceptions.socket.SocketConnectionException;
import com.kong.support.socket.helper.Cryptor;
import com.kong.support.socket.helper.imps.DESCryptorImpl;
import com.kong.support.socket.helper.imps.JsonDataInteractionLifeCycle;
import com.kong.support.socket.helper.imps.StringDataInteractionLifeCycle;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketException;
import java.nio.charset.Charset;

public class DataInteractionLifeCycleTest {
    Logger logger = LoggerFactory.getLogger(DataInteractionLifeCycleTest.class);

    @Test
    public void test() throws ClassFormatException, CryptoExceptions, EncodingException, SocketConnectionException, DecodingException, DataParserException, SocketException {
        String data = "hello";

        StringDataInteractionLifeCycle dataInteractionLifeCycle
                = new StringDataInteractionLifeCycle();
        byte[] bytes = dataInteractionLifeCycle.buildResponseObject(null,
                data, Charset.defaultCharset());
        String response = new String(bytes,Charset.defaultCharset());
        Assert.assertEquals(response,data);
        logger.info("response : {} ", response);
        String accept = dataInteractionLifeCycle.accept(String.class, null,
                data.getBytes(Charset.defaultCharset()), Charset.defaultCharset());
        Assert.assertEquals(accept,data);
        logger.info("accept :   {}",accept);

    }




    @Test
    public void gsonTest() throws ClassFormatException, CryptoExceptions, EncodingException, SocketConnectionException, DecodingException, DataParserException, SocketException {
        JsonDataInteractionLifeCycle<UserBean> dataInteractionLifeCycle
                 = new JsonDataInteractionLifeCycle();
        Cryptor cryptor = new DESCryptorImpl();
        dataInteractionLifeCycle.setCryptor(cryptor);

        UserBean userBean = new UserBean();
        userBean.setAge(10);
        userBean.setEmail("12344@qq.com");
        userBean.setGender("woman");
        userBean.setName("fangang");
        userBean.setVip(true);
        userBean.setMobile("1222233344");

        byte[] bytes = dataInteractionLifeCycle.buildResponseObject(null,
                userBean, Charset.defaultCharset());
        String jsonUserBean = new String(bytes,Charset.defaultCharset());

        logger.info("user bean json : {}",jsonUserBean);

        UserBean accept = dataInteractionLifeCycle.accept(UserBean.class, null,
                bytes, Charset.defaultCharset());

        Assert.assertEquals(accept,userBean);


    }
}
