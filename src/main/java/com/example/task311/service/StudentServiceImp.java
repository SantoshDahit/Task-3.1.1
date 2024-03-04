package com.example.task311.service;

import com.example.task311.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.task311.model.Student;

import java.util.List;
import java.util.Optional;

@Service

public class StudentServiceImp {

    @Autowired
   private StudentRepository studentRepository;


    public void addStudent(Student student) {
        studentRepository.save(student);

    }


    public List<Student> getAll() {
        return (List<Student>) studentRepository.findAll();
    }


    public Student getStudentById(int id) throws Exception{
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new Exception("Student not found for ID: " + id);
    }


    public void updateStudentById(int id, String firstName, String lastName, String degree) {
        Optional<Student> student = studentRepository.findById(id);
        Student user = student.get();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDegree(degree);
        studentRepository.save(user);
    }


    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);

    }
}
