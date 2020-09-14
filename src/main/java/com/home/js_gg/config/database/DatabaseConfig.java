package com.home.js_gg.config.database;

import com.google.common.base.Strings;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * DB 및 JPA 관련 설정 Config
 *
 * @author ahnjs
 * @file name DatabaseConfig.java
 * @date 2020. 7. 20
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages= {"com.home.js_gg.repository"})
@PropertySource("classpath:js.properties")
public class DatabaseConfig {

    @Value("${database.driver}")
    private String driver;
    @Value("${database.url}")
    private String url;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("${database.dialect}")
    private String dialect;
    @Value("${database.defalut.schema}")
    private String schema;
    @Value("${database.ddl.auto}")
    private String auto;

//    @Primary
//    @Bean(name = "dataSource")
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driver);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    @Primary
//    @Bean(name = "jpaProperties")
//    @ConfigurationProperties(prefix = "spring.jpa")
//    public JpaProperties jpaProperties(){
//        return new JpaProperties();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("dataSource") DataSource dataSource,
//            @Qualifier("jpaProperties") JpaProperties jpaProperties
//    ){
//        return builder
//                .dataSource(dataSource)
//                .packages("com.home.js_gg")
//                .properties(jpaProperties.getProperties())
//                .persistenceUnit("default")
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
//    ){
//        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory.getObject());
//        transactionManager.setNestedTransactionAllowed(true);
//        return transactionManager;
//    }

    @Bean(name = "dataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    /**
     * Jpa transaction manager jpa transaction manager.
     *
     * @param entityManagerFactoryBean the entity manager factory bean
     * @return the jpa transaction manager
     */
    @Bean
    @Autowired
    public JpaTransactionManager jpaTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

    /**
     * Entity manager factory bean local container entity manager factory bean.
     *
     * @return the local container entity manager factory bean
     */
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        HibernateJpaVendorAdapter jpsAdapter = new HibernateJpaVendorAdapter();
////        String dbmode = System.getenv("");
////        boolean bDbDevMode = StringUtil.toBoolean(dbmode);
////        if(bDbDevMode){
////            jpsAdapter.setShowSql(true);
////        }
//        entityManagerFactoryBean.setJpaVendorAdapter(jpsAdapter);
//        entityManagerFactoryBean.setDataSource(dataSource());
//        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        entityManagerFactoryBean.setPackagesToScan("com.home.js_gg");
//        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
//
//        return entityManagerFactoryBean;
//    }
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter jpsAdapter = new HibernateJpaVendorAdapter();
//        String dbmode = System.getenv("");
//        boolean bDbDevMode = StringUtil.toBoolean(dbmode);
//        if(bDbDevMode){
//            jpsAdapter.setShowSql(true);
//        }
        entityManagerFactoryBean.setJpaVendorAdapter(jpsAdapter);
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("com.home.js_gg");
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());

        return entityManagerFactoryBean;
    }

    private Properties jpaHibernateProperties(){
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", dialect);
        props.setProperty("hibernate.hbm2ddl.auto", auto);
        props.setProperty("hibernate.show_sql", "false");
        props.setProperty("hibernate.format_sql", "true");
        if (!Strings.isNullOrEmpty(schema)){
            props.setProperty("hibernate.default_schema", schema);
        }
//        props.setProperty("hibernate.cache.use_second_level_cache", "true");
//        props.setProperty("hibernate.cache.use_query_cache", "true");
//        props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
//        props.setProperty("hibernate.c3p0.min_size", "1");
//        props.setProperty("hibernate.c3p0.max_size", "5");
//        props.setProperty("hibernate.c3p0.timeout", "10");
//        props.setProperty("hibernate.c3p0.idle_test_period", "60000");
        return props;
    }
}
