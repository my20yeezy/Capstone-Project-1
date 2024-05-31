//package com.pet_projects.school_management_system.repositories;
//
//import com.pet_projects.school_management_system.models.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    Optional<User> findByEmail(String email);
//
//    @Modifying
//    @Query(value = "UPDATE User u set u.firstName =:firstName,"+
//            " u.lastName =:lastName," + "u.email =:email where u.id =:id")
//    void update(String firstName, String lastName, String email, Long id);
//}
