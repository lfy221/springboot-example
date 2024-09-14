package com.neu.java.spring.jdbc.student.dao;

import com.neu.java.spring.jdbc.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> getStudentList() {
        String sql = "select * from student";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student getStudentById(Integer id) {
        String sql = "select * from student t where t.id = ?";

        final Student student = new Student();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                student.setId(id);
                student.setName(resultSet.getString("name"));
                student.setScore(resultSet.getInt("score"));
                student.setUpdatetime(resultSet.getDate("updatetime"));
            }
        }, id);

        return student.getId() == null ? null : student;
    }

    public int insertStudent(Student student) {
        String sql = "insert into `student`\n" +
                "(`id`,`name`,`score`,`updatetime`)" +
                "values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getId(), student.getName(), student.getScore(), student.getUpdatetime());
    }

    public int updateStudent(Student student) {
        String sql = "update student set name = ?, score = ?, updatetime = ? where id = ?";

        return jdbcTemplate.update(sql, student.getName(), student.getScore(), student.getUpdatetime(), student.getId());
    }

    public int deleteStudent(Integer id) {
        String sql = "delete from student where id = ?";

        return jdbcTemplate.update(sql, id);
    }
}
