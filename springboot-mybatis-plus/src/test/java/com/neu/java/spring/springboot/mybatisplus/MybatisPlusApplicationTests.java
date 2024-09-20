package com.neu.java.spring.springboot.mybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusApplicationTests {

    private long time;

    @Test
    public void contextLoads() {
        System.out.println("mybatis-plus test contextload");
    }

    @Before
    public void startUp() {
        this.time = System.currentTimeMillis();
        log.info("Spring boot mybatis-plus test start--------->");
    }

    @After
    public void stopTest() {
        log.info("Spring boot mybatis-plus test end---------<>, 用时: {} ms", System.currentTimeMillis() - this.time);
    }

}
