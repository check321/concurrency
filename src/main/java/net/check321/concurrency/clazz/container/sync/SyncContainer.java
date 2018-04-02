package net.check321.concurrency.clazz.container.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
/**
  * @auther:fyang
  * @date: 2018/3/26
  * @description: ArrayList、Vector Exception in thread "Thread-0" java.util.ConcurrentModificationException
 *                CopyOnWriteArrayList
 */
public class SyncContainer {

    static List<Integer> container = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        init();

        // 遍历元素
        new Thread(() -> {
            for (Integer item : container) {
                log.info("item: {}",item);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("InterruptedException :", e);
                }
            }
        }).start();

        // 删除一个元素
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("InterruptedException :", e);
            }
            container.remove(2);
        }).start();
    }

    private static void init() {
        for (int i = 0; i < 10; i++) {
            container.add(i);
        }
    }


}
