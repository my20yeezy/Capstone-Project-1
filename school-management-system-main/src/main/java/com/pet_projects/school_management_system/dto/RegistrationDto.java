package com.pet_projects.school_management_system.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationDto {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
