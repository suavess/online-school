package com.suave.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Suave
 * @date 2021/1/24 11:52 下午
 */
@Configuration
@MapperScan("com.suave.edu.mapper")
public class MybatisPlusConfiguration {
    @Bean
    public ISqlInjector iSqlInjector() {
        return new LogicSqlInjector();
    }
}
