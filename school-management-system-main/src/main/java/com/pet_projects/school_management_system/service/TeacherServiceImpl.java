package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.repository.RoleRepository;
import com.pet_projects.school_management_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveTeacher(Teacher teacher) {

        Teacher newTeacher = new Teacher();

        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        newTeacher.setRole(roleRepository.findByName("TEACHER"));
        newTeacher.setFirstName(teacher.getFirstName());
        newTeacher.setLastName(teacher.getLastName());

        teacherRepository.save(newTeacher);
    }

    @Override
    public Teacher findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

}
