package com.neu.java.spring.springboot.hikari.modules.log.service.impl;

import com.neu.java.spring.springboot.hikari.modules.log.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogService implements ILogService {
    @Override
    public void addLog() {
        log.debug("debug日志11111111");
        log.info("info日志22222222");
        log.warn("warn日志33333333");
        log.error("error日志666666666");
    }

    @Override
    public void addLogMore() {
        while (true) {
            log.debug("debug日志11111111----------");
            log.info("info日志22222222----------");
            log.warn("warn日志33333333----------");
            log.error("error日志666666666----------");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
