package com.kong.support;

import com.kong.support.exceptions.ResourceAccessException;
import com.kong.support.resources.defines.Resource;
import com.kong.support.resources.defines.ResourceBundle;
import com.kong.support.resources.imps.ByteResource;
import com.kong.support.resources.imps.ResourceBundleImp;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * PROJECT     :   commonweb
 * AUTHOR      :   Kong Xiang&Aaron
 * CREATEDAT   :   2018/4/11 0:43 星期三
 * EMAIL       :   playboxjre@Gmail.com
 * DESCRIPTION :
 */
public class ResourceTest {
    Logger logger = LoggerFactory.getLogger(ResourceTest.class);

    @Test
    public void test() {
        String path = "src/main/resources/log4j.properties";
        String path1 = "http://www..com";
        Resource resource = new ByteResource(path1);
        URI uri = resource.getURI();
        byte[] bytes = resource.getBytes();
        String ret = new String(bytes, Charset.forName("ISO-8859-1"));

        logger.info("ret {}", ret);
        Resource local = new ByteResource(path);
         uri = local.getURI();
         bytes = local.getBytes();
         ret = new String(bytes, Charset.forName("ISO-8859-1"));


        logger.info("ret {}", ret);
    }

    @Test
    public void resourceBundleTest() throws ResourceAccessException {
        String path = "src/main/resources/log4j.properties";
        String path1 = "http://www.konvigne.com";
        ResourceBundle resourceBundle = new ResourceBundleImp();
        Resource resource = resourceBundle.getResource(path1);
        byte[] bytes = resource.getBytes();
        if (bytes!=null) {
            String ret = new String(bytes, Charset.forName("UTF-8"));

            logger.info("ret {}", ret);
        }
    }
}
