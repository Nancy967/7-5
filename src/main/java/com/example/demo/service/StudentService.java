package com.example.demo.service;

import com.example.demo.model.Student;

public interface StudentService {

    Integer insert(Student student);

    void update(Student student);

    void deleteById(Integer id);

    Student getById(Integer id);
}
