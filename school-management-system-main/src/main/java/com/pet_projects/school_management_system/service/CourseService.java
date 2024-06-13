package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;

import java.util.List;

public interface CourseService {

    List<Course> findAllCourses();

    Course saveCourse(Course course);

    Course findCourseById(long id);

    void updateCourse(Course course);

    void deleteCourse(Long courseId);

    void assignTeacher(Course course, Teacher teacher);

    void unassignTeacher(Course course, Teacher teacher);

    void enrollCourse(Course course, Student student);

    void dropCourse(Course course, Student student);

}
