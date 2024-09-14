package com.neu.java.spring.jdbc.student.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Student {
    private Integer id;

    private String name;

    private Integer score;

    private Date updatetime;
}
