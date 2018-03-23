package net.check321.concurrency.clazz.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author check321
 * @title 多线程并发测试类
 * @description
 * @date 2018/3/20 22:51
 */
@Slf4j
public class ConcurrencyTest {

    boolean lock;

    public boolean isLock() {
        return this.lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 200;
        final Set<String> instances = Collections.synchronizedSet(new HashSet<>());
        final  ConcurrencyTest lock = new ConcurrencyTest();
        lock.setLock(true);

        ExecutorService executors = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            executors.execute(() -> {
                while (true) {
                    if (!lock.isLock()) {
                       String instance = LazySyncSingleton.getInstance().toString();
//                        log.info("{} create new instance: {}",Thread.currentThread().getName(),instance);
                        instances.add(instance);
                        break;
                    }
                }
            });
        }

        Thread.sleep(5000);
        lock.setLock(false);
        Thread.sleep(5000);

        for (String instance : instances) {
            log.info(instance);
        }
        executors.shutdown();

    }
}
