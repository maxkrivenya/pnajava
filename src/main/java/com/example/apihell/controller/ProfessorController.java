package com.example.apihell.controller;

import com.example.apihell.model.Professor;
import com.example.apihell.model.Student;
import com.example.apihell.model.dto.ProfessorDTO;
import com.example.apihell.model.dto.StudentDTO;
import com.example.apihell.service.ProfessorService;
import com.example.apihell.service.utils.ProfessorDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/professor")
@Transactional
public class ProfessorController {
    private final ProfessorService professorService;
    private final ProfessorDTOMapper professorDTOMapper;

    public ProfessorController(ProfessorService professorService, ProfessorDTOMapper professorDTOMapper) {
        this.professorService = professorService;
        this.professorDTOMapper = professorDTOMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable(name="id") String id){
        Professor professor = professorService.getProfessorById(id);
        if(professor==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(professorDTOMapper.wrap(professor), HttpStatus.OK);
    }

    @PostMapping(value = "/like/{page}")
    public ResponseEntity<List<ProfessorDTO>> likeStudent(@PathVariable int page, @RequestBody ProfessorDTO professorDTO) {
        if(professorDTO == null){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        List<Professor> allProfessors = professorService.getAllProfessors();
        if(allProfessors.isEmpty()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        List<ProfessorDTO> returnValue =  allProfessors.stream()
                .map(professorDTOMapper::wrap)
                .filter(st -> st.name().contains(professorDTO.name()))
                .filter(st -> st.surname().contains(professorDTO.surname()))
                .filter(st -> st.patronim().contains(professorDTO.patronim()))
                .filter(st -> st.title().contains(professorDTO.title()))
                .filter(st -> st.department().contains(professorDTO.department()))
                .toList();
        if(returnValue.isEmpty()){return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        if(returnValue.size() < 5){return new ResponseEntity<>(returnValue, HttpStatus.OK);}
        if(returnValue.size() < 5*(page + 1)){
            returnValue = returnValue.subList(returnValue.size() - 5, returnValue.size());
        }else{
            returnValue = returnValue.subList(5 * page, (5 * page) + 5);
        }

        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PostMapping(value="/new/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorDTO professorDTO){
        Professor professor = professorDTOMapper.unwrap(professorDTO);
        professorService.save(professor);
        return new ResponseEntity<>(professor, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable String id, @RequestBody Professor professor) {
        Professor updatedProfessor  = professorService.getProfessorById(id);
        if(updatedProfessor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

}
