package net.check321.concurrency.clazz.singleton;

/**
* @title 饿汉式静态块加载
* @description 线程安全
* @author check321
* @date 2018/3/20 22:44
*/
public class HungeryStaticSingleton {

    private HungeryStaticSingleton(){};

    private static HungeryStaticSingleton instance = null;

    // 由JVM保证类加载时调用一次，故线程安全
    static {
        instance = new HungeryStaticSingleton();
    }

    // 静态工厂
    public static HungeryStaticSingleton getInstance(){
        return instance;
    }
}
