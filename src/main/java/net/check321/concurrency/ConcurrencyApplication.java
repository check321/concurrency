package net.check321.concurrency;

import net.check321.concurrency.clazz.threadLocal.RequestFilter;
import net.check321.concurrency.clazz.threadLocal.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ConcurrencyApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	/*
	* 请求过滤器
	* */
	@Bean
	public FilterRegistrationBean requestFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new RequestFilter());
		registrationBean.addUrlPatterns("/threadLocal/*");
		return registrationBean;
	}

	/*
	* 请求拦截器
	* */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
	}
}
