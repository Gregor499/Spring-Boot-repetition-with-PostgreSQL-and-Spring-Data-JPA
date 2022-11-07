package com.example.demo.studentManagement;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.example.demo.student.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceTest {
    @Test
    void shouldGetAllStudents(){
        //GIVEN
        Student student = new Student(null, "Herbert", "herbert@a.de", LocalDate.of(2000, 1,1));
        Student student2 = new Student(null, "Norbert", "norbert@a.de", LocalDate.of(2001,2,2));

        List<Student> expected = List.of(student,student2);

        StudentRepository studentRepository = mock(StudentRepository.class);
        when(studentRepository.findAll()).thenReturn(expected);

        StudentService studentService = new StudentService(studentRepository);

        //WHEN
        List<Student> actual = studentService.getStudents();

        //THEN
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldAddNewStudent(){
        //GIVEN
        List<Student> initialList = List.of();

        Student student = new Student(null, "Herbert", "herbert@a.de", LocalDate.of(2000, 1,1));

        Student expected = new Student(1L, "Herbert", "herbert@a.de", LocalDate.of(2000, 1,1));

        StudentRepository studentRepository = mock(StudentRepository.class);
        when(studentRepository.save(student)).thenReturn(expected);
        when(studentRepository.findAll()).thenReturn(List.of(expected));

        StudentService studentService = new StudentService(studentRepository);

        //WHEN
        Student actual = studentService.addNewStudent(student);
        List<Student> actualStudentList = studentService.getStudents();

        //THEN
        Assertions.assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(actualStudentList.size()).isGreaterThan(0);
    }
}
