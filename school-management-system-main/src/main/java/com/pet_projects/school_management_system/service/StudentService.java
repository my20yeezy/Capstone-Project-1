package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.models.Student;

import java.util.List;

public interface StudentService {

    void saveStudent(Student student);

    Student findByEmail(String email);

    Student findById(Long id);

    List<Student> findAllStudents();

}
