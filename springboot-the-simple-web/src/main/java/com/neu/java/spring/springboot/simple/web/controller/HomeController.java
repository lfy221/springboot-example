package com.neu.java.spring.springboot.simple.web.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class HomeController {

    @RequestMapping(value = "/web", method = RequestMethod.GET)
    @ResponseBody
    public String simples() {
        return "hello spring boot";
    }

    @RequestMapping(value = "/more", method = RequestMethod.POST)
    @ResponseBody
    public String more(@RequestBody User user) {

        return "hello spring boot: " + user.getId();
    }
}
