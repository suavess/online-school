package com.suave.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Suave
 * @date 2021/2/3 2:00 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.suave"})
@MapperScan(basePackages = {"com.suave.acl.mapper"})
public class AclApplication8009 {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication8009.class, args);
    }
}
