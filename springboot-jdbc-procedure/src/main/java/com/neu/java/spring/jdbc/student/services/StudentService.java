package com.neu.java.spring.jdbc.student.services;

import com.neu.java.spring.jdbc.student.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudentList();

    Student getStudentById(Integer id);

    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(Integer id);
}
