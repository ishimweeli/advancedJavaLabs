package com.example.apilayer.controller;

import com.example.corebusinesslogic.StudentService;
import com.example.corebusinesslogic.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public String registerStudent(@RequestBody Student student) {
        String studentNumber = studentService.registerStudent(student);
        return "Thank you for registering. Your student number is: " + studentNumber;
    }

    @GetMapping("/{studentNumber}")
    public Student getStudent(@PathVariable String studentNumber) {
        return studentService.getStudentDetails(studentNumber);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
