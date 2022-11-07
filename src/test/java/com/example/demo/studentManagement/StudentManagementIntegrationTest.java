package com.example.demo.studentManagement;

import com.example.demo.student.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentManagementIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void integrationTest(){
        ResponseEntity<Void> studentAddingResponse = restTemplate.postForEntity("/api/v1/student", new Student(null, "Lars", "lars@a.de", LocalDate.of(2000,1,1)), Void.class);

        assertThat(studentAddingResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
