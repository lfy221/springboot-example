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

import java.util.Date;
import java.util.List;

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
        stu.setUpdatetime(new Date());

        int row = studentService.insertStudent(stu);
        Assert.assertNotEquals(-1, row);
    }

    @Test
    public void b_query() {
        List<Student> list = studentService.getStudentList();

        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void c_update() {
        List<Student> list = studentService.getStudentList();
        Assert.assertTrue(list.size() > 0);

        Student student = list.get(list.size() - 1);
        String name = "牛";
        Assert.assertNotEquals(student.getName(), name);

        student.setName(name);
        studentService.updateStudent(student);

        Student afterUpdate = studentService.getStudentById(student.getId());
        Assert.assertEquals(name, afterUpdate.getName());
    }

    @Test
    public void d_delete() {
        List<Student> list = studentService.getStudentList();
        int size = list.size();
        Assert.assertTrue(size > 0);

        Student student = list.get(list.size() - 1);
        int res = studentService.deleteStudent(student.getId());
        Assert.assertTrue(res > -1);

        Student afterDelete = studentService.getStudentById(student.getId());
        Assert.assertNull(afterDelete);
    }
}
