package net.check321.concurrency.clazz.singleton;

/**
* @title 双重锁懒汉式，使用volatile修饰实例，禁止指令重拍
* @description 线程安全
* @author check321
* @date 2018/3/20 22:40
*/
public class LazySyncSingleton2 {
    private LazySyncSingleton2() {
    };

    // 保证变量内存可见性
    private volatile static LazySyncSingleton2 instance;

    /*
    * JVM实例化对象过程：
    * 1、memory = allocate() 分配初始化空间
    * 2、ctorInstance() 初始化对象
    * 3、intance = memory 对象指向分配好的内存空间
    *
    * */
    public static LazySyncSingleton2 getInstance() {
        if (null == instance) {
            synchronized (LazySyncSingleton2.class) {
                if (null == instance) {
                    instance = new LazySyncSingleton2();
                }
            }
        }
        return instance;
    }
}
