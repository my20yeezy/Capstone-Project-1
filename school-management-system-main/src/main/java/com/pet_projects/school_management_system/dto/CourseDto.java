package com.pet_projects.school_management_system.dto;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
@Builder

public class CourseDto {

    private Long id;

    @NotEmpty(message = "Course name should not be empty")
    private String name;

    private String description;

    private String teacher;

    @Column(columnDefinition = "integer default 0")
    private Integer numberOfStudents = 0;

    private String schedule;

    private String time;

}
