package com.example.apihell.controller;

import com.example.apihell.exception.ResourceNotFoundException;
import com.example.apihell.model.Group;
import com.example.apihell.model.Subject;
import com.example.apihell.service.SubjectService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/subject")
@Transactional
public class SubjectController {
    private final SubjectService subjectService;
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/byName/{name}")
    ResponseEntity<List<Subject>> getAllByName(@PathVariable(name="name") String name){
        List<Subject> list = subjectService.getAllByName(name);
        if(list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Subject> getSubjectById(@PathVariable(name="id") String id){
        Subject subject = subjectService.getSubjectById(id);
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
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        Subject savedSubject  = subjectService.save(subject);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subject> updateSubject(@PathVariable String id, @RequestBody Subject subject){
        Subject updatedSubject  = subjectService.getSubjectById(id);
        if(updatedSubject == null){
            throw new ResourceNotFoundException("no such subject!");
        }
        updatedSubject.setId(subject.getId());
        updatedSubject.setName(subject.getName());
        updatedSubject.setFullName(subject.getFullName());
        updatedSubject.setSemesterId(subject.getSemesterId());

        subjectService.save(updatedSubject);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) {
        subjectService.deleteSubjectById(id);
        return ResponseEntity.ok("deleted subject " + id);
    }

    @GetMapping(path="/cache")
    public HttpStatus logCache(){
        subjectService.logCache();
        return HttpStatus.NO_CONTENT;
    }
}
