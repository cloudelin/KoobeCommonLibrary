/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.data;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.koobe.common.core.KoobeApplication;

/**
 *
 * @author lyhcode
 * 
 * 2014-1-23 cloude - Use data source {org.apache.commons.dbcp.BasicDataSource} for connection pool mechanism
 * 2014-1-28 cloude - Add lazy initialization
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class KoobeDataServiceContext {

    @Autowired
    KoobeApplication application;

    private final static Logger log = LoggerFactory.getLogger(KoobeDataServiceContext.class);

    private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
//    private static final int POOL_INITSIZE = 10;
//    private static final int POOL_MINIDLE = 10;
//    private static final int POOL_MAXIDLE = 10;
//    private static final int POOL_MAXACTIVE = 50;
    
    @Bean
    @Lazy(value=true)
    public DataSource dataSource() {
    	
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(JDBC_DRIVER_CLASS);
    	dataSource.setUrl(getConnectionUrl());
        log.info("Data source connection url: {}", dataSource.getUrl());
        
//        dataSource.setInitialSize(POOL_INITSIZE);
//        dataSource.setMinIdle(POOL_MINIDLE);
//        dataSource.setMaxIdle(POOL_MAXIDLE);
//        dataSource.setMaxActive(POOL_MAXACTIVE);
        
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1"); // for mysql
        dataSource.setTimeBetweenEvictionRunsMillis(1800000); // half-hour

        dataSource.setRemoveAbandoned(true);
        dataSource.setLogAbandoned(true);
        
        log.info("Inject dataSource {}.", dataSource);
        
        try {
        	log.info("DataSource: {}, InitSize Connection: {}", dataSource, ((BasicDataSource)dataSource).getInitialSize());
        	log.info("DataSource: {}, MaxActive Connection: {}", dataSource, ((BasicDataSource)dataSource).getMaxActive());
        	log.info("DataSource: {}, MaxIdle Connection: {}", dataSource, ((BasicDataSource)dataSource).getMaxIdle());
        	log.info("DataSource: {}, MinIdle Connection: {}", dataSource, ((BasicDataSource)dataSource).getMinIdle());
        	log.info("DataSource: {}, NumActive Connection: {}", dataSource, ((BasicDataSource)dataSource).getNumActive());
        } catch (Exception e) { }
        
        return dataSource;
    }

    @Bean
    @Lazy(value=true)
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//    	jdbcTemplate.setLazyInit(true);
//      jdbcTemplate.execute("use KGL"); // should determined by jdbc conn URL
    	
        log.info("Inject jdbcTemplate {}.", jdbcTemplate);
        return jdbcTemplate;
    }

    @Bean
    @Lazy(value=true)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
    	LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.koobe.common.data");
        return lef;
    }

    @Bean
    @Lazy(value=true)
    public JpaVendorAdapter jpaVendorAdapter() {
    	HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        if (log.isDebugEnabled()) {
        	hibernateJpaVendorAdapter.setShowSql(true);
        }
        hibernateJpaVendorAdapter.setGenerateDdl(false);  // Disable hbm2ddl to keep original MySQL database schema
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    @Lazy(value=true)
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
    
    /**
     * Get the JDBC Connection URL
     * @return connection url
     */
    private String getConnectionUrl() {
        return application.getConfig().getJDBCConnectionUrl();
    }
}
