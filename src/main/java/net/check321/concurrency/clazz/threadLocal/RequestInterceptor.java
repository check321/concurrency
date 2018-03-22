package net.check321.concurrency.clazz.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @title 请求拦截器 方法结束前销毁线程绑定的信息
* @description
* @author check321
* @date 2018/3/22 23:27
*/
@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        RequestHolder.remove();
        log.info("thread id: {} terminate.",Thread.currentThread().getId());
        return;
    }
}
