package com.neu.java.spring.jdbc.student;

import com.neu.java.spring.SpringBootJdbcApplicationTest;
import com.neu.java.spring.jdbc.student.entity.Student;
import com.neu.java.spring.jdbc.student.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Slf4j
@Component
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentTest extends SpringBootJdbcApplicationTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void a_save() {
        Student stu = new Student();
        stu.setName("杨");
        stu.setScore(123);
        stu.setUpdatetime(new Date(System.currentTimeMillis()));

        int row = studentService.insertStudent(stu);
        Assert.assertNotEquals(-1, row);
    }
}
