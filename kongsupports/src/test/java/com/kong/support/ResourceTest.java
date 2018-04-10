package com.kong.support;

import com.kong.support.resources.defines.Resource;
import com.kong.support.resources.imps.ByteResource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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
        Resource resource = new ByteResource("src/main/resources/log4j.properties");
        URI uri = resource.getURI();
        byte[] bytes = resource.getBytes();
        String ret = new String(bytes, Charset.forName("ISO-8859-1"));


        logger.info("ret {}", ret);
    }
}
