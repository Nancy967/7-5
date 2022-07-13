package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceImplMockTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentDao studentDao;

    @Test
    public void getById(){
        Student mockstudent = new Student();
        mockstudent.setId(100);
        mockstudent.setName("Dora");

        Mockito.when(studentDao.getById(Mockito.any())).thenReturn(mockstudent);

        Student student = studentService.getById(2);
        assertNotNull(student);
        assertEquals(100, student.getId());
        assertEquals("Dora", student.getName());
    }
}