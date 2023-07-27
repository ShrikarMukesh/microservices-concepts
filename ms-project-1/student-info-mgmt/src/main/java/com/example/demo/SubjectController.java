package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/{id}")
    public ResponseEntity<Subject> get(@PathVariable Long id){
        Subject subject=subjectService.get(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping
    public ResponseEntity<Subject> create(@RequestBody Subject subject){
        Subject saved=subjectService.create(subject);
        return new ResponseEntity(saved, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
       subjectService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAll(){
        List<Subject> subjects = subjectService.getAll();
        return ResponseEntity.ok(subjects);
    }
}
