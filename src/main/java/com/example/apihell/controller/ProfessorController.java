package com.example.apihell.controller;

import com.example.apihell.exception.ResourceNotFoundException;
import com.example.apihell.model.Professor;
import com.example.apihell.model.dto.ProfessorDTO;
import com.example.apihell.service.ProfessorService;
import com.example.apihell.service.utils.ProfessorDTOMapper;
import com.example.apihell.service.utils.SubjectDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/professor")
@Transactional
public class ProfessorController {
    private final ProfessorService professorService;
    private final ProfessorDTOMapper professorDTOMapper;
    private final SubjectDTOMapper subjectDTOMapper;

    public ProfessorController(ProfessorService professorService, ProfessorDTOMapper professorDTOMapper, SubjectDTOMapper subjectDTOMapper) {
        this.professorService = professorService;
        this.professorDTOMapper = professorDTOMapper;
        this.subjectDTOMapper = subjectDTOMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable(name="id") String id){
        Professor professor = professorService.getProfessorById(id);
        if(professor==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(professorDTOMapper.wrap(professor), HttpStatus.OK);
    }

    @PostMapping(value="/new/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorDTO professorDTO){
        Professor professor = professorDTOMapper.unwrap(professorDTO);
        professorService.save(professor);
        return new ResponseEntity<>(professor, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable String id, @RequestBody Professor professor){
        Professor updatedProfessor  = professorService.getProfessorById(id);
        if(updatedProfessor == null){
            throw new ResourceNotFoundException("no such professor!");
        }
        updatedProfessor.setId(professor.getId());
        updatedProfessor.setDepartment(professor.getDepartment());
        updatedProfessor.setTitle(professor.getTitle());
        updatedProfessor.setName(professor.getName());
        updatedProfessor.setSurname(professor.getSurname());
        updatedProfessor.setPatronim(professor.getPatronim());

        professorService.save(updatedProfessor);
        return new ResponseEntity<>(professorDTOMapper.wrap(updatedProfessor), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteProfessorById(@PathVariable(name="id") String id) {
        professorService.deleteProfessorById(id);
        return ResponseEntity.ok("deleted professor " + id);
    }

    @GetMapping(path="/cache")
    public HttpStatus logCache(){
        professorService.logCache();
        return HttpStatus.NO_CONTENT;
    }
}
