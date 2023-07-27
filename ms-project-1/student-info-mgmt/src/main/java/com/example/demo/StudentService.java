package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;

    public Student get(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        log.debug("Get student:{}", id);
        if (optionalStudent.isPresent() )
            return optionalStudent.get();
        return null;
    }

    public Student create(Student student) {

        Optional<Semester> semesterOptional =  semesterRepository.findById(1L);
        Semester semester;
        if (semesterOptional.isEmpty()) {
            semester = new Semester();
            LocalDate currentDate = LocalDate.now();
            LocalDate endDate = currentDate.plus(5, ChronoUnit.MONTHS);
            semester.setId(1L);
            semester.setStartingDate(currentDate);
            semester.setEndDate(endDate);
            semesterRepository.save(semester);
            log.debug("Saved semester:{}", semester);
        } else {
            semester = semesterOptional.get();
        }

        student.setCurrentSemester(semester);
        Student saved = studentRepository.save(student);
        log.debug("Added:{}", saved);
        return saved;
    }

    public Student update(Long id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.save(student);
            log.debug("{} updated", id);
        } else {
            log.debug("Student with id:{} is not found.", id);
        }
        return student;
    }

    public void delete(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.delete(optionalStudent.get()); ;
            log.debug("{} deleted.", id);
        } else {
            log.debug("Student with id:{} is not found.", id);
        }
    }

    public List<Student> getAll() {
        Iterable<Student> it = studentRepository.findAll();
        List<Student> students = new ArrayList<>();
        for (Student student : it) {
            students.add(student);
        }
        return students;
    }

    public Student addSubject(Long id, Long subjectId) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
            if ( subjectOptional.isPresent() ) {
                Student student = optionalStudent.get();
                Subject subject = subjectOptional.get();
                Semester semester = student.getCurrentSemester();
                List<Subject> subjects = semester.getSubjects();
                boolean contains = false;
                for (Subject subject1 : subjects ) {
                    if (subject1.getId() == subjectId ) {
                        contains = true;
                        break;
                    }
                }
                if ( !contains ) {
                    semester.getSubjects().add(subject);
                    semesterRepository.save(semester);
                    log.debug("Subject:{} added to semester:{}", subjectId, semester);
                } else {
                    log.debug("Subject:{} already present in semester:{}", subject, semester);
                }
            } else {
                log.debug("{} subject not found.", subjectId);
            }
        } else {
            log.debug("Student with id:{} is not found.", id);
        }
        return null;
    }

    public Student removeSubject(Long id, Long subjectId) {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isPresent()) {
                Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
                if ( subjectOptional.isPresent() ) {
                    Student student = optionalStudent.get();
                    Subject subject = subjectOptional.get();
                    Semester semester = student.getCurrentSemester();
                    List<Subject> subjects = semester.getSubjects();
                    boolean contains1= false;
                    for(Subject subject1:subjects){
                        if (subject1.getId() == subjectId ) {
                            contains1 = true;
                            break;
                        }
                    }
                    if(contains1){
                        semester.getSubjects().remove(subject);
                        semesterRepository.save(semester);
                        log.debug("Subject:{} removed from semester:{}", subjectId, semester);
                        return student;
                    } else {
                        log.debug("Subject:{} not present in semester:{}", subject, semester);
                    }

                }
            }
        return null;
    }
}
