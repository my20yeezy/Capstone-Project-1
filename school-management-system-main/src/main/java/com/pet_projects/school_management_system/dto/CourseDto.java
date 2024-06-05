package com.pet_projects.school_management_system.dto;


import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
public class CourseDto {

    private Long id;

    @NotEmpty(message = "Course name should not be empty")
    private String name;

    private String description;

    private Teacher teacher;

    private String teacherString;

    @Column(columnDefinition = "integer default 0")
    private Integer numberOfStudents = 0;

    private String schedule;

    private String time;

    private List<Student> students;

}
