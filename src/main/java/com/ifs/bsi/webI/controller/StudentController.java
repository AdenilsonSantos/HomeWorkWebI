package com.ifs.bsi.webI.controller;

import com.ifs.bsi.webI.model.Student;
import com.ifs.bsi.webI.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> listAll(){
        return studentService.listAll();
    }

    @GetMapping("/{idStudent}")
    public ResponseEntity<?> getById(@PathVariable Long idStudent){
        return studentService.getById(idStudent);
    }


    @PostMapping
    public ResponseEntity<?> register(@RequestBody Student student){
        return studentService.register(student);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        return studentService.update(student);
    }

    @DeleteMapping("/{idStudent}")
    public ResponseEntity<?> delete(@PathVariable Long idStudent){
        return studentService.delete(idStudent);
    }
}
