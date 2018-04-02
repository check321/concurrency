package net.check321.concurrency.clazz.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author check321
 * @title AQS同步组件-同步屏障
 * @description 可循环线程同步器
 * @date 2018/3/27 23:28
 */
@Slf4j
public class CyclicBarrierTest {

    // 线程数
    private final static int threadCount = 5;

    // 该构造参数为barrier-action 在线程到达屏障后优先执行该函数逻辑
    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, () -> {
        log.info("callback is runing...");
    });

    // 该构造参数为barrier-action 在线程到达屏障后优先执行该函数逻辑
    private final static CyclicBarrier mainBarrier = new CyclicBarrier(1, () -> {
        log.info("mainBarrier callback is runing...");
        log.info("final barrier: {}",cyclicBarrier.getNumberWaiting());
    });

    public static void main(String[] args) throws Exception {

        // 线程池
        ExecutorService executors = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < threadCount; i++) {
                final int threadNum = i;
                executors.execute(() -> {
                    try {
                        execute(threadNum);
                    } catch (Exception e) {
                        log.error("error : ", e);
                    }
                });
            }
        } finally {
            executors.shutdown();
        }
    }

    private static void execute(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(2000);
        log.info("thread {} get ready!", threadNum);
        cyclicBarrier.await();
        log.info("thread {} finished!", threadNum);
        if(threadNum == threadCount - 1){
            mainBarrier.await();
        }
    }
}
