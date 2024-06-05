package com.pet_projects.school_management_system.security;

import com.pet_projects.school_management_system.models.Role;
import com.pet_projects.school_management_system.models.SomeUser;
import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.repository.StudentRepository;
import com.pet_projects.school_management_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

//    private UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Teacher someTeacher = teacherRepository.findFirstByEmail(email);

        Student someStudent = studentRepository.findFirstByEmail(email);

        if (someTeacher != null) {

            List<Role> rolesList = new ArrayList<>();
            rolesList.add(someTeacher.getRole());

            return new org.springframework.security.core.userdetails.User(
                    someTeacher.getEmail(),
                    someTeacher.getPassword(),
                    rolesList.stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
        }
        else if (someStudent != null) {
            List<Role> rolesList = new ArrayList<>();
            rolesList.add(someStudent.getRole());

            return new org.springframework.security.core.userdetails.User(
                    someStudent.getEmail(),
                    someStudent.getPassword(),
                    rolesList.stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
        }
        else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

}
