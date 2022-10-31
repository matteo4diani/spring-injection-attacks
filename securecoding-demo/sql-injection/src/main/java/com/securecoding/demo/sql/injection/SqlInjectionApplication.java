package com.securecoding.demo.sql.injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.securecoding.demo")
@EntityScan(basePackages = "com.securecoding.demo")
public class SqlInjectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlInjectionApplication.class, args);
    }
}
