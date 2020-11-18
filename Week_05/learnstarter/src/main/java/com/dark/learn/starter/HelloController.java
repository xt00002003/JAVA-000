package com.dark.learn.starter;

import com.dark.learn.starter.pojo.CustomObjectAutoConfiguration;
import com.dark.learn.starter.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * project_name: learnstarter
 * package: com.dark.learn.starter
 * describe: web项目测试类
 *
 * @author : xue.teng
 * creat_date: 2020-11-18
 * creat_time: 16:07
 **/
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private Student student;
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @RequestMapping("/helloWorld")
    public String hello(){
        System.out.println("学生类注入的信息是:"+student);
        return "ok";
    }


}
