package com.pet_projects.school_management_system.controller;

import com.pet_projects.school_management_system.dto.CourseDto;
import com.pet_projects.school_management_system.mappers.CourseMapper;
import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.models.SomeUser;
import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.security.SecurityUtil;
import com.pet_projects.school_management_system.service.CourseService;
import com.pet_projects.school_management_system.service.StudentService;
import com.pet_projects.school_management_system.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class CourseController {

    @Autowired
    private CourseService courseService;

    private final CourseMapper courseMapper;

    private final TeacherService teacherService;
    private final StudentService studentService;

    @GetMapping("/courses")
    public String listCourses(Model model) {
        List<Course> courses = courseService.findAllCourses();
        if (SecurityUtil.getSessionTeacher() == null && SecurityUtil.getSessionStudent() == null) {
            return "redirect:/";
        } else {
            Teacher teacher = teacherService.findByEmail(Objects.requireNonNull(SecurityUtil.getSessionTeacher().getEmail()));
            Student student = studentService.findByEmail(Objects.requireNonNull(SecurityUtil.getSessionStudent()).getEmail());
            model.addAttribute("courses", courses);
            if(teacher != null) {
                model.addAttribute("teacher", teacher);
            }
            if (student != null) {
                model.addAttribute("student", student);
            }
            return "courses";
        }
    }

    @GetMapping("/courses/new")
    public String addCourseForm(Model model) {
        if (SecurityUtil.getSessionTeacher() == null && SecurityUtil.getSessionStudent() == null) {
            return "redirect:/";
        } else {
            Teacher teacher = teacherService.findByEmail(SecurityUtil.getSessionTeacher().getEmail());
            Student student = studentService.findByEmail(SecurityUtil.getSessionStudent().getEmail());
            model.addAttribute("teacher", teacher);
            model.addAttribute("student", student);
            Course course = new Course();
            model.addAttribute("course", course);
            return "add-course";
        }
    }

    @PostMapping("/courses/new")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("course", course);
            return "add-course";
        }
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{courseId}/update")
    public String updateCourseForm(@PathVariable("courseId") long courseId, Model model) {
        Course course = courseService.findCourseById(courseId);
        model.addAttribute("course", course);
        return "update-course";
    }

    @PostMapping("/courses/{courseId}/update")
    public String updateCourse(
            @PathVariable("courseId") Long courseId,
            @Valid @ModelAttribute("course") Course course,
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
        Teacher teacher = teacherService.findByEmail(Objects.requireNonNull(SecurityUtil.getSessionTeacher()).getEmail());
        Course course = courseService.findCourseById(courseId);
        courseService.assignTeacher(course, teacher);
        return "redirect:/courses";
    }

    @PostMapping("/courses/{courseId}/unassign")
    public String unassignTeacher(@PathVariable("courseId") Long courseId) {
        Teacher teacher = teacherService.findByEmail(Objects.requireNonNull(SecurityUtil.getSessionTeacher()).getEmail());
        Course course = courseService.findCourseById(courseId);
        courseService.unassignTeacher(course, teacher);
        return "redirect:/courses";
    }

}
