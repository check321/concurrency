package net.check321.concurrency.clazz.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author check321
 * @title 信号量
 * @description 利用信号量可控制对同一资源的线程并发量
 * @date 2018/3/27 22:47
 */
@Slf4j
public class SemaphoreTest {
    // 线程数
    private final static int threadCount = 30;

    public static void main(String[] args) throws Exception {

        // 信号量
        final Semaphore semaphore = new Semaphore(3);
        // 线程池
        ExecutorService executors = Executors.newCachedThreadPool();

        for (int i = 0; i < threadCount; i++) {
            final int param = i;
            executors.execute(() -> {
                try {
                    semaphore.tryAcquire(20000,TimeUnit.MILLISECONDS); // 尝试加锁
                    execute(param);
                    semaphore.release(); // 释放锁
                } catch (Exception e) {
                    log.error("error : ", e);
                }
            });
        }

        executors.shutdown();
    }

    private static void execute(int param) throws InterruptedException {
        Thread.sleep(2000);
        log.info("receive parameter: {}", param);
    }
}
