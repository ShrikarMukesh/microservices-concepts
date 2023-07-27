package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
@SpringBootTest(classes = DemoApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    @DisplayName("Create new student - student not exists - success")
    void createNewStudent_studentNotExists_success() {
        // Given
        String url = "/api/v1/students";
        Student student = new Student();
        student.setFirstName("Alice");
        student.setLastName("Smith");
        student.setCourseId("eee");

        // Then
        HttpEntity<Student> request = new HttpEntity<>(student);
        ResponseEntity<Student> response = testRestTemplate.postForEntity(url, request, Student.class);
        log.info("Created:{}", response);
        Student created = response.getBody();

        // Verify
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Get student info - student exists - success")
    void getStudentInfo_studentExists_success() {
        // Given
        Long studentId = 9L;
        String url = "/api/v1/students/" + studentId;

        // Then
        ResponseEntity<Student> response = testRestTemplate.getForEntity(url, Student.class);
        Student student = response.getBody();
        log.info("Got:{}", student);

        // Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @Order(3)
    @DisplayName("Get all student info - student exists-success")
    void getAllStudentInfo_studentsExist_success(){
        // Given
        String url = "/api/v1/students";

        // Then
        HttpEntity entity = new HttpEntity(null);
        ResponseEntity<List<Student>> response = testRestTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<Student>>(){});

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Student> students = response.getBody();
        for(Student student : students) {
            log.info("{}", student);
        }
    }

    @Test
    @Order(4)
    @DisplayName("Delete student info - student exists - success")
    void deleteStudentInfo_studentExists_success() {
        //Given
        String url= "/api/v1/students/11";

        //Then
        HttpEntity httpEntity = new HttpEntity<>(null);
        ResponseEntity<Void> response = testRestTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);

        // Verify
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}