package net.check321.concurrency.clazz.executors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class FixedExecutorTest {

    public static final int threadCount = 4;

    static final int executeCount = 12;

    static final ExecutorService executors = Executors.newFixedThreadPool(threadCount);

    public static void main(String[] args) {

        try {
            for (int i = 0; i < executeCount; i++) {
                executors.execute(() -> {
                    try {
                        mock();
                    } catch (Exception e) {
                        log.error("error: {}",e.toString());
                    }
                });
            }
        } finally {
            executors.shutdown();
        }
    }

    private static void mock() throws InterruptedException {
        Thread.sleep(1000);
        log.info("[{}] is running...", Thread.currentThread().getId());
        executors.shutdownNow();
    }
}
