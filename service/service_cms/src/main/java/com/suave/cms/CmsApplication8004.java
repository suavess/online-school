package com.suave.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Suave
 * @date 2021/1/29 10:38 上午
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.suave"})
@MapperScan(basePackages = {"com.suave.cms.mapper"})
public class CmsApplication8004 {
}
