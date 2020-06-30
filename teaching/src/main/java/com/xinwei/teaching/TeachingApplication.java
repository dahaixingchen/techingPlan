package com.xinwei.teaching;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan("com.xinwei.teaching")
@SpringBootApplication
public class TeachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachingApplication.class, args);
    }

}
