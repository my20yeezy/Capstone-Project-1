package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.models.Teacher;

import java.util.List;

public interface TeacherService {

    void saveTeacher(Teacher teacher);

    Teacher findByEmail(String email);

    List<Teacher> findAllTeachers();
}
