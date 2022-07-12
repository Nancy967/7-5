package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<Student> create(@RequestBody Student student) {

        Integer studentId = studentService.insert(student);

        Student newStudent = studentService.getById(studentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<?> update(@PathVariable Integer studentId,
                                    @RequestBody Student student) {

        student.setId(studentId);
        studentService.update(student);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> delete(@PathVariable Integer studentId) {

        studentService.deleteById(studentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> read(@PathVariable Integer studentId) {

        Student student = studentService.getById(studentId);

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}