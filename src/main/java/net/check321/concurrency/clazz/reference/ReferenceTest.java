package net.check321.concurrency.clazz.reference;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

@Slf4j
/**
 * @auther:fyang
 * @date: 2018/3/23
 * @description: Java 4种引用类型测试
 *
 */
public class ReferenceTest {
    public static void main(String[] args) {
        weakReferenceTest();
    }

    /*
    * StrongReference 强引用 默认实现
    * 对象会尽可能长时间的存活，当没有对象指向时，GC才会回收
    *
    * */
    private static void strongReferenceTest() {
        Object object = new Object();
        // 强引用
        Object strongReference = object;

        log.info("before gc,reference: {}", strongReference.toString());
        object = null;
        // 发生GC
        System.gc();
        log.info("after gc ,reference: {}", strongReference.toString());
    }


    /*
    * WeakReference 弱引用
    * 当引用对象在JVM不再有强引用时，WeakReference将会被回收
    *
    * */
    private static void weakReferenceTest() {
        Object object = new Object();
        // 弱引用
        WeakReference<Object> weakReference = new WeakReference<>(object);

        log.info("before gc,reference: {}", weakReference.get().toString());
        // 弱引用引用对象在JVM失去强引用
        object = null;
        System.gc();
        log.info("after gc ,reference: {}", weakReference.get().toString());
    }

    /*
    * SoftReference 软引用
    * 与WeakReference类似，会尽可能的保证对象存活直到JVM发生OOM前会对该引用对象回收，适合缓存功能的实现
    * */
    private static void softReferenceTest() {
        Object object = new Object();
        // 软引用
        SoftReference<Object> softReference = new SoftReference<>(object);


        log.info("before gc,reference: {}", softReference.get().toString());
        object = null;
        System.gc();
        log.info("after gc ,reference: {}", softReference.get().toString());


    }


}
