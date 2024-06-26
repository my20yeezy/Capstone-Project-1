package com.pet_projects.school_management_system.service;

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

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findCourseById(long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        Course existingCourse = courseRepository.findById(course.getId()).get();
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());

        if(course.getTeacherString() != null) {
            existingCourse.setTeacherString(course.getTeacherString());
        }

        existingCourse.setSchedule(course.getSchedule());
        existingCourse.setTime(course.getTime());

        if (course.getTeacher() != null) {
            existingCourse.setTeacher(course.getTeacher());
        }

        if (course.getStudents() != null) {
            existingCourse.getStudents().clear();
            existingCourse.getStudents().addAll(course.getStudents());
        }

        courseRepository.save(existingCourse);
    }

    @Override
    @Transactional
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
            if (course.getNumberOfStudents() > 0) {
                course.setNumberOfStudents(course.getNumberOfStudents() - 1);
            }
            student.getEnrolledCourses().remove(course);
            courseRepository.save(course);
            studentRepository.save(student);
        }
    }

}
