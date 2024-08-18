package com.neu.java.spring.springboot.javaconsole.orange;

import org.springframework.stereotype.Component;

@Component
public class BigOrange {
    public String getOrangeColor() {
        System.out.println("color is orange");
        return "orange";
    }
}
