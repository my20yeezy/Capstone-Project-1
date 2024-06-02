package com.pet_projects.school_management_system.service;

import com.pet_projects.school_management_system.dto.CourseDto;
import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public List<CourseDto> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map((course) -> mapToCourseDto(course)).collect(Collectors.toList());
    }

    @Override
    public Course saveCourse(CourseDto courseDto) {
        Course course = mapToCourse(courseDto);
        return courseRepository.save(course);
    }

    @Override
    public CourseDto findCourseById(long id) {
        Course course = courseRepository.findById(id).get();
        return mapToCourseDto(course);
    }

    @Override
    public void updateCourse(CourseDto courseDto) {
        Course course = mapToCourse(courseDto);
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    private Course mapToCourse(CourseDto courseDto) {
        Course course = Course.builder()
                .id(courseDto.getId())
                .name(courseDto.getName())
                .description(courseDto.getDescription())
                .teacher(courseDto.getTeacher())
                .numberOfStudents(courseDto.getNumberOfStudents())
                .schedule(courseDto.getSchedule())
                .time(courseDto.getTime())
                .build();
        return course;
    }

    private CourseDto mapToCourseDto(Course course) {
        CourseDto courseDto = CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .teacher(course.getTeacher())
                .numberOfStudents(course.getNumberOfStudents())
                .schedule(course.getSchedule())
                .time(course.getTime())
                .build();
        return courseDto;
    }


}
