package com.pet_projects.school_management_system.controller;

import com.pet_projects.school_management_system.models.SomeUser;
import com.pet_projects.school_management_system.models.Student;
import com.pet_projects.school_management_system.models.Teacher;
import com.pet_projects.school_management_system.service.RoleService;
import com.pet_projects.school_management_system.service.StudentService;
import com.pet_projects.school_management_system.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    private TeacherService teacherService;
    private StudentService studentService;
    private RoleService roleService;

//    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping({"/login", "login?error=true", "/"})
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register-teacher")
    public String getRegisterTeacherForm(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "register-teacher";
    }

    @GetMapping("/register-student")
    public String getRegisterStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "register-student";
    }

    @PostMapping("/register/save/teacher")
    public String registerTeacher(
            @Valid @ModelAttribute("teacher") Teacher teacher,
            BindingResult result,
            Model model) {

        Teacher existingTeacher = teacherService.findByEmail(teacher.getEmail());
        if (existingTeacher != null || teacher.getEmail().isEmpty() || teacher.getEmail().isBlank()) {
            return "redirect:/register?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("teacher", teacher);
            return "register";
        }

        teacherService.saveTeacher(teacher);

        return "redirect:/login";
    }

    @PostMapping("/register/save/student")
    public String registerStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult result,
            Model model) {

        Student existingStudent = studentService.findByEmail(student.getEmail());
        if (existingStudent != null || student.getEmail().isEmpty() || student.getEmail().isBlank()) {
            return "redirect:/register?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "register";
        }

        studentService.saveStudent(student);

        return "redirect:/login";
    }

}
