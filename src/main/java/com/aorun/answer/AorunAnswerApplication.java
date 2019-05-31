package com.aorun.answer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.aorun.answer.dao")
public class AorunAnswerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AorunAnswerApplication.class, args);
    }

}
