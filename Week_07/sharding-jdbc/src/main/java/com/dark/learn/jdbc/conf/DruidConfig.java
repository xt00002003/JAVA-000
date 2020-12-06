/**
 * @author teng.xue
 * @date 2020/11/29
 * @since
 **/
package com.dark.learn.jdbc.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    @Bean
    /**
     * 使用这个注解获取配置文件中的信息，直接封账成DruidDataSource
     */
    @ConfigurationProperties(prefix = "first.datasource")
    public DataSource druidDataSource() {
        DruidDataSource first=new DruidDataSource();
        return first;
    }

    @Bean
    /**
     * 使用这个注解获取配置文件中的信息，直接封账成DruidDataSource
     */
    @ConfigurationProperties(prefix = "second.datasource")
    public DataSource secondDruidDataSource() {
        DruidDataSource second=new DruidDataSource();
        return second;
    }

}
