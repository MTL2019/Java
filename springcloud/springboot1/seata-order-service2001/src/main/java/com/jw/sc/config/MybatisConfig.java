package com.jw.sc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.jw.sc.dao"})
public class MybatisConfig {
}
