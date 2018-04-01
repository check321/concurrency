package net.check321.concurrency.clazz.deadLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
* @title 死锁测试
* @description
* @author check321
* @date 2018/3/31 10:49
*/
@Slf4j
public class DeadLockTest implements Runnable{

    // 静态标记模仿多线程公共资源
    boolean commonFlag = false;

    // 锁对象
    public static Object lock1 = new Object(),lock2 = new Object();


    @Override
    public void run() {

        if(commonFlag){
            log.info("t1 is running...");
            synchronized (lock1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("error: ",e);
                }

                log.info("t1 is acquring lock2...");
                synchronized (lock2){
                    log.info("get lock2");
                }
            }

        }

        if(!commonFlag){
            log.info("t2 is running...");

            synchronized (lock2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.info("error2:",e);
                }

                log.info("t2 is acquring lock1...");
                synchronized (lock1){
                    log.info("get lock");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLockTest t1 = new DeadLockTest();
        DeadLockTest t2 = new DeadLockTest();

        t1.commonFlag = true;
        t2.commonFlag =false;

        new Thread(t1).start();
        new Thread(t2).start();
    }
}
