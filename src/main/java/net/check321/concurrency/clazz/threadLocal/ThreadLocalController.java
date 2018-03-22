package net.check321.concurrency.clazz.threadLocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @title threadLocal 请求测试接口
* @description
* @author check321
* @date 2018/3/22 23:36
*/
@RestController
@RequestMapping("/threadLocal/")
public class ThreadLocalController {

    @RequestMapping("test")
    public Long test(){
        return RequestHolder.get();
    }
}
