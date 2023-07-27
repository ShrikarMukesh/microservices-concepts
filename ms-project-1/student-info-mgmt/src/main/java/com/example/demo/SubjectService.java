package com.example.demo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public Subject get(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        log.debug("Get subject:{}", id);
        return optionalSubject.get();

    }

    public Subject create(Subject subject) {
        Subject saved = subjectRepository.save(subject);
        log.debug("Added:{}", saved);
        return saved;
    }

    public Subject update(Long id, Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            subjectRepository.save(subject);
            log.debug("{} updated", id);
        } else {
            log.debug("Student with id:{} is not found.", id);
        }
        return subject;
    }

    public void delete(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            subjectRepository.delete(optionalSubject.get());
            log.debug("{} deleted.", id);
        } else {
            log.debug("subject with id:{} is not found.", id);
        }
    }

    public List<Subject> getAll() {
       Iterable<Subject> it = subjectRepository.findAll();
       List<Subject> subjects = new ArrayList<>();
       for(Subject subject : it) {
           subjects.add(subject);
       }
        return subjects;
    }

}
