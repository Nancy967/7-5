package com.example.demo.dao;

import com.example.demo.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentDaoImplTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void getById(){
        Student student = studentDao.getById(2);
        assertNotNull(student);
        assertEquals("Rom", student.getName());
        assertEquals(34.6, student.getScore());
        assertFalse(student.isGraduate());
        assertNotNull(student.getCreateDate());
    }

    @Test
    @Transactional
    public void deleteById(){
        studentDao.deleteById(3);

        Student student = studentDao.getById(3);
        assertNull(student);
    }

    @Test
    @Transactional
    public void insert(){
        Student student = new Student();
        student.setName("Bob");
        student.setScore(60.0);
        student.setGraduate(true);

        Integer studentId = studentDao.insert(student);

        Student result = studentDao.getById(studentId);
        assertNotNull(result);
        assertEquals("Bob", result.getName());
        assertEquals(60.0, result.getScore());
        assertTrue(result.isGraduate());
        assertNotNull(result.getCreateDate());
    }

    @Test
    @Transactional
    public void update(){
        Student student = studentDao.getById(3);
        student.setName("Nancy");

        studentDao.update(student);

        Student result = studentDao.getById(3);
        assertNotNull(result);
        assertEquals("Nancy", result.getName());
    }
}