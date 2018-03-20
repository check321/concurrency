package net.check321.concurrency.clazz.singleton;

/**
 * @author check321
 * @title 懒汉双重同步锁式单例
 * @description 同步化实例获取过程 但因处理器与编译器的指令重排序优化 依然造成线程不安全
 * @date 2018/3/20 22:26
 */
public class LazySyncSingleton {

    private LazySyncSingleton() {
    };

    private static LazySyncSingleton instance;

    /*
    * JVM实例化对象过程：
    * 1、memory = allocate() 分配初始化空间
    * 2、ctorInstance() 初始化对象
    * 3、intance = memory 对象指向分配好的内存空间
    *
    * 因为实例话过程中2、3步骤不存在happens-before关系所以有可能被处理器指令重排序后执行顺序颠倒：
    *
    * 1、memory = allocate() 分配初始化空间
    * 3、intance = memory 对象指向分配好的内存空间
    * 2、ctorInstance() 初始化对象
    *
    * 导致多线程在35行的实例校验时为空但随后立即被其他线程初始化对象，最终造成多个实例创建
    *
    * */
    public static LazySyncSingleton getInstance() {
        if (null == instance) {
            synchronized (LazySyncSingleton.class) {
                if (null == instance) {
                    instance = new LazySyncSingleton();
                }
            }
        }
        return instance;
    }
}
