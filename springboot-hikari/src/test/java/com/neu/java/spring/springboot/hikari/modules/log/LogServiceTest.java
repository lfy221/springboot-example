package com.neu.java.spring.springboot.hikari.modules.log;

import com.neu.java.spring.springboot.hikari.modules.HikariApplicationTests;
import com.neu.java.spring.springboot.hikari.modules.log.service.ILogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogServiceTest extends HikariApplicationTests {
    @Autowired
    ILogService logService;

//    @Test
    public void addLog() {
        logService.addLog();
    }

    @Test
    public void addLogMore() {
        logService.addLogMore();
    }
}
