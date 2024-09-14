package com.neu.java.spring.jdbc.student.services.impl;

import com.neu.java.spring.jdbc.student.dao.StudentDao;
import com.neu.java.spring.jdbc.student.entity.Student;
import com.neu.java.spring.jdbc.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getStudentList() {
        return studentDao.getStudentList();
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public int insertStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public int deleteStudent(Integer id) {
        return studentDao.deleteStudent(id);
    }
}
