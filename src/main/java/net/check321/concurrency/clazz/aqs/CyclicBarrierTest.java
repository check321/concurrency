package net.check321.concurrency.clazz.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author check321
 * @title 可循环Barrier
 * @description 可循环线程同步器
 * @date 2018/3/27 23:28
 */
@Slf4j
public class CyclicBarrierTest {

    // 线程数
    private final static int threadCount = 30;

    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        log.info("lambda callback is runing...");
    });

    public static void main(String[] args) throws Exception {
        // 线程池
        ExecutorService executors = Executors.newCachedThreadPool();

        for (int i = 0; i < threadCount; i++) {
            Thread.sleep(1000);
            final int threadNum = i;
            executors.execute(() -> {
                try {
                    execute(threadNum);
                } catch (Exception e) {
                    log.error("error : ", e);
                }
            });
        }

        executors.shutdown();
    }

    private static void execute(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(2000);
        log.info("thread {} get ready!", threadNum);
        cyclicBarrier.await();
        log.info("thread {} finished!",threadNum);
    }
}
