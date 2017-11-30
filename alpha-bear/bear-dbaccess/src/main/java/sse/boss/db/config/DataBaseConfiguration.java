/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2016 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package sse.boss.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AutoConfigureBefore({ MybatisConfiguration.class })
@EnableTransactionManagement
public class DataBaseConfiguration implements EnvironmentAware {

	private RelaxedPropertyResolver propertyResolver;

	private static Logger logger = LoggerFactory.getLogger(DataBaseConfiguration.class);

	@Override
	public void setEnvironment(Environment env) {
		this.propertyResolver = new RelaxedPropertyResolver(env, "jdbc.");
	}

	@Bean(name = "writeDataSource", destroyMethod = "close", initMethod = "init")
	@Primary
	public DataSource writeDataSource() throws SQLException{
		logger.info("Configuring Write DataSource");

		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(propertyResolver.getProperty("url"));
		datasource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
		datasource.setUsername(propertyResolver.getProperty("username"));
		datasource.setPassword(propertyResolver.getProperty("password"));
		datasource.setMaxWait(Long.parseLong(propertyResolver.getProperty("maxWait")));
		datasource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initialSize")));
		datasource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("minIdle")));
		datasource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("maxActive")));
		datasource.setLogAbandoned(Boolean.parseBoolean(propertyResolver.getProperty("logAbandoned")));
		datasource.setRemoveAbandoned(Boolean.parseBoolean(propertyResolver.getProperty("removeAbandoned")));
		datasource.setRemoveAbandonedTimeout(Integer.parseInt(propertyResolver.getProperty("removeAbandonedTimeout")));
		datasource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(propertyResolver.getProperty("timeBetweenEvictionRunMillis")));
		datasource.setMinEvictableIdleTimeMillis(Integer.parseInt(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
		datasource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
		datasource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("testWhileIdle")));
		datasource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("testOnBorrow")));
		datasource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("testOnReturn")));
		datasource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("poolPreparedStatements")));
		datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize")));
		datasource.setFilters(propertyResolver.getProperty("filters"));
		

		logger.debug("Write DataSource Configuration(URL):" + datasource.getUrl());
		logger.debug("Write DataSource Configuration(DRIVER):" + datasource.getDriverClassName());
		logger.debug("Write DataSource Configuration(USERNAME):" + datasource.getUsername());
		logger.debug("Write DataSource Configuration(PASSWORD):" + datasource.getPassword());
		logger.debug("Write DataSource Configuration(MAX_WAIT):" + datasource.getMaxWait());
		logger.debug("Write DataSource Configuration(INITIALSIZE):" + datasource.getInitialSize());
		logger.debug("Write DataSource Configuration(MINIDLE):" + datasource.getMinIdle());
		logger.debug("Write DataSource Configuration(MAX_WAIT):" + datasource.getMaxWait());
		logger.debug("Write DataSource Configuration(MAX_ACTIVE):" + datasource.getMaxActive());
		logger.debug("Write DataSource Configuration(LOG_ABANDONED):" + propertyResolver.getProperty("logAbandoned"));
		logger.debug("Write DataSource Configuration(REMOVE_ABANDONED):" + propertyResolver.getProperty("removeAbandoned"));
		logger.debug("Write DataSource Configuration(REMOVE_ABANDONED_TIMEOUT):" + datasource.getRemoveAbandonedTimeout());
		logger.debug("Write DataSource Configuration(JDBC_TIMEBETWEENEVICTIONRUNMILLIS):" + datasource.getTimeBetweenEvictionRunsMillis());
		logger.debug("Write DataSource Configuration(JDBC_MINEVICTABLEIDLETIMEMILLIS):" + datasource.getMinEvictableIdleTimeMillis());
		logger.debug("Write DataSource Configuration(JDBC_VALIDATIONQUERY):" + datasource.getValidationQuery());
		logger.debug("Write DataSource Configuration(JDBC_TESTWHILEIDLE):" + propertyResolver.getProperty("testWhileIdle"));
		logger.debug("Write DataSource Configuration(JDBC_TESTONBORROW):" + propertyResolver.getProperty("testOnBorrow"));
		logger.debug("Write DataSource Configuration(JDBC_TESTONRETURN):" + propertyResolver.getProperty("testOnReturn"));
		logger.debug("Write DataSource Configuration(JDBC_POOLPREPAREDSTATEMENTS):" + propertyResolver.getProperty("poolPreparedStatements"));
		logger.debug("Write DataSource Configuration(JDBC_MAXPOOLPREPAREDSTATEMENTPERCONNECTIONSIZE):" + datasource.getMaxPoolPreparedStatementPerConnectionSize());
