package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@Slf4j
@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private SemesterRepository semesterRepository;
    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    @DisplayName("GetStudentInfo test case - student exists - success case")
    void getStudentInfo_studentExists_success() {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        student.setId(100L);
        student.setFirstName("John");
        student.setLastName("Doe");

        // Mock
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // Then
        Student got = studentService.get(studentId);
        log.debug("{}", got);

        // Verify
        Mockito.verify(studentRepository).findById(studentId);
        Mockito.verifyNoMoreInteractions(studentRepository);
    }


    @Test
    @DisplayName("GetStudentInfo test case - student not exists - failure case")
    void getStudentInfo_studentNotExists_failure() {
        // Given
        Long studentId = 100L;

        // Mock
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.ofNullable(null));

        // Then
        Student got = studentService.get(studentId);
        log.debug("{}", got);

        // Verify
        Assertions.assertNull(got);
        Mockito.verify(studentRepository).findById(studentId);
        Mockito.verifyNoMoreInteractions(studentRepository);
    }

    @Test
    @DisplayName("deleteStudentInfo test case - student exists - success case")
    void deleteStudentInfo_studentExists_success() {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        student.setId(100L);
        student.setFirstName("John");
        student.setLastName("Doe");

        // Mock
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        Mockito.doNothing().when(studentRepository).delete(student);

        // Then
        studentService.delete(studentId);

        // Verify
        Mockito.verify(studentRepository).findById(studentId);
        Mockito.verify(studentRepository).delete(student);
        Mockito.verifyNoMoreInteractions(studentRepository);
    }

    @Test
    @DisplayName("deleteStudentInfo test case - student not exists - failure case")
    void deleteStudentInfo_studentNotExists_failure() {
        // Given
        Long studentId = 1111L;

        // Mock
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.ofNullable(null));

        // Then
        studentService.delete(studentId);

        // Verify
        Mockito.verify(studentRepository).findById(studentId);
        Mockito.verifyNoMoreInteractions(studentRepository);
    }
}
