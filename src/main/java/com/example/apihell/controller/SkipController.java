package com.example.apihell.controller;
import com.example.apihell.exception.ResourceNotFoundException;
import com.example.apihell.model.Skip;
import com.example.apihell.model.dto.SkipDTO;
import com.example.apihell.service.SkipService;
import com.example.apihell.service.utils.SkipDTOMapper;
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
    private final SkipDTOMapper skipDTOMapper;

    public SkipController(SkipService skipService, SkipDTOMapper skipDTOMapper) {
        this.skipService = skipService;
        this.skipDTOMapper = skipDTOMapper;
    }

    @GetMapping("/student/{id}")
    ResponseEntity<List<SkipDTO>> getSkipsByStudentId(@PathVariable(name="id") String id){
        List<SkipDTO> skips = skipService.getSkipsByStudentId(id).stream().map(skipDTOMapper::wrap).toList();
        if(skips.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(skips, HttpStatus.OK);
    }

    @PostMapping(value="/new/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkipDTO> createSkip(@RequestBody Skip skip){
        Skip savedSkip  = skipService.save(skip);
        return new ResponseEntity<>(skipDTOMapper.wrap(savedSkip), HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkipDTO> updateSkip(@PathVariable String id, @RequestBody Skip skip){
        Skip updatedSkip  = skipService.getSkipById(id);
        if(updatedSkip == null){
            throw new ResourceNotFoundException("no such skip!");
        }
        updatedSkip.setId(skip.getId());
        updatedSkip.setDate(skip.getDate());
        updatedSkip.setValue(skip.getValue());

        skipService.save(updatedSkip);
        return new ResponseEntity<>(skipDTOMapper.wrap(updatedSkip), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteSkipById(@PathVariable(name="id") String id) {
        Skip skip = skipService.getSkipById(id);
        if(skip==null) {
            return new ResponseEntity<>("NO SUCH ENTITY", HttpStatus.NO_CONTENT);
        }
        skipService.deleteSkip(skip);
        return ResponseEntity.ok("deleted skip " + id + ' ' + skip.getId());
    }

    @DeleteMapping(path = "/delete/byId/{id}")
    public ResponseEntity<String> deleteSkipByIdv2(@PathVariable(name="id") String id) {
        Skip skip = skipService.getSkipById(id);
        if(skip==null) {
            return new ResponseEntity<>("NO SUCH ENTITY", HttpStatus.NO_CONTENT);
        }
        skipService.deleteSkipById(id);
        return ResponseEntity.ok("deleted skip " + id + ' ' + skip.getId());
    }

    @GetMapping(path="/cache")
    public HttpStatus logCache(){
        skipService.logCache();
        return HttpStatus.NO_CONTENT;
    }
}
