package com.pet_projects.school_management_system.security;

import com.pet_projects.school_management_system.models.SomeUser;
import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.service.StudentService;
import com.pet_projects.school_management_system.service.StudentServiceImpl;
import com.pet_projects.school_management_system.service.TeacherService;
import com.pet_projects.school_management_system.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    @Autowired
    private static TeacherService teacherService;

    @Autowired
    private static StudentService studentService;


    public static Teacher getSessionTeacher() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return teacherService.findByEmail(authentication.getName());
        }
        return null;
    }

    public static Student getSessionStudent() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return studentService.findByEmail(authentication.getName());
        }
        return null;
    }
}
