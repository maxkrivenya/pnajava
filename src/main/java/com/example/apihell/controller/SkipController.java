package com.example.apihell.controller;
import com.example.apihell.exception.ResourceNotFoundException;
import com.example.apihell.model.Skip;
import com.example.apihell.service.SkipService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/skips")
@Transactional
public class SkipController {
    private final SkipService skipService;
    public SkipController(SkipService skipService) {
        this.skipService = skipService;
    }

    @GetMapping("/student/{id}")
    ResponseEntity<List<Skip>> getSkipsByStudentId(@PathVariable(name="id") String id){
        List<Skip> skips = skipService.getSkipsByStudentId(id);
        if(skips.isEmpty()){
            return new ResponseEntity<>(skips, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(skips, HttpStatus.OK);
    }


    @PostMapping(value="/new/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Skip> createSkip(@RequestBody Skip skip){
        Skip savedSkip  = skipService.save(skip);
        return new ResponseEntity<>(savedSkip, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Skip> updateSkip(@PathVariable String id, @RequestBody Skip skip){
        Skip updatedSkip  = skipService.getSkipById(id);
        if(updatedSkip == null){
            throw new ResourceNotFoundException("no such skip!");
        }
        updatedSkip.setId(skip.getId());
        updatedSkip.setDate(skip.getDate());
        updatedSkip.setValue(skip.getValue());

        skipService.save(updatedSkip);
        return new ResponseEntity<>(updatedSkip, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) {
        skipService.deleteSkipById(id);
        return ResponseEntity.ok("deleted skip " + id);
    }
}
