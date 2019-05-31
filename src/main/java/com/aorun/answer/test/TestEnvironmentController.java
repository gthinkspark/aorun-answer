package com.aorun.answer.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class TestEnvironmentController {

    @Autowired
    private Environment env;

    @RequestMapping("/testProfile")
    public String testProfile() {
        return env.getProperty("profile");
    }


    @RequestMapping("/testProfile2")
    public String testProfile2() {
        return "testProfile2";
    }


    @RequestMapping("/zero")
    @ResponseBody
    public String zero() {
        System.err.println("Controller测试");
        String info = "除0异常";
        int a = 1 / 0;
        return info;
    }


}
