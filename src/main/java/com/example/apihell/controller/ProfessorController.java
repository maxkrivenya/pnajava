package com.example.apihell.controller;

import com.example.apihell.model.Professor;
import com.example.apihell.service.ProfessorService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/professor")
@Transactional
public class ProfessorController {
    private final ProfessorService professorService;
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable(name="id") String id){
        Professor professor = professorService.getProfessorById(id);
        if(professor==null) {
            return new ResponseEntity<>(new Professor(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }
}
