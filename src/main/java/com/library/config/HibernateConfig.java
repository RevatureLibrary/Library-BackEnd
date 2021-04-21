package com.library.config;

import com.library.models.Department;
import com.library.models.Library;
import com.library.models.User;
import com.library.models.enums;
import com.library.services.DepartmentService;
import com.library.services.LibraryService;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {


    @Bean
    public LocalSessionFactoryBean entityManagerFactory() {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.*");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

        @Bean
        public DataSource dataSource() {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName("org.postgresql.Driver");
            dataSourceBuilder.url("jdbc:postgresql://database-2.cmidvpydcvzn.us-east-2.rds.amazonaws.com:5432/postgres");
            dataSourceBuilder.username("postgres");
            dataSourceBuilder.password("Pos12345");

            return dataSourceBuilder.build();
        }


        @Bean
        public PlatformTransactionManager transactionManager() {
            HibernateTransactionManager transactionManager
                    = new HibernateTransactionManager();
            transactionManager.setSessionFactory(entityManagerFactory().getObject());
            return transactionManager;
        }

        private final Properties hibernateProperties() {
            Properties hibernateProperties = new Properties();
            hibernateProperties.setProperty(
                    "hibernate.hbm2ddl.auto", "create-drop");
            hibernateProperties.setProperty(
                    "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

            return hibernateProperties;
        }

}

