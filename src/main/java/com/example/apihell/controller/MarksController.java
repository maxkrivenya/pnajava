package com.example.apihell.controller;

import com.example.apihell.model.Mark;
import com.example.apihell.service.MarksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/marks")
public class MarksController {
    private final
    MarksService marksService;

    public MarksController(MarksService marksService) {
        this.marksService = marksService;
    }

    @GetMapping("/")
    ResponseEntity<List<Mark>> getAllMarks(){
        List<Mark> marks = marksService.getAll();
        if(marks.isEmpty()) {
            return new ResponseEntity<>(marks, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(marks, HttpStatus.OK);
        }
    }

    @GetMapping("/professor/{professorName}")
    ResponseEntity<List<Mark>> getMarksByProfessor(@PathVariable(name="professorName") String professorName){
        List<Mark> marks = marksService.getMarksByProfessor(professorName);
        if(marks.isEmpty()) {
            return new ResponseEntity<>(marks, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(marks, HttpStatus.OK);
        }
    }

    @GetMapping("/student/{studentId}")
    ResponseEntity<List<Mark>> getMarksByStudentId(@PathVariable(name="studentId") String studentId){
        List<Mark> marks = marksService.getMarksByStudId(studentId);
        if(marks.isEmpty()) {
            return new ResponseEntity<>(marks, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(marks, HttpStatus.OK);
        }
    }
}
