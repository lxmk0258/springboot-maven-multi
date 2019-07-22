package com.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author liuxin
 */
@Configuration
@MapperScan(basePackages = "com.dao.rcd", sqlSessionTemplateRef = "dbRcdSqlSessionTemplate")
public class DSRcdConfig {

    @Bean(name="dbRcdDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.rcd")
    public DataSource dbRcdDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory dbRcdSqlSessionFactory(@Qualifier("dbRcdDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/rcd/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager dbRcdTransactionManager(@Qualifier("dbRcdDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate dbRcdSqlSessionTemplate(@Qualifier("dbRcdSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

