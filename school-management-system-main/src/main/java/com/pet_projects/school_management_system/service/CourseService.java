package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.CourseDto;
import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.models.User;

import java.util.List;

public interface CourseService {

    List<CourseDto> findAllCourses();

    Course saveCourse(CourseDto courseDto);

    CourseDto findCourseById(long id);

    void updateCourse(CourseDto course);

    void deleteCourse(Long courseId);

    public void assignTeacher(Course course, User user);

    public void unassignTeacher(Course course, User user);

    public Course mapToCourse(CourseDto courseDto);
}
