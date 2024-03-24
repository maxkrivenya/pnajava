package com.example.apihell.controller;

import com.example.apihell.model.Group;
import com.example.apihell.model.Student;
import com.example.apihell.service.GroupService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id){
        groupService.deleteGroupById(id);
        return ResponseEntity.ok("deleted group" + id);
    }
}
