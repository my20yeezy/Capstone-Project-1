package com.pet_projects.school_management_system.serviceTest;

import com.pet_projects.school_management_system.models.Role;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.repository.RoleRepository;
import com.pet_projects.school_management_system.repository.TeacherRepository;
import com.pet_projects.school_management_system.service.TeacherService;
import com.pet_projects.school_management_system.service.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TeacherServiceTest {

    @Mock
    private TeacherService teacherService;

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

        Mockito.when(roleRepository.findByName("TEACHER")).thenReturn(new Role("TEACHER"));
        Mockito.when(passwordEncoder.encode(teacher.getPassword())).thenReturn("encodedPassword");

        teacherServiceImpl.saveTeacher(teacher);

        Mockito.verify(teacherRepository, Mockito.times(1)).save(Mockito.any(Teacher.class));
    }
}
