package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.models.Student;

public interface StudentService {

    void saveStudent(Student student);

    Student findByEmail(String email);
}
