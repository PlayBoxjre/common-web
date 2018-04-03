package com.kong.support;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;

public class Log4jTest {

    private Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    @Before
    public void before() {
        LoggerContext iLoggerFactory = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(iLoggerFactory);
    }


    @Test
    public void test() {

        logger.debug("DEBUG:{}", "this is debug log");
        logger.info("info:{}", "this is info log");
        logger.warn("warn:{}", "this is warn log");
        logger.error("error:{}", "this is error log");
        logger.trace("trace:{}", "this is trace log");

    }
}
