package com.pet_projects.school_management_system.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder

public class CourseDto {

    private Long id;

    @NotEmpty(message = "Course name should not be empty")
    private String name;

    private String description;

    private String teacher;

//    private Integer numberOfStudents;

    private String schedule;

    private String time;

}
