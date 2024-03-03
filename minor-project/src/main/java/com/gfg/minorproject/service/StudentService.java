package com.gfg.minorproject.service;

import com.gfg.minorproject.dto.CreateStudentRequest;
import com.gfg.minorproject.dto.StudentResponse;
import com.gfg.minorproject.dto.UpdateStudentRequest;
import com.gfg.minorproject.model.SecuredUser;
import com.gfg.minorproject.model.Student;
import com.gfg.minorproject.repository.StudentCacheRepo;
import com.gfg.minorproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${student.authorities}")
    String authorities;

    @Autowired
    SecuredUserService securedUserService;

    @Autowired
    StudentCacheRepo studentCacheRepo;

    public Student addStudent(CreateStudentRequest createStudentRequest) {
        Student student = createStudentRequest.to();
        //adding username to the student table
        SecuredUser securedUser = student.getSecuredUser();
        //encode password before storing
        securedUser.setPassword(passwordEncoder.encode(securedUser.getPassword()));
        securedUser.setAuthorities(authorities);

        //save user to userdetail table to create userId
        SecuredUser securedUser1 = securedUserService.save(securedUser);

        //then store user in the student table
        student.setSecuredUser(securedUser1);
        return studentRepository.save(student);
    }

    public Student getStudentById(Integer id) {

        return studentRepository.findById(id).orElse(null);
    }

    public StudentResponse getUsingCache(Integer id) {
        long start = System.currentTimeMillis();
        StudentResponse studentResponse = studentCacheRepo.getStudent(id);
        if(studentResponse == null){
            Student student = studentRepository.findById(id).orElse(null);
            studentResponse = new StudentResponse(student);
            studentCacheRepo.set(studentResponse);
            long end = System.currentTimeMillis();
            System.out.println("inside getUsingCache() fun: time taken to retrieve data= "+(end-start));
        }else {
            long end = System.currentTimeMillis();
            System.out.println("inside getUsingCache() fun: time taken to retrieve data= "+(end-start));
        }
        return studentResponse;
    }
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudentById(Integer id, UpdateStudentRequest updateStudentRequest) {
        return null;
    }
}
