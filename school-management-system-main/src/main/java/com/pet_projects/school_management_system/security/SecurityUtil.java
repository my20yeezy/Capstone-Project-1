package com.pet_projects.school_management_system.security;

import com.pet_projects.school_management_system.dto.RegistrationDto;
import com.pet_projects.school_management_system.models.User;
import com.pet_projects.school_management_system.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    private static UserServiceImpl userService;

    public SecurityUtil(UserServiceImpl userService) {
        SecurityUtil.userService = userService;
    }

    public static User getSessionUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return userService.findByEmail(authentication.getName());
        }
        return null;
    }
}
