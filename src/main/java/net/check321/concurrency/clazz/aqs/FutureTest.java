package net.check321.concurrency.clazz.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
* @title Future 获取线程方法执行结果
* @description
* @author check321
* @date 2018/3/28 22:43
*/
@Slf4j
public class FutureTest {

   private static class Caller implements Callable<String>{
       @Override
       public String call() throws Exception {
           log.info("[{}] caller do something...",Thread.currentThread().getName());
           Thread.sleep(3000);
           return "work complate!";
       }
   }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       ExecutorService executors =  Executors.newCachedThreadPool();
       // 接收callable方法返回结果
       Future<String> future = executors.submit(new Caller());
       String result = future.get();
       log.info("[{}] get result: {}",Thread.currentThread().getName(),result);
       executors.shutdown();
    }
}
