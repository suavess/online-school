package com.suave.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Suave
 * @date 2021/1/24 11:29 下午
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.suave"})
public class EduApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication8001.class, args);
    }
}
