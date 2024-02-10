package com.gfg.minorproject.controller;

import com.gfg.minorproject.dto.CreateStudentRequest;
import com.gfg.minorproject.dto.UpdateStudentRequest;
import com.gfg.minorproject.model.SecuredUser;
import com.gfg.minorproject.model.Student;
import com.gfg.minorproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest){
        return studentService.addStudent(createStudentRequest);
    }

    @GetMapping("/{id}") //this api can be invoked by admin only
    public Student getStudentById(@PathVariable("id") Integer id){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        return studentService.getStudentById(id);
    }

    @GetMapping("/details")
    public Student getStudent(){
        //this app return the student details who is calling this api from security context
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        return studentService.getStudentById(securedUser.getStudent().getId());
    }
    @GetMapping("/all")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @DeleteMapping("")//
    //this app return the student details who is calling this api from security context
    public void deleteStudentById()
    {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
         studentService.deleteStudentById(securedUser.getStudent().getId());
    }

    @PutMapping("")
    public Student updateStudentById( @RequestBody @Valid UpdateStudentRequest updateStudentRequest){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        return studentService.updateStudentById(securedUser.getStudent().getId(),updateStudentRequest);
    }
}
