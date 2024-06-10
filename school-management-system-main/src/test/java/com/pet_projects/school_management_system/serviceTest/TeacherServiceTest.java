package com.pet_projects.school_management_system.serviceTest;

import com.pet_projects.school_management_system.models.Role;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.repository.RoleRepository;
import com.pet_projects.school_management_system.repository.TeacherRepository;
import com.pet_projects.school_management_system.service.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeacherServiceTest {

    @InjectMocks
    TeacherServiceImpl teacherServiceImpl;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSaveTeacher() {
        Teacher teacher = new Teacher();
        teacher.setEmail("test@test.com");
        teacher.setPassword("password");
        teacher.setFirstName("First");
        teacher.setLastName("Last");

        when(roleRepository.findByName("TEACHER")).thenReturn(new Role("TEACHER"));
        when(passwordEncoder.encode(teacher.getPassword())).thenReturn("encodedPassword");

        teacherServiceImpl.saveTeacher(teacher);

        verify(teacherRepository, times(1)).save(Mockito.any(Teacher.class));
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        Teacher teacher = new Teacher();
        teacher.setEmail(email);

        when(teacherRepository.findByEmail(email)).thenReturn(teacher);

        Teacher found = teacherServiceImpl.findByEmail(email);

        assertEquals(teacher, found);
        verify(teacherRepository, times(1)).findByEmail(email);
    }
}
