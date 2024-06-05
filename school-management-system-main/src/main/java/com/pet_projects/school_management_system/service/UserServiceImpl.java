//package com.pet_projects.school_management_system.service;
//
//import com.pet_projects.school_management_system.dto.UserDto;
//import com.pet_projects.school_management_system.models.SomeUser;
//import com.pet_projects.school_management_system.repository.RoleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//
//    private final RoleRepository roleRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void saveUser(UserDto userDto) {
//
////        SomeUser someUser = new SomeUser();
////
////        someUser.setEmail(userDto.getEmail());
////        someUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
////        someUser.setRole(roleRepository.findByName(userDto.getRole()));
////        someUser.setFirstName(userDto.getFirstName());
////        someUser.setLastName(userDto.getLastName());
////        userRepository.save(someUser);
//    }
//
//
//    @Override
//    public SomeUser findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//}
