package com.pet_projects.school_management_system.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String teacher;

    @Column(columnDefinition = "integer default 0")
    private Integer numberOfStudents;
    //todo set default value of 0

    private String schedule;

    private String time;





}
