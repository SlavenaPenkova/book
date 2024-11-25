package com.tinqin.academy.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.tinqin.academy")
@EnableJpaRepositories("com.tinqin.academy.persistence.repositories")
@EntityScan("com.tinqin.academy.persistence.models")
public class BookApplication {

        public static void main(String[] args) {
            SpringApplication.run(BookApplication.class, args);
        }

    }