package net.check321.concurrency.clazz.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class AtimicBoolean {

    // 并发执行线程数
    public static int threadCount = 100;

    // 执行总次数
    public static int exeCount = 10000;

    // 原子Boolean
    public static AtomicBoolean isChanged = new AtomicBoolean();

    public static void main(String[] args) throws InterruptedException {
        // thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(exeCount);

        for (int i = 0; i < exeCount; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    change();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("semaphore error: ",e);
                }
                countDownLatch.countDown();
            });

        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("isChanged: {}", isChanged.get());
    }


    static void change(){
        // 当false时，赋值true
        if(isChanged.compareAndSet(false,true)){
            log.info("variable has changed");
        }
    }

}
