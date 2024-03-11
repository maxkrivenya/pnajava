package com.example.apihell.controller;
import com.example.apihell.model.Professor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.example.apihell.service.ProfessorService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/professors")
@Transactional
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    @GetMapping("/")
    public ResponseEntity<List<Professor>> getAllProfessors(){
        List<Professor> list = professorService.getAllProfessors();
        if(list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getNames(){
        List<String> list = professorService.getNames();
        if(list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
}