package com.dark.learn.jdbc.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * project_name: jdbc
 * package: com.dark.learn.jdbc.conf
 * describe: TODO
 *
 * @author : xue.teng
 * creat_date: 2020-12-01
 * creat_time: 20:52
 **/
@Configuration
public class DataSourceConfig {

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(@Qualifier("druidDataSource")DataSource firstDataSource, @Qualifier("secondDruidDataSource")DataSource secondDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.FIRST.name(), firstDataSource);
        targetDataSources.put(DataSourceType.SECOND.name(), secondDataSource);
        return new DynamicDataSource(firstDataSource, targetDataSources);
    }

}
