package com.example.apihell.controller;

import com.example.apihell.model.Group;
import com.example.apihell.model.Professor;
import com.example.apihell.model.Student;
import com.example.apihell.model.dto.GroupDTO;
import com.example.apihell.model.dto.ProfessorDTO;
import com.example.apihell.service.GroupService;
import com.example.apihell.service.utils.GroupDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/group")
@Transactional
public class GroupController {
    private final GroupService groupService;
    private final GroupDTOMapper groupDTOMapper;

    public GroupController(GroupService groupService, GroupDTOMapper groupDTOMapper) {
        this.groupService = groupService;
        this.groupDTOMapper = groupDTOMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable("id") String id) {
        Group group = groupService.getGroupById(id);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(groupDTOMapper.wrap(group), HttpStatus.OK);
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
    public ResponseEntity<GroupDTO> createGroup(@RequestBody Group group){
        Group savedGroup  = groupService.save(group);
        return new ResponseEntity<>(groupDTOMapper.wrap(savedGroup), HttpStatus.CREATED);
    }

    @PostMapping(value = "/like/{page}")
    public ResponseEntity<List<GroupDTO>> likeStudent(@PathVariable int page, @RequestBody GroupDTO groupDTO) {
        if(groupDTO == null){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        List<Group> allProfessors = groupService.findAll();
        if(allProfessors.isEmpty()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        List<GroupDTO> returnValue =  allProfessors.stream()
                .map(groupDTOMapper::wrap)
                .filter(st -> st.id().contains(groupDTO.id()))
                .filter(st -> st.faculty().contains(groupDTO.faculty()))
                .filter(st -> st.degree().contains(groupDTO.degree()))
                .filter(st -> st.educationType().contains(groupDTO.educationType()))
                .toList();
        if(returnValue.isEmpty()){return new ResponseEntity<>(HttpStatus.NO_CONTENT);}

        if(groupDTO.semesterNumber() != null){
            returnValue = returnValue.stream().filter(st -> st.semesterNumber().equals(groupDTO.semesterNumber())).toList();
        }

        if(returnValue.size() < 5){return new ResponseEntity<>(returnValue, HttpStatus.OK);}

        if(returnValue.size() < 5*(page + 1)){
            returnValue = returnValue.subList(returnValue.size() - 5, returnValue.size());
        }else{
            returnValue = returnValue.subList(5 * page, (5 * page) + 5);
        }

        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable String id, @RequestBody Group group) {
        Group updatedGroup  = groupService.getGroupById(id);
        if(updatedGroup == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedGroup.setId(group.getId());
        updatedGroup.setDegree(group.getDegree());
        updatedGroup.setFaculty(group.getFaculty());
        updatedGroup.setEducationType(group.getEducationType());
        updatedGroup.setSemesterNumber(group.getSemesterNumber());

        groupService.save(updatedGroup);
        return new ResponseEntity<>(groupDTOMapper.wrap(updatedGroup), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) {
        groupService.deleteGroupById(id);
        return ResponseEntity.ok("deleted group " + id);
    }

}
