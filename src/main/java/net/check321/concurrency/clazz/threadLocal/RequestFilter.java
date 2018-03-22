package net.check321.concurrency.clazz.threadLocal;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/** 
* @title 在请求前将id与当前线程绑定
* @description 
* @author check321 
* @date 2018/3/22 23:28
*/ 
@Slf4j
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("Request path: {} ,id: {}",request.getServletPath(),Thread.currentThread().getId());
        RequestHolder.set(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
