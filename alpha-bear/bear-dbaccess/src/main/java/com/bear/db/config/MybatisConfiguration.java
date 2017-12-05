/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2016 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.bear.db.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bear.db.config.DbContextHolder.DbType;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@ConditionalOnClass({EnableTransactionManagement.class, EntityManager.class})
@AutoConfigureAfter({DataBaseConfiguration.class})
@EntityScan("sse.boss.core.model.db")
@MapperScan(basePackages = {"sse.boss.**.dao"})
public class MybatisConfiguration implements EnvironmentAware {
    private static Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

    @Resource(name = "writeDataSource")
    private DataSource writeDataSource;

    @Resource(name = "readDataSources")
    private List<DataSource> readDataSources;

    private MyAbstractRoutingDataSource dataSource;

    @Override
    public void setEnvironment(Environment environment) {
    }

    @Bean
    public MyAbstractRoutingDataSource roundRobinDataSourceProxy() {
        if (dataSource == null) {
            MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource();
            Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
            proxy.setDefaultTargetDataSource(writeDataSource);
            targetDataSources.put(DbType.READ, readDataSources.get(0));
            targetDataSources.put(DbType.WRITE, writeDataSource);
            proxy.setTargetDataSources(targetDataSources);
            dataSource = proxy;
        }
        logger.info("Configuring round robin data source proxy for read-write separation.");

        return dataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(MyAbstractRoutingDataSource dataSource) {
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            sessionFactory.setConfigLocation(
                    new DefaultResourceLoader().getResource(String.format("/mybatis-config%s.xml", getProfile())));
            logger.debug("Configuring sql session factory.");
            return sessionFactory.getObject();
        } catch (Exception e) {
            logger.warn("Could not configure session factory", e);
            return null;
        }
    }

    public String getProfile() {
        String profile = System.getProperty("PROFILE");
        if (profile == null || profile.trim().equals("")) {
            profile = "";
        } else {
            profile = "-" + profile;
        }
        return profile;
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceTransactionManager transactionManager() {

        logger.debug("Configuring transaction manager for write data source.");
        return new DataSourceTransactionManager(roundRobinDataSourceProxy());
    }
}
