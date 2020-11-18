package com.dark.learn.starter.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * project_name: learnstarter
 * package: com.dark.learn.starter.pojo
 * describe: 自动装配类
 *
 * @author : xue.teng
 * creat_date: 2020-11-18
 * creat_time: 15:32
 **/
@Configuration
@EnableConfigurationProperties({Student.class,Klass.class,School.class})
public class CustomObjectAutoConfiguration {

}
