package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.repository.RoleRepository;
import com.pet_projects.school_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveStudent(Student student) {

        Student newStudent = new Student();

        newStudent.setEmail(student.getEmail());
        newStudent.setPassword(passwordEncoder.encode(student.getPassword()));
        newStudent.setRole(roleRepository.findByName("STUDENT"));
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());

        studentRepository.save(newStudent);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
