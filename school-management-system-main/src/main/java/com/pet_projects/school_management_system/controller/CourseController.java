package com.pet_projects.school_management_system.controller;

import com.pet_projects.school_management_system.dto.CourseDto;
import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.models.User;
import com.pet_projects.school_management_system.security.SecurityUtil;
import com.pet_projects.school_management_system.service.CourseService;
import com.pet_projects.school_management_system.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserServiceImpl userService;

    public CourseController(CourseService courseService, UserServiceImpl userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model) {
        List<CourseDto> courses = courseService.findAllCourses();
        if (SecurityUtil.getSessionUser() == null) {
            return "redirect:/";
        } else {
            User user = userService.findByEmail(SecurityUtil.getSessionUser().getEmail());
            model.addAttribute("courses", courses);
            model.addAttribute("user", user);
            return "courses";
        }
    }

    @GetMapping("/courses/new")
    public String addCourseForm(Model model) {
        if (SecurityUtil.getSessionUser() == null) {
            return "redirect:/";
        } else {
            User user = userService.findByEmail(SecurityUtil.getSessionUser().getEmail());
            model.addAttribute("user", user);
            Course course = new Course();
            model.addAttribute("course", course);
            return "add-course";
        }
    }

    @PostMapping("/courses/new")
    public String saveCourse(@Valid @ModelAttribute("course") CourseDto courseDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("course", courseDto);
            return "add-course";
        }
        courseService.saveCourse(courseDto);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{courseId}/update")
    public String updateCourseForm(@PathVariable("courseId") long courseId, Model model) {
        CourseDto course = courseService.findCourseById(courseId);
        model.addAttribute("course", course);
        return "update-course";
    }

    @PostMapping("/courses/{courseId}/update")
    public String updateCourse(
            @PathVariable("courseId") Long courseId,
            @Valid @ModelAttribute("course") CourseDto course,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "update-course";
        }
        course.setId(courseId);
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{courseId}/delete")
    public String deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/courses";
    }

    @PostMapping("/courses/{courseId}/assign")
    public String  assignTeacher(@PathVariable("courseId") Long courseId) {
        User user = userService.findByEmail(Objects.requireNonNull(SecurityUtil.getSessionUser()).getEmail());

        CourseDto courseDto = courseService.findCourseById(courseId);
        Course course = courseService.mapToCourse(courseDto);

        courseService.assignTeacher(course, user);
        return "redirect:/courses";
    }

    @PostMapping("/courses/{courseId}/unassign")
    public String unassignTeacher(@PathVariable("courseId") Long courseId) {
        User user = userService.findByEmail(Objects.requireNonNull(SecurityUtil.getSessionUser()).getEmail());

        CourseDto courseDto = courseService.findCourseById(courseId);
        Course course = courseService.mapToCourse(courseDto);


        courseService.unassignTeacher(course, user);
        return "redirect:/courses";
    }

}
