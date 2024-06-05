package com.pet_projects.school_management_system.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SomeUser {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

//    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "role_id")
//    private Role role;

    private String firstName;

    private String lastName;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(
//            name = "user_course",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns =@JoinColumn(name = "course_id"))
//    private List<Course> courses = new ArrayList<>();


//    public SomeUser(String email, String password, Role role, String firstName, String lastName) {
//        this.email = email;
//        this.password = password;
//        this.role = role;
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    public SomeUser(String name, String surname) {
//        this.firstName = name;
//        this.lastName = surname;
//    }
}
