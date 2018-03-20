package net.check321.concurrency.clazz.singleton;

/**
* @title 枚举类单例
* @description 线程安全
* @author check321
* @date 2018/3/20 22:46
*/
public class EnumSingleton {

    private EnumSingleton(){};

    private EnumSingleton instance;

    public static EnumSingleton getInstance(){
        return Sigleton.INSTANCE.getInstance();
    }

    private enum Sigleton{
        INSTANCE;

        private EnumSingleton singleton;

        // JVM保证方法调用一次
        Sigleton(){
            singleton = new EnumSingleton();
        }

        public EnumSingleton getInstance(){
            return singleton;
        }
    }
}
