package net.check321.concurrency.clazz.singleton;

/**
* @title 饿汉单例
* @description 单例类装载时创建 线程安全
* @author check321
* @date 2018/3/20 22:22
*/
public class HungerySingleton {

    private HungerySingleton(){};

    private static HungerySingleton instance = new HungerySingleton();

    // 静态工厂
    public static HungerySingleton getInstance(){
        return instance;
    }


}
