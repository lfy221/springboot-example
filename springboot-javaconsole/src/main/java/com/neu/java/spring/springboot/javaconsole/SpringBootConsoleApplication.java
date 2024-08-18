package com.neu.java.spring.springboot.javaconsole;

import com.neu.java.spring.springboot.javaconsole.apple.RedApple;
import com.neu.java.spring.springboot.javaconsole.orange.BigOrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    private RedApple apple;
    @Autowired
    private BigOrange orange;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Spring boot is startup...");

        System.out.println(apple.getAppleColor());
        System.out.println(orange.getOrangeColor());
    }
}
