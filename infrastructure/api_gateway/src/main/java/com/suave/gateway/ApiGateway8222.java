package com.suave.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Suave
 * @date 2021/2/3 10:03 上午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateway8222 {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway8222.class, args);
    }
}
