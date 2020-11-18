package com.dark.learn.starter.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * project_name: learnstarter
 * package: com.dark.learn.starter.pojo
 * describe: 实体类
 *
 * @author : xue.teng
 * creat_date: 2020-11-18
 * creat_time: 14:52
 **/
@Data
@ConfigurationProperties(prefix = "custom.student")
public class Student {
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;

}
