package com.dark.learn.jdbc.conf;

import java.lang.annotation.*;

/**
 * project_name: jdbc
 * package: com.dark.learn.jdbc.conf
 * describe: 自定义数据源
 *
 * @author : xue.teng
 * creat_date: 2020-12-01
 * creat_time: 20:56
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomDataSource {

    /**
     * 切换数据源名称
     */
    DataSourceType value() default DataSourceType.MASTER;
}
