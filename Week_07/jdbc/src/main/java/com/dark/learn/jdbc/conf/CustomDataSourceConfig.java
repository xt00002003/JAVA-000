/**
 * @author teng.xue
 * @date 2020/12/2
 * @since
 **/
package com.dark.learn.jdbc.conf;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@Slf4j
public class CustomDataSourceConfig {
    /**
     * 这里只封装了
     *  driverClassName
     *  username
     *  password
     *  url
     * @return
     */
    @Bean
    @ConfigurationProperties("first.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource firstDataSource() {
        DataSourceProperties dataSourceProperties = firstDataSourceProperties();
        log.info("first datasource: {}", dataSourceProperties.getUrl());
        DataSource first=dataSourceProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
        return first;
    }

    @Bean
    @Resource
    public PlatformTransactionManager firstTxManager(DataSource firstDataSourceProperties) {
        return new DataSourceTransactionManager(firstDataSourceProperties);
    }

    @Bean
    @ConfigurationProperties("second.datasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource secondDataSource() {
        DataSourceProperties dataSourceProperties = secondDataSourceProperties();
        log.info("second datasource: {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager barTxManager(DataSource secondDataSource) {
        return new DataSourceTransactionManager(secondDataSource);
    }


}
