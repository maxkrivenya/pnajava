package com.example.apihell.controller;

import com.example.apihell.exception.ResourceNotFoundException;
import com.example.apihell.model.Group;
import com.example.apihell.model.Student;
import com.example.apihell.service.GroupService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/group")
@Transactional
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable("id") String id) {
        Group group = groupService.getGroupById(id);
        if (group == null) {
            return new ResponseEntity<>(group, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(group, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudentsByGroupId(@PathVariable("id") String id) {
        Group group = groupService.getGroupById(id);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(group.getStudents(), HttpStatus.OK);
        }
    }

    @PostMapping(value="/new/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> createGroup(@RequestBody Group group){
        Group savedGroup  = groupService.save(group);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> updateGroup(@PathVariable String id, @RequestBody Group group){
        Group updatedGroup  = groupService.getGroupById(id);
        if(updatedGroup == null){
            throw new ResourceNotFoundException("no such group!");
        }
        updatedGroup.setId(group.getId());
        updatedGroup.setDegree(group.getDegree());
        updatedGroup.setFaculty(group.getFaculty());
        updatedGroup.setEducationType(group.getEducationType());
        updatedGroup.setSemesterNumber(group.getSemesterNumber());

        groupService.save(updatedGroup);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) {
        groupService.deleteGroupById(id);
        return ResponseEntity.ok("deleted group " + id);
    }

    @GetMapping(path="/cache")
    public HttpStatus logCache(){
        groupService.logCache();
        return HttpStatus.NO_CONTENT;
    }
}
