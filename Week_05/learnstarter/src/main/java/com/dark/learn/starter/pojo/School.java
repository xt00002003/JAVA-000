package com.dark.learn.starter.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * project_name: learnstarter
 * package: com.dark.learn.starter.pojo
 * describe: TODO
 *
 * @author : xue.teng
 * creat_date: 2020-11-18
 * creat_time: 14:56
 **/
@Data
@ConfigurationProperties(prefix = "custom.school")
public class School {

    private String name;

}
