package com.example.boottest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.LockSupport;

public class FutureTest {
    private static final Logger logger = LoggerFactory.getLogger(FutureTest.class);

    @Test
    public void testSupplyAsync() {
        CompletableFuture<Integer> futureInt = CompletableFuture.supplyAsync(() -> logSomething());
        logger.info("int:{}", futureInt.join());
    }

    private static int logSomething() {
        logger.info("=====enter future");
        LockSupport.parkNanos(1000_000_000L);
        logger.info("=====end future");
        return 10;
    }
}
