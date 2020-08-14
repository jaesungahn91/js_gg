package com.home.js_gg.config.db;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * DB 및 JPA 관련 설정 Config
 *
 * @author ahnjs
 * @pkg name co.worker.studyfarm
 * @file name DatabaseConfig.java
 * @date 2020. 7. 20
 */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages= {"co.worker.studyfarm.repository"})
@PropertySource("classpath:js.properties")
public class DatabaseConfig {

    @Value("${}")
    private String driver;
    @Value("${}")
    private String url;
    @Value("${}")
    private String id;
    @Value("${}")
    private String pwd;

    /**
     * pgsql DataSource
     *
     * @return the data source
     */
    @Bean(name="dataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(id);
        dataSource.setPassword(pwd);

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
    @Bean
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
        entityManagerFactoryBean.setPackagesToScan("co.worker");
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());

        return entityManagerFactoryBean;
    }


    private Properties jpaHibernateProperties(){
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "");
        props.setProperty("hibernate.show_sql", "false");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "none");

        String databaseScheme = "";
//        if(!Strings.isNullOrEmpty(databaseScheme)){
//            props.setProperty("hibernate.default_schema", "");
//        }

        props.setProperty("hibernate.cache.use_second_level_cache", "true");
        props.setProperty("hibernate.cache.use_query_cache", "true");
        props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        props.setProperty("hibernate.c3p0.min_size", "1");
        props.setProperty("hibernate.c3p0.max_size", "5");
        props.setProperty("hibernate.c3p0.timeout", "10");
        props.setProperty("hibernate.c3p0.idle_test_period", "60000");
        return props;
    }
}
