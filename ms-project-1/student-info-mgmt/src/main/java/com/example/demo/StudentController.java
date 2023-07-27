package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final BookInfoClient bookInfoClient;

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = studentService.get(id);
        String bookId = "22";
//        URI uri = URI.create("/api/v1/books/" + bookId);
        Book booked = bookInfoClient.getBook(bookId);
        log.info("Got Book: {}",booked);
        return ResponseEntity.ok(student);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student saved = studentService.create(student);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Student updated = studentService.update(id, student);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("addSubject/{id}")
    public ResponseEntity<Student> addSubject(@PathVariable Long id, @RequestBody Long subjectId) {
        Student student = studentService.addSubject(id, subjectId);
        return new ResponseEntity(student, HttpStatus.OK);
    }

   @PutMapping("removeSubject/{id}")
    public ResponseEntity<Student> removeSubject(@PathVariable Long id, @RequestBody Long subjectId){
       Student student= studentService.removeSubject(id, subjectId );
       return new ResponseEntity(student, HttpStatus.OK);

    }
    // GET ALL
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }
}
