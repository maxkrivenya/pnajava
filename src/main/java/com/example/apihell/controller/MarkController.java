package com.example.apihell.controller;
import com.example.apihell.model.Mark;
import com.example.apihell.model.Student;
import com.example.apihell.service.MarkService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/marks")
@Transactional
public class MarkController {
    private final MarkService markService;
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping("/{id}")
    ResponseEntity<List<Mark>> getMarksByStudentId(@PathVariable(name="id") String id){
        List<Mark> marks = markService.getMarksByStudentId(id);
        if(marks.isEmpty()){
            return new ResponseEntity<>(marks, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(marks, HttpStatus.OK);
    }

    @GetMapping("/{id}/student")
    ResponseEntity<Student> getStudentThroughMarks(@PathVariable(name="id") String id){
        List<Mark> marks = markService.getMarksByStudentId(id);
        if(marks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(marks.get(0).getStudent(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) throws Exception{
        markService.deleteMarkById(id);
        return ResponseEntity.ok("deleted mark" + id);
    }
}
