package com.example.demo.dao;

import com.example.demo.mapper.StudentRowMapper;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer insert(Student student) {
        String sql = "INSERT INTO student(name, score, graduate, create_date) VALUES (:name, :score, :graduate, :createDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("name", student.getName());
        map.put("score", student.getScore());
        map.put("graduate", student.isGraduate());
        map.put("createDate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int id = keyHolder.getKey().intValue();
        System.out.println("mysql 自動生成的 id 為: " + id);

        return id;
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE student SET name = :name, score = :score, graduate = :graduate WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", student.getId());
        map.put("name", student.getName());
        map.put("score", student.getScore());
        map.put("graduate", student.isGraduate());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM student WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Student getById(Integer id) {
        String sql = "SELECT id, name, score, graduate, create_date FROM student WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
