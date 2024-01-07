package com.gfg.minorproject.service;

import com.gfg.minorproject.dto.CreateStudentRequest;
import com.gfg.minorproject.dto.UpdateStudentRequest;
import com.gfg.minorproject.model.Student;
import com.gfg.minorproject.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(CreateStudentRequest createStudentRequest) {
        Student student = createStudentRequest.to();
        return studentRepository.save(student);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
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
