package net.check321.concurrency.clazz.container.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * @auther:fyang
 * @date: 2018/3/26
 * @description: ArrayList -> Vector -> CopyOnWriteArrayList 并发操作测试
 */
@Slf4j
public class ArrayListTest {

    // 并发执行线程数
    public static int threadCount = 100;

    // 执行总次数
    public static int exeCount = 10000;

    // unsafe container
//      public static List<Integer> container = new ArrayList<>();
    public static List<Integer> container = new Vector<>();
    // public static List<Integer> container = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(exeCount);

        for (int i = 0; i < exeCount; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    counting();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("semaphore error: ", e);
                }
                countDownLatch.countDown();
            });

        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("{} container size: {}", container.getClass().getName(), container.size());
    }


    static void counting() {
        // +1
        container.add(1);
    }
}
