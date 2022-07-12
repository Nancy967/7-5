package com.example.demo.mapper;

import com.example.demo.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setScore(resultSet.getDouble("score"));
        student.setGraduate(resultSet.getBoolean("graduate"));
        student.setCreateDate(resultSet.getTimestamp("create_date"));

        return student;
    }
}
