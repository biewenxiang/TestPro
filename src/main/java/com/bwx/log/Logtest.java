package com.bwx.log;

import org.slf4j.LoggerFactory;

public class Logtest {
//    final static  Logger logger = Logger.getLogger(Logtest.class.getClass().getName());
    private final static org.slf4j.Logger logger= LoggerFactory.getLogger(Logtest.class);
    public static void main(String[] args) {
        System.out.println("dasssssssss");
        logger.info("sass");
        Object fileDir =  "s";
        logger.info("{}不是目录，不需要处理。", fileDir);
        logger.info("{}不是目录，不需要处理。", fileDir);
        logger.error("{} topic--- {}站点", "1", "2",new  Exception("sss"));
        logger.info("{}不是目录，不需要处理。", fileDir);

    }
}
