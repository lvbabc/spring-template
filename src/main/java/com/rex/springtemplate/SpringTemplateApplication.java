package com.rex.springtemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rex.springtemplate.mapper")
public class SpringTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTemplateApplication.class, args);
    }

}
