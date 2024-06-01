package com.pet_projects.school_management_system.controller;

import com.pet_projects.school_management_system.dto.RegistrationDto;
import com.pet_projects.school_management_system.models.User;
import com.pet_projects.school_management_system.service.UserService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(
            @Valid @ModelAttribute("user") RegistrationDto user,
            BindingResult result,
            Model model) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null || user.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/courses?success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
