package com.benbird.bencenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.benbird.bencenter.mapper")
public class BencenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BencenterApplication.class, args);
    }

}
