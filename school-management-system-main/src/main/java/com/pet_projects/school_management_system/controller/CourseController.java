package com.pet_projects.school_management_system.controller;

import com.pet_projects.school_management_system.dto.CourseDto;
import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String listCourses(Model model) {
        List<CourseDto> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/courses/new")
    public String addCourseForm(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "add-course";
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
    public String updateCourse(@PathVariable("courseId") Long courseId,
                               @Valid @ModelAttribute("course") CourseDto course,
                               BindingResult result) {
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



}
