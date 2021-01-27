package com.suave.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Suave
 * @date 2021/1/27 10:40 上午
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.suave"})
public class VodApplication8003 {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication8003.class, args);
    }
}
