package io.platformengineer.demo.service;

import io.platformengineer.demo.model.Student;
import io.platformengineer.demo.model.generics.IStudent;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudents();
    Optional<Student> findStudentById(Long id);
    Student saveStudent(Student student);
    Optional<Student> updateStudent(Long id, Student studentDetails);
    boolean deleteStudentById(Long id);
}
