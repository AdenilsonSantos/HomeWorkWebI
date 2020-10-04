package com.ifs.bsi.webI.service;

import com.ifs.bsi.webI.model.Student;
import com.ifs.bsi.webI.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<?> listAll(){
        List<Student> studentList = studentRepository.findAll();
        if (studentList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(studentList);
    }

    public ResponseEntity<?> getById(Long idStudent){
        Optional<Student> studentOptional = studentRepository.findById(idStudent);
        if (!studentOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not exist that student in our data base");
        }
        return ResponseEntity.ok(studentOptional.get());
    }

    public ResponseEntity<?> register(Student student){
        Student s = studentRepository.save(student);
        if (s == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Anything is wrong, verify that and try again");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    public ResponseEntity<?> update(Student student){
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if (!studentOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not exist that student in our data base");
        }
        Student s = studentRepository.save(student);
        if (s == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Anything is wrong, verify that and try again");
        }
        return ResponseEntity.ok("Update sucessfully: " + s.getName());
    }

    public ResponseEntity<?> delete(Long idStudent){
        Optional<Student> studentOptional = studentRepository.findById(idStudent);
        if (!studentOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not exist that student in our data base");
        }
        studentRepository.deleteById(idStudent);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Delete sucessfully");
    }
}
