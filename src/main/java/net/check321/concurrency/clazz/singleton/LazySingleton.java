package net.check321.concurrency.clazz.singleton;

/**
* @title 懒汉单例 实例第一次使用创建
* @description 线程不安全
* @author check321
* @date 2018/3/20 22:17
*/
public class LazySingleton implements Singleton<LazySingleton>{

    // 私有化构造
    private LazySingleton(){};

    // 单例对象
    private static LazySingleton instance;

    public LazySingleton getInstance(){
        if(null == instance){
            instance = new LazySingleton();
        }
        return instance;
    };
}
