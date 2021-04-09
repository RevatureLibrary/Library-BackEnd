package com.library;

import com.library.controler.LoginController;
import com.library.models.request.LoginAttempt;
import com.library.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Driver {
    @Autowired
    AuthenticationManager authenticationManager;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Driver.class, args);
    }
}
