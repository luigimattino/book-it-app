/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lmattino.book.it.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Mattinò
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"it.lmattino.book.it.app"})
@EnableJpaRepositories(basePackages="it.lmattino.book.it.app.repositories")
public class BookItApp {

    public static void main(String[] args) {
        SpringApplication.run(BookItApp.class, args);
    }
}
