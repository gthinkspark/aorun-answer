package com.aorun.answer;

import com.aorun.common.util.RedisUtil;
import com.aorun.common.util.jsonp.Jsonp_data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.aorun.answer.dao")
@RestController
@ComponentScan(basePackages={"com.aorun.answer","com.aorun.common"})
public class AorunAnswerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AorunAnswerApplication.class, args);
    }

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/")
    public Object hi(){
        System.out.println(redisUtil.sSet("11","hello word!"));
        System.out.println(redisUtil.get("D79425K28IB81MWy0aB2Tm587PB36854"));
        return Jsonp_data.success("");
    }
}
