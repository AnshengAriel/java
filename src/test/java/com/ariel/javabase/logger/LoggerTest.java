package com.ariel.javabase.logger;

import org.junit.Test;

import java.util.logging.Logger;

public class LoggerTest {

    @Test
    public void getGlobal() {
        Logger log = Logger.getGlobal();
        log.info("hello info");
        log.warning("warning");
        log.fine("ignored");
        log.severe("servere");
    }

    @Test
    public void test() {
        Logger log = Logger.getLogger(LoggerTest.class.getName());
        log.info("hello info");
        log.warning("warning");
        log.fine("ignored");
        log.severe("servere");
    }

}
