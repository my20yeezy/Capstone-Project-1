//package com.pet_projects.school_management_system.services;
//
//import com.pet_projects.school_management_system.models.Role;
//import com.pet_projects.school_management_system.models.User;
//import com.pet_projects.school_management_system.repositories.UserRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
////    @Override
////    public User registerUser(RegistrationRequest registration) {
////        var user = new User(registration.getFirstName(), registration.getLastName(),
////                registration.getEmail(),
////                passwordEncoder.encode(registration.getPassword()),
////                Arrays.asList(new Role("ROLE_USER")));
////        return userRepository.save(user);
////    }
//
//    @Override
//    public Optional<User> findByEmail(String email) {
//        return Optional.ofNullable(userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    @Transactional
//    @Override
//    public void updateUser(Long id, String firstName, String lastName, String email) {
//        userRepository.update(firstName, lastName, email, id);
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//
//    }
//
////    @Transactional
////    @Override
////    public void deleteUser(Long id) {
////        Optional<User> theUser = userRepository.findById(id);
////        theUser.ifPresent(user -> verificationTokenService.deleteUserToken(user.getId()));
////        userRepository.deleteById(id);
////    }
//
//}
