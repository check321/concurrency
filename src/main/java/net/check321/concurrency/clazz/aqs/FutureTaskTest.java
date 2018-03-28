package net.check321.concurrency.clazz.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author check321
 * @title FutureTask
 * @description
 * @date 2018/3/28 22:55
 */
@Slf4j
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("[{}] caller do something...", Thread.currentThread().getName());
                Thread.sleep(3000);
                return "work complate!";
            }
        });

        // 开启线程执行task
        new Thread(task).start();
        String result = task.get();
        log.info("[{}] main get result: {}", result);
    }
}
