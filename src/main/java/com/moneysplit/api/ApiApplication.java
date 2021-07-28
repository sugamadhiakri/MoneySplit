package com.moneysplit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);


    }


    @Override
    public void run(String... args) throws Exception {


        System.out.println(jdbcTemplate.getDataSource().toString());

    }
}
