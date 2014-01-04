/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.data;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.service.KoobeService;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Driver;
import java.util.logging.Level;

/**
 *
 * @author lyhcode
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class KoobeDataServiceContext {

    @Autowired
    KoobeApplication application;

    private final static Logger log = LoggerFactory.getLogger(KoobeDataServiceContext.class);

    private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";

    /**
     * Get the JDBC Connection URL
     * @return connection url
     */
    private String getConnectionUrl() {
        return application.getConfig().getJDBCConnectionUrl();
    }

    /**
     * Get JdbcTemplate instance from Spring-JDBC
     * @return jdbcTemplate
     */
    private JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        jdbcTemplate.execute("use KGL");

        log.info("JdbcTemplate created.");
        return jdbcTemplate;
    }

    /**
     * Get Data Source Object
     * @return dataSource
     */
    private DataSource getDataSource() {

        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            dataSource.setDriverClass((Class<Driver>) Class.forName(JDBC_DRIVER_CLASS));

            log.info("Data source connection url: {}", getConnectionUrl());
            dataSource.setUrl(getConnectionUrl());


            //dataSource.setUsername();
            //dataSource.setPassword();
            return dataSource;
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KoobeDataService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = getDataSource();
        log.info("Inject dataSource {}.", dataSource);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        log.info("Inject jdbcTemplate {}.", jdbcTemplate);
        return jdbcTemplate;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.koobe.common.data");
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(false);  // Disable hbm2ddl to keep original MySQL database schema
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

}
