package com.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author liuxin
 */
@Configuration
@MapperScan(basePackages = "com.dao.local", sqlSessionTemplateRef = "dbLocalSqlSessionTemplate")
public class DSLocalConfig {


    @Bean(name = "dbLocalDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.local")
    @Primary
    public DataSource dbLocalDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory dbLocalSqlSessionFactory(@Qualifier("dbLocalDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/local/*.xml"));
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager dbLocalTransactionManager(@Qualifier("dbLocalDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate dbLocalSqlSessionTemplate(@Qualifier("dbLocalSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

