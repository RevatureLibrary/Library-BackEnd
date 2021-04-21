package com.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootApplication(exclude = {  HibernateJpaAutoConfiguration.class,
                                    H2ConsoleAutoConfiguration.class}
                        )
public class Driver {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Driver.class, args);

    }

}
