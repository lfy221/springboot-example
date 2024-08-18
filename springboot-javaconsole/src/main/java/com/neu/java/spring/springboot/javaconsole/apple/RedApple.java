package com.neu.java.spring.springboot.javaconsole.apple;

import org.springframework.stereotype.Component;

@Component
public class RedApple {
    public String getAppleColor() {
        System.out.println("apple color red");
        return "red";
    }
}
