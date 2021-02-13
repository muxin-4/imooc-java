package com.imooc.spring.ioc;

import com.imooc.spring.ioc.entity.Apple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        // 初始化IOC容器并实例化对象
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Apple apple2 = context.getBean("apple2", Apple.class);
        System.out.println(apple2.getTitle());

    }
}
