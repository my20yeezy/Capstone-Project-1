package com.pet_projects.school_management_system.serviceTest;

import com.pet_projects.school_management_system.models.Role;
import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.repository.RoleRepository;
import com.pet_projects.school_management_system.repository.StudentRepository;
import com.pet_projects.school_management_system.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest {

    @InjectMocks
    StudentServiceImpl studentServiceImpl;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSaveStudent() {
        Student student = new Student();
        student.setEmail("test@fakemail.com");
        student.setPassword("password");
        student.setFirstName("First");
        student.setLastName("Last");

        when(roleRepository.findByName("STUDENT")).thenReturn(new Role("STUDENT"));
        when(passwordEncoder.encode(student.getPassword())).thenReturn("encodedPassword");

        studentServiceImpl.saveStudent(student);

        verify(studentRepository, times(1)).save(Mockito.any(Student.class));
    }

    @Test
    public void testFindByEmail() {
        String email = "test@fakemail.com";
        Student student = new Student();
        student.setEmail(email);

        when(studentRepository.findByEmail(email)).thenReturn(student);

        Student found = studentServiceImpl.findByEmail(email);

        assertEquals(student, found);
        verify(studentRepository, times(1)).findByEmail(email);
    }
}
