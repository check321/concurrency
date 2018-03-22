package net.check321.concurrency.clazz.threadLocal;


/**
 * @author check321
 * @title 利用ThreadLocal线程封闭特性绑定每个线程ID到容器
 * @description
 * @date 2018/3/22 22:24
 */
public class RequestHolder {
    // 请求容器
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void set(Long id) {
        requestHolder.set(id);
    }

    public static Long get() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
