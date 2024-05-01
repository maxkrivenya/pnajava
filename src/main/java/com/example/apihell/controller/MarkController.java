package com.example.apihell.controller;
import com.example.apihell.model.Mark;
import com.example.apihell.model.dto.MarkDTO;
import com.example.apihell.model.dto.StudentDTO;
import com.example.apihell.service.MarkService;
import com.example.apihell.service.utils.MarkDTOMapper;
import com.example.apihell.service.utils.StudentDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/marks")
@Transactional
public class MarkController {
    private final MarkService markService;
    private final MarkDTOMapper markDTOMapper;
    private final StudentDTOMapper studentDTOMapper;

    public MarkController(MarkService markService, MarkDTOMapper markDTOMapper, StudentDTOMapper studentDTOMapper) {
        this.markService = markService;
        this.markDTOMapper = markDTOMapper;
        this.studentDTOMapper = studentDTOMapper;
    }

    @GetMapping("/{id}")
    ResponseEntity<List<MarkDTO>> getMarksByStudentId(@PathVariable(name="id") String id){
        List<MarkDTO> marks = markService.getMarksByStudentId(id).stream().map(markDTOMapper::wrap).toList();
        if(marks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(marks, HttpStatus.OK);
    }

    @GetMapping("/{id}/student")
    ResponseEntity<StudentDTO> getStudentThroughMarks(@PathVariable(name="id") String id){
        List<Mark> marks = markService.getMarksByStudentId(id);
        if(marks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentDTOMapper.wrap(marks.get(0).getStudent()),HttpStatus.OK);
    }

    @PostMapping(value="/new/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarkDTO> createMark(@RequestBody MarkDTO markDTO){
        markService.save(markDTOMapper.unwrap(markDTO));
        return new ResponseEntity<>(markDTO, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarkDTO> updateMark(@PathVariable String id, @RequestBody Mark mark) {
        Mark updatedMark  = markService.getMarkById(id);
        if(updatedMark == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedMark.setId(mark.getId());
        updatedMark.setDate(mark.getDate());
        updatedMark.setValue(mark.getValue());

        markService.save(updatedMark);
        return new ResponseEntity<>(markDTOMapper.wrap(updatedMark), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) {
        markService.deleteMarkById(id);
        return ResponseEntity.ok("deleted mark " + id);
    }
}

