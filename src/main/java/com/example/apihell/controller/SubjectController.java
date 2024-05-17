package com.example.apihell.controller;

import com.example.apihell.model.Group;
import com.example.apihell.model.Subject;
import com.example.apihell.model.dto.SubjectDTO;
import com.example.apihell.service.SubjectService;
import com.example.apihell.service.utils.SubjectDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/subject")
@Transactional
public class SubjectController {
    private final SubjectService subjectService;
    private final SubjectDTOMapper subjectDTOMapper;

    public SubjectController(SubjectService subjectService, SubjectDTOMapper subjectDTOMapper) {
        this.subjectService = subjectService;
        this.subjectDTOMapper = subjectDTOMapper;
    }

    @GetMapping("/byName/{name}")
    ResponseEntity<List<SubjectDTO>> getAllByName(@PathVariable(name="name") String name){
        List<Subject> list = subjectService.getAllByName(name);
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list.stream().map(subjectDTOMapper::wrap).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<SubjectDTO> getSubjectById(@PathVariable(name="id") String id){
        SubjectDTO subject = subjectDTOMapper.wrap(subjectService.getSubjectById(id));
        if(subject==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @GetMapping("/{id}/groups")
    ResponseEntity<List<Group>> getGroupsById(@PathVariable(name="id") String id){
        Subject subject = subjectService.getSubjectById(id);
        if(subject==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subject.getGroups(), HttpStatus.OK);
    }

    @PostMapping(value="/new/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody Subject subject){
        SubjectDTO savedSubject  = subjectDTOMapper.wrap(subjectService.save(subject));
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable String id, @RequestBody Subject subject) {
        Subject updatedSubject  = subjectService.getSubjectById(id);
        if(updatedSubject == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedSubject.setId(subject.getId());
        updatedSubject.setName(subject.getName());
        updatedSubject.setFullName(subject.getFullName());
        updatedSubject.setSemesterId(subject.getSemesterId());

        subjectService.save(updatedSubject);
        return new ResponseEntity<>(subjectDTOMapper.wrap(updatedSubject), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) {
        subjectService.deleteSubjectById(id);
        return ResponseEntity.ok("deleted subject " + id);
    }
}
