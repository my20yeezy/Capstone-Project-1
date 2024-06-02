package com.pet_projects.school_management_system.dto;


import com.pet_projects.school_management_system.models.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationDto {

    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String role;

    private String firstName;

    private String lastName;

}
