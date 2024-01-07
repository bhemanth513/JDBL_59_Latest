package com.gfg.minorproject.controller;

import com.gfg.minorproject.dto.CreateStudentRequest;
import com.gfg.minorproject.dto.UpdateStudentRequest;
import com.gfg.minorproject.model.Student;
import com.gfg.minorproject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/minor-project")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest){
        return studentService.addStudent(createStudentRequest);
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable("id") Integer id){
        return studentService.getStudentById(id);
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @DeleteMapping("/delete-student/{id}")
    public void deleteStudentById(@PathVariable("id") Integer id){
         studentService.deleteStudentById(id);
    }

    @PutMapping("/update-student/{id}")
    public Student updateStudentById(@PathVariable("id") Integer id, @RequestBody @Valid UpdateStudentRequest updateStudentRequest){
        return studentService.updateStudentById(id,updateStudentRequest);
    }
}
