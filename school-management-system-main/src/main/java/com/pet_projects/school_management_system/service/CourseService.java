package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.CourseDto;
import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.models.SomeUser;
import com.pet_projects.school_management_system.models.Teacher;

import java.util.List;

public interface CourseService {

//    List<CourseDto> findAllCourses();
    List<Course> findAllCourses();

    Course saveCourse(Course course);

//    CourseDto findCourseById(long id);
    Course findCourseById(long id);

//    void updateCourse(CourseDto course);
    void updateCourse(Course course);

    void deleteCourse(Long courseId);

    public void assignTeacher(Course course, Teacher teacher);

    public void unassignTeacher(Course course, Teacher teacher);

}
