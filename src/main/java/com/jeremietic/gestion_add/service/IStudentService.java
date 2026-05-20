package com.jeremietic.gestion_add.service;

import com.jeremietic.gestion_add.model.Student;

import java.util.List;

public interface IStudentService {

    Student addStudent(Student student);

    List<Student> getStudents();

    Student updateStudent(Student student, Long id);


    void deleteStudent(Long id);

    Student getStudentById(Long id);
}
