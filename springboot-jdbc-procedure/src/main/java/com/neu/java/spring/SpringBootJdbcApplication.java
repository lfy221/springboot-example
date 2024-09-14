package com.neu.java.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJdbcApplication implements CommandLineRunner {
    public static void main(String[] args) {
        System.out.println("Hello jdbc!");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start jdbc procedure");
    }
}
