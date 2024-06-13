package com.pet_projects.school_management_system.security;

import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.service.StudentService;
import com.pet_projects.school_management_system.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityUtil {

    @Autowired
    private final TeacherService teacherService;

    @Autowired
    private final StudentService studentService;


    public Teacher getSessionTeacher() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return teacherService.findByEmail(authentication.getName());
        }
        return null;
    }

    public Student getSessionStudent() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return studentService.findByEmail(authentication.getName());
        }
        return null;
    }

}
