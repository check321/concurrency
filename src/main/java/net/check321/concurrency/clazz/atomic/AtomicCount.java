package net.check321.concurrency.clazz.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicCount {

    // 并发执行线程数
    public static int threadCount = 100;

    // 执行总次数
    public static int exeCount = 10000;

    // 原子计数器 CAS实现
    public static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(exeCount);

        for (int i = 0; i < exeCount; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("semaphore error: ",e);
                }
                countDownLatch.countDown();
            });

        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("counter: {}", counter);
    }


    static void add(){
        // +1
        counter.incrementAndGet();
    }
}
