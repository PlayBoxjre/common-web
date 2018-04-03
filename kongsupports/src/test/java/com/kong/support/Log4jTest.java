package com.kong.support;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {

    private Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    @Test
    public void test() {
        logger.debug("DEBUG:{}", "this is debug log");
        logger.info("info:{}", "this is info log");
        logger.warn("warn:{}", "this is warn log");
        logger.error("error:{}", "this is error log");
        logger.trace("trace:{}", "this is trace log");
    }
}
