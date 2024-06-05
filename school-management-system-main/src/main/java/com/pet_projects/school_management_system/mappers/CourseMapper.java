package com.pet_projects.school_management_system.mappers;

import com.pet_projects.school_management_system.dto.CourseDto;
import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.models.SomeUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {

//    public Course mapToCourse(CourseDto courseDto) {
//
//        courseDto.getTeacher();
//
//        List<String> usersAsString = courseDto.getTeacher();
//        List<SomeUser> usersAsSomeUser = new ArrayList<>();
//        for (String userAsString: usersAsString) {
//            String name = userAsString.split(" ", 2)[0];
//            String surname = userAsString.split(" ", 2)[1];
//            usersAsSomeUser.add(new SomeUser(name, surname));
//        }
//
//        return Course.builder()
//                .id(courseDto.getId())
//                .name(courseDto.getName())
//                .description(courseDto.getDescription())
//                .teacherString(courseDto.getTeacher())
//                .numberOfStudents(courseDto.getNumberOfStudents())
//                .schedule(courseDto.getSchedule())
//                .time(courseDto.getTime())
//                .someUsers(usersAsSomeUser)
//                .build();
//    }
//
//    public CourseDto mapToCourseDto(Course course) {
//        return CourseDto.builder()
//                .id(course.getId())
//                .name(course.getName())
//                .description(course.getDescription())
//                .teacher(course.getTeacherString())
//                .numberOfStudents(course.getNumberOfStudents())
//                .schedule(course.getSchedule())
//                .time(course.getTime())
//                .build();
//    }
}
