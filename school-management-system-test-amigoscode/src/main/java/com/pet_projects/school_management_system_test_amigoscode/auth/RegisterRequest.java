package com.pet_projects.school_management_system_test_amigoscode.auth;

import com.pet_projects.school_management_system_test_amigoscode.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private String password;

}
