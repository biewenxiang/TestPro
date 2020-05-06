package com.bwx.log;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

public class Logtest {
    final static  Logger logger = Logger.getLogger(Logtest.class.getClass().getName());

    public static void main(String[] args) {
        System.out.println("dasssssssss");
        logger.info("sass");
    }
}
