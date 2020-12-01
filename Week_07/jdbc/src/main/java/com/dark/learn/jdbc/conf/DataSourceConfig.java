package com.dark.learn.jdbc.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
    @Bean
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.second")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.second", name = "enabled", havingValue = "true")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource firstDataSource, DataSource secondDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), firstDataSource);
        targetDataSources.put(DataSourceType.SLAVE.name(), secondDataSource);
        return new DynamicDataSource(firstDataSource, targetDataSources);
    }
}
