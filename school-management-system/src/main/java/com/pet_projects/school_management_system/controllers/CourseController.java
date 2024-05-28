package com.pet_projects.school_management_system.controllers;

import com.pet_projects.school_management_system.services.CourseService;
import com.pet_projects.school_management_system.services.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String getAllCourses(Model model) {
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "courses";
    }

    @GetMapping("/welcome")
    public String welcomeScreen(Model model) {

        return "welcome";
    }

}
