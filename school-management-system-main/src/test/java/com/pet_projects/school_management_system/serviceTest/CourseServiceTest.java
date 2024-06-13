package com.pet_projects.school_management_system.serviceTest;

import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.repository.CourseRepository;
import com.pet_projects.school_management_system.repository.StudentRepository;
import com.pet_projects.school_management_system.repository.TeacherRepository;
import com.pet_projects.school_management_system.service.CourseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CourseServiceTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void testFindAllCourses() {
        courseService.findAllCourses();
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllCoursesIntegration() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course());
        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseService.findAllCourses();
        assertEquals(courses.size(), result.size());
    }

    @Test
    public void testSaveCourse() {
        Course course = new Course();
        courseService.saveCourse(course);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void testFindCourseById() {
        long id = 1L;
        when(courseRepository.findById(id)).thenReturn(Optional.of(new Course()));
        courseService.findCourseById(id);
        verify(courseRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteCourse() {
        Long id = 1L;
        courseService.deleteCourse(id);
        verify(courseRepository, times(1)).deleteById(id);
    }

    @Test
    public void testAssignTeacher() {
        Course course = new Course();
        Teacher teacher = new Teacher();
        courseService.assignTeacher(course, teacher);
        verify(courseRepository, times(1)).save(course);
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    public void testUnassignTeacher() {
        Course course = new Course();
        Teacher teacher = new Teacher();
        teacher.setEmail("test@fakemail.com");
        course.setTeacher(teacher);
        courseService.unassignTeacher(course, teacher);
        verify(courseRepository, times(1)).save(course);
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    public void testEnrollCourse() {
        Course course = new Course();
        Student student = new Student();
        courseService.enrollCourse(course, student);
        verify(courseRepository, times(1)).save(course);
        verify(studentRepository, times(1)).save(student);
        Assertions.assertTrue(course.getStudents().contains(student));
        Assertions.assertTrue(student.getEnrolledCourses().contains(course));
    }

    @Test
    public void testEnrollCourseNumberOfStudents() {
        Course course = new Course();
        Student student = new Student();
        courseService.enrollCourse(course, student);
        verify(courseRepository, times(1)).save(course);
        verify(studentRepository, times(1)).save(student);
        assertEquals(1, course.getNumberOfStudents());
    }

    @Test
    public void testDropCourse() {
        Course course = new Course();
        Student student = new Student();
        course.getStudents().add(student);
        student.getEnrolledCourses().add(course);
        courseService.dropCourse(course, student);
        verify(courseRepository, times(1)).save(course);
        verify(studentRepository, times(1)).save(student);
        Assertions.assertFalse(course.getStudents().contains(student));
        Assertions.assertFalse(student.getEnrolledCourses().contains(course));
    }

}
