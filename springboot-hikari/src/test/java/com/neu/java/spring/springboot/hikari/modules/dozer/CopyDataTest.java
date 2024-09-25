package com.neu.java.spring.springboot.hikari.modules.dozer;

import com.neu.java.spring.springboot.hikari.modules.HikariApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CopyDataTest extends HikariApplicationTests {
    @Autowired
    private CopyData copyData;

    @Test
    public void userCopy() {
        UserSource source = new UserSource();
        source.setName("小牛");
        source.setAddress("金融街");

        log.debug("user copy test --debug");
        log.info("user copy test --info");

        UserTarget target = copyData.copyUser(source);
        Assert.assertEquals(source.getName(), target.getName());
        Assert.assertEquals(source.getAddress(), target.getAddress());
    }
}
