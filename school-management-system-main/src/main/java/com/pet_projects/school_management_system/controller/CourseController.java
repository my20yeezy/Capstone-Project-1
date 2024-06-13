package com.pet_projects.school_management_system.controller;

import com.pet_projects.school_management_system.models.Course;
import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.security.SecurityUtil;
import com.pet_projects.school_management_system.service.CourseService;
import com.pet_projects.school_management_system.service.StudentService;
import com.pet_projects.school_management_system.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final SecurityUtil securityUtil;

    @GetMapping("/courses")
    public String listCourses(Model model) {
        List<Course> courses = courseService.findAllCourses();
        if (securityUtil.getSessionTeacher() == null && securityUtil.getSessionStudent() == null) {
            return "redirect:/"; //todo maybe delete?
        } else {
            Teacher teacher = null;
            Student student = null;
            if (securityUtil.getSessionTeacher() != null) {
                teacher = teacherService.findByEmail(securityUtil.getSessionTeacher().getEmail());
            }
            if (securityUtil.getSessionStudent() != null) {
                student = studentService.findByEmail(securityUtil.getSessionStudent().getEmail());
            }
            courses.sort(Comparator.comparing(Course::getName));
            model.addAttribute("courses", courses);
            model.addAttribute("teacher", teacher);
            model.addAttribute("student", student);
        }
        return "courses";
    }

    @GetMapping("/courses/new")
    public String addCourseForm(Model model) {
        if (securityUtil.getSessionTeacher() == null && securityUtil.getSessionStudent() == null) {
            return "redirect:/";
        } else {
            Teacher teacher = teacherService.findByEmail(securityUtil.getSessionTeacher().getEmail());
            model.addAttribute("teacher", teacher);
            Course course = new Course();
            model.addAttribute("course", course); //todo only teachers should be able to get to this page
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
        if (securityUtil.getSessionTeacher() == null && securityUtil.getSessionStudent() == null) {
            return "redirect:/";
        } else {
            Course course = courseService.findCourseById(courseId);
            model.addAttribute("course", course);
            return "update-course";
        }
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
    public String assignTeacher(@PathVariable("courseId") Long courseId) {
        if (securityUtil.getSessionTeacher() == null && securityUtil.getSessionStudent() == null) {
            return "redirect:/";
        } else {
            Teacher teacher = teacherService.findByEmail(securityUtil.getSessionTeacher().getEmail());
            Course course = courseService.findCourseById(courseId);
            courseService.assignTeacher(course, teacher);
            return "redirect:/courses";
        }
    }

    @PostMapping("/courses/{courseId}/unassign")
    public String unassignTeacher(@PathVariable("courseId") Long courseId) {
        if (securityUtil.getSessionTeacher() == null && securityUtil.getSessionStudent() == null) {
            return "redirect:/";
        } else {
            Teacher teacher = teacherService.findByEmail(securityUtil.getSessionTeacher().getEmail());
            Course course = courseService.findCourseById(courseId);
            courseService.unassignTeacher(course, teacher);
            return "redirect:/courses";
        }
    }

    @PostMapping("/courses/{courseId}/enroll")
    public String enrollCourse(@PathVariable("courseId") Long courseId) {
        if (securityUtil.getSessionTeacher() == null && securityUtil.getSessionStudent() == null) {
            return "redirect:/";
        } else {
            Student student = studentService.findByEmail(securityUtil.getSessionStudent().getEmail());
            Course course = courseService.findCourseById(courseId);
            courseService.enrollCourse(course, student);
            return "redirect:/courses";
        }
    }

    @PostMapping("/courses/{courseId}/drop")
    public String dropCourse(@PathVariable("courseId") Long courseId) {
        if (securityUtil.getSessionTeacher() == null && securityUtil.getSessionStudent() == null) {
            return "redirect:/";
        } else {
            Student student = studentService.findByEmail(securityUtil.getSessionStudent().getEmail());
            Course course = courseService.findCourseById(courseId);
            courseService.dropCourse(course, student);
            return "redirect:/courses";
        }
    }

    @GetMapping("/courses/{courseId}/details")
    public String courseDetailsForm(@PathVariable("courseId") long courseId, Model model) {
        Course course = courseService.findCourseById(courseId);
        Teacher teacher = teacherService.findByEmail(securityUtil.getSessionTeacher().getEmail());
        List<Student> students = courseService.findCourseById(courseId).getStudents();
        model.addAttribute("course", course);
        model.addAttribute("teacher", teacher);
        model.addAttribute("students", students);
        return "course-details";
    }

    @PostMapping("/courses/{courseId}/drop/{studentId}")
    public String dropStudent(@PathVariable("courseId") Long courseId, @PathVariable("studentId") Long studentId) {
        Student student = studentService.findById(studentId);
        Course course = courseService.findCourseById(courseId);
        courseService.dropCourse(course, student);
        return "redirect:/courses/{courseId}/details";
    }

    @GetMapping("/teachers")
    public String listTeachers(Model model) {
        List<Teacher> teachers = teacherService.findAllTeachers();
        Teacher teacher = null;
        Student student = null;
        if (securityUtil.getSessionTeacher() != null) {
            teacher = teacherService.findByEmail(securityUtil.getSessionTeacher().getEmail());
        }
        if (securityUtil.getSessionStudent() != null) {
            student = studentService.findByEmail(securityUtil.getSessionStudent().getEmail());
        }
        teachers.sort(Comparator.comparing(Teacher::getLastName));
        model.addAttribute("teachers", teachers);
        model.addAttribute("teacher", teacher);
        model.addAttribute("student", student);
        return "teachers";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> students = studentService.findAllStudents();
        Teacher teacher = null;
        Student student = null;
        if (securityUtil.getSessionTeacher() != null) {
            teacher = teacherService.findByEmail(securityUtil.getSessionTeacher().getEmail());
        }
        if (securityUtil.getSessionStudent() != null) {
            student = studentService.findByEmail(securityUtil.getSessionStudent().getEmail());
        }
        students.sort(Comparator.comparing(Student::getLastName));
        model.addAttribute("students", students);
        model.addAttribute("teacher", teacher);
        model.addAttribute("student", student);
        return "students";
    }

}
