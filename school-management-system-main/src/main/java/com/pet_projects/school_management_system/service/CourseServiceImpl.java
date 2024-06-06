package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.CourseDto;
import com.pet_projects.school_management_system.mappers.CourseMapper;
import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.repository.CourseRepository;
import com.pet_projects.school_management_system.repository.StudentRepository;
import com.pet_projects.school_management_system.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CourseMapper mapper;


//    @Override
//    @Transactional
//    public List<CourseDto> findAllCourses() {
//        List<Course> courses = courseRepository.findAll();
//        return courses.stream().map(mapper::mapToCourseDto).collect(Collectors.toList());
//    }
    @Override
    @Transactional
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }


    @Override
    public Course saveCourse(Course course) {
//        Course course = mapper.mapToCourse(course);
        return courseRepository.save(course);
    }

//    @Override
//    public CourseDto findCourseById(long id) {
//        Course course = courseRepository.findById(id).get();
//        return mapper.mapToCourseDto(course);
//    }
    @Override
    public Course findCourseById(long id) {
        return courseRepository.findById(id).get();
    }

//    @Override
//    public void updateCourse(CourseDto courseDto) {
//        Course course = mapper.mapToCourse(courseDto);
//        courseRepository.save(course);
//    }
    @Override
    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    @Transactional
    public void assignTeacher(Course course, Teacher teacher) {
        if (course.getTeacher() == null) {
            course.setTeacher(teacher);
            course.setTeacherString(teacher.toString());
            teacher.getAssignedCourses().add(course);
            courseRepository.save(course);
            teacherRepository.save(teacher);
        }
    }

    @Override
    @Transactional
    public void unassignTeacher(Course course, Teacher teacher) {
        Teacher currentTeacher = null;
        if (course.getTeacher() != null) {
            currentTeacher = course.getTeacher();
            if (teacher.getEmail().equals(currentTeacher.getEmail())) {
                course.setTeacher(null);
                course.setTeacherString(null);
                teacher.getAssignedCourses().remove(course);
                courseRepository.save(course);
                teacherRepository.save(teacher);
            }
        }

    }

    @Override
    @Transactional
    public void enrollCourse(Course course, Student student) {
        if (!student.getEnrolledCourses().contains(course)) {
            course.getStudents().add(student);
            student.getEnrolledCourses().add(course);
            course.setNumberOfStudents(course.getNumberOfStudents() + 1);
            courseRepository.save(course);
            studentRepository.save(student);
        }
    }

    @Override
    @Transactional
    public void dropCourse(Course course, Student student) {
        if (student.getEnrolledCourses().contains(course)) {
            course.getStudents().remove(student);
            course.setNumberOfStudents(course.getNumberOfStudents() - 1);
            student.getEnrolledCourses().remove(course);
            courseRepository.save(course);
            studentRepository.save(student);
        }
    }

}
