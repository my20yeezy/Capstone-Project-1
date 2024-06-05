package com.pet_projects.school_management_system.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String role;

    private String firstName;

    private String lastName;

    private List<String> courses;

}
