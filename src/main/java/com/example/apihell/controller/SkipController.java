package com.example.apihell.controller;
import com.example.apihell.model.Skip;
import com.example.apihell.service.SkipService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    ResponseEntity<List<Skip>> getSkipsByStudentId(@PathVariable(name="id") String id){
        List<Skip> skips = skipService.getSkipsByStudentId(id);
        if(skips.isEmpty()){
            return new ResponseEntity<>(skips, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(skips, HttpStatus.OK);
    }
}
