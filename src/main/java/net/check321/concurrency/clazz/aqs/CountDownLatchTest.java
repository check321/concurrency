package net.check321.concurrency.clazz.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author check321
 * @title AQS同步组件--CountDownLatch
 * @description 允许等待一个或多个线程等待其它线程完成操作
 * @date 2018/3/27 21:59
 */
@Slf4j
public class CountDownLatchTest {

    // 线程数
    private final static int threadCount = 5;

    public static void main(String[] args) throws Exception {

        // latch
        final CountDownLatch latch = new CountDownLatch(threadCount);
        // 线程池
        ExecutorService executors = Executors.newCachedThreadPool();

        for (int i = 0; i < threadCount; i++) {
            final int param = i;
            executors.execute(()->{
                try{
                    execute(param);
                }catch (Exception e){
                    log.error("error : ",e);
                }finally {
                    latch.countDown();
                }
            });
        }

        // 等待latch倒数为0
        latch.await(1000,TimeUnit.MILLISECONDS);
        log.info("execute finish");
        executors.shutdown();
    }

    private static void execute(int param) throws InterruptedException {
        if(param == 3){
            Thread.sleep(2000);
        }
        log.info("receive parameter: {}", param);
    }
}
