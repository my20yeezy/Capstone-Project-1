package com.pet_projects.school_management_system.controller;

import com.pet_projects.school_management_system.dto.RegistrationDto;
import com.pet_projects.school_management_system.models.Role;
import com.pet_projects.school_management_system.models.User;
import com.pet_projects.school_management_system.service.RoleService;
import com.pet_projects.school_management_system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private RoleService roleService;

//    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping({"/login", "login?error=true", "/"})
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(
            @Valid @ModelAttribute("user") RegistrationDto userDto,
            BindingResult result,
            Model model) {

        User existingUser = userService.findByEmail(userDto.getEmail());
        if (existingUser != null || userDto.getEmail().isEmpty() || userDto.getEmail().isBlank()) {
            return "redirect:/register?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register";
        }

        userService.saveUser(userDto);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDto.getPassword(),
                userDetails.getAuthorities()
        );

        return "redirect:/courses?success";
    }

}
