package com.library.config;

import com.library.models.User;
import com.library.models.enums;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
    @Autowired
    UserService userService;


//    @Bean
//    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
//        LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
//        factory.setPersistenceUnitName("h2unit");
//        return factory;
//    }
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
            dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postgres");
            dataSourceBuilder.username("postgres");
            dataSourceBuilder.password("password");

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

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();

    }

    private void seedUsersTable() {
        String sql = "SELECT username, email FROM users U WHERE U.username = \"admin\" OR U.email = \"test@test.com\" LIMIT 1";
        User u = userService.readByUsername("admin");
        //List<User> u = (List<User>) entityManagerFactory().getObject().getCurrentSession().createQuery(sql,User.class).uniqueResult();
        if(u == null ) {
            User user = new User();
            user.setFirstName("Test");
            user.setLastName("Admin");
            user.setUsername("admin");
            user.setEmail("test@test.com");
            user.setPassword("pass");
            user.setAccountType(enums.AccountType.ADMIN);
//                user.setConfirmEmail(true);
            userService.register(user);
            System.out.println("Admin created");
            System.out.println(user);
//                logger.info("Users Seeded");
        } else {

            System.out.println("Admin exists");
//                logger.info("Users Seeding Not Required");
        }
    }
}

