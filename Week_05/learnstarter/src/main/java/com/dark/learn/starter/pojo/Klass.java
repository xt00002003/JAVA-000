package com.dark.learn.starter.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * project_name: learnstarter
 * package: com.dark.learn.starter.pojo
 * describe: 班级
 *
 * @author : xue.teng
 * creat_date: 2020-11-18
 * creat_time: 14:55
 **/
@Data
@ConfigurationProperties(prefix = "custom.klass")
public class Klass {

    private String name;

}
