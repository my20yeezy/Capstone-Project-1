package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.models.Teacher;

public interface TeacherService {

    void saveTeacher(Teacher teacher);

    Teacher findByEmail(String email);
}
