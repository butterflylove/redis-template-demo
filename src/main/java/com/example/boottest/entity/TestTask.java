package com.example.boottest.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangtianlong on 20/1/20.
 */
public class TestTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

    @Override
    public void run() {
        logger.info("Test scheduledExecutorService...");
    }
}