//		logger.debug("Write DataSource Configuration(JDBC_SLAVE_FILTERS):" + datasource.getRemoveAbandonedTimeout());


		return datasource;
	}

	@Bean(name = "readDataSource", destroyMethod = "close", initMethod = "init")
	public DataSource readDataSource() throws SQLException{
		logger.info("Configuring Read DataSource");

		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(propertyResolver.getProperty("slave.url"));
		datasource.setDriverClassName(propertyResolver.getProperty("slave.driverClassName"));
		datasource.setUsername(propertyResolver.getProperty("slave.username"));
		datasource.setPassword(propertyResolver.getProperty("slave.password"));
		datasource.setMaxWait(Long.parseLong(propertyResolver.getProperty("slave.maxWait")));
        datasource.setMaxWait(Long.parseLong(propertyResolver.getProperty("maxWait")));
        datasource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initialSize")));
		datasource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("slave.maxActive")));
		datasource.setLogAbandoned(Boolean.parseBoolean(propertyResolver.getProperty("slave.logAbandoned")));
		datasource.setRemoveAbandoned(Boolean.parseBoolean(propertyResolver.getProperty("slave.removeAbandoned")));
		datasource.setRemoveAbandonedTimeout(Integer.parseInt(propertyResolver.getProperty("slave.removeAbandonedTimeout")));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(propertyResolver.getProperty("slave.timeBetweenEvictionRunMillis")));
        datasource.setMinEvictableIdleTimeMillis(Integer.parseInt(propertyResolver.getProperty("slave.minEvictableIdleTimeMillis")));
        datasource.setValidationQuery(propertyResolver.getProperty("slave.validationQuery"));
        datasource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("slave.testWhileIdle")));
        datasource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("slave.testOnBorrow")));
        datasource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("slave.testOnReturn")));
        datasource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("slave.poolPreparedStatements")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty("slave.maxPoolPreparedStatementPerConnectionSize")));
        datasource.setFilters(propertyResolver.getProperty("filters"));

		logger.debug("Read DataSource Configuration(URL):" + datasource.getUrl());
		logger.debug("Read DataSource Configuration(DRIVER):" + datasource.getDriverClassName());
		logger.debug("Read DataSource Configuration(USERNAME):" + datasource.getUsername());
		logger.debug("Read DataSource Configuration(PASSWORD):" + datasource.getPassword());
		logger.debug("Read DataSource Configuration(MAX_WAIT):" + datasource.getMaxWait());
        logger.debug("Read DataSource Configuration(MAX_WAIT):" + datasource.getMaxWait());
        logger.debug("Read DataSource Configuration(INITIALSIZE):" + datasource.getInitialSize());
		logger.debug("Read DataSource Configuration(MAX_ACTIVE):" + datasource.getMaxActive());
		logger.debug("Read DataSource Configuration(LOG_ABANDONED):" + propertyResolver.getProperty("slave.logAbandoned"));
		logger.debug("Read DataSource Configuration(REMOVE_ABANDONED):" + propertyResolver.getProperty("slave.removeAbandoned"));
		logger.debug("Read DataSource Configuration(REMOVE_ABANDONED_TIMEOUT):" + datasource.getRemoveAbandonedTimeout());
        logger.debug("Read DataSource Configuration(JDBC_TIMEBETWEENEVICTIONRUNMILLIS):" + datasource.getTimeBetweenEvictionRunsMillis());
        logger.debug("Read DataSource Configuration(JDBC_MINEVICTABLEIDLETIMEMILLIS):" + datasource.getMinEvictableIdleTimeMillis());
        logger.debug("Read DataSource Configuration(JDBC_VALIDATIONQUERY):" + datasource.getValidationQuery());
        logger.debug("Read DataSource Configuration(JDBC_TESTWHILEIDLE):" + propertyResolver.getProperty("slave.testWhileIdle"));
        logger.debug("Read DataSource Configuration(JDBC_TESTONBORROW):" + propertyResolver.getProperty("slave.testOnBorrow"));
        logger.debug("Read DataSource Configuration(JDBC_TESTONRETURN):" + propertyResolver.getProperty("slave.testOnReturn"));
        logger.debug("Read DataSource Configuration(JDBC_POOLPREPAREDSTATEMENTS):" + propertyResolver.getProperty("slave.poolPreparedStatements"));
        logger.debug("Read DataSource Configuration(JDBC_MAXPOOLPREPAREDSTATEMENTPERCONNECTIONSIZE):" + datasource.getMaxPoolPreparedStatementPerConnectionSize());

		return datasource;
	}

	@Bean(name = "readDataSources")
	public List<DataSource> readDataSources() throws SQLException{
		List<DataSource> dataSources = new ArrayList<DataSource>();
		dataSources.add(readDataSource());

		logger.info("Read only data source count: " + dataSources.size());

		return dataSources;
	}

}
