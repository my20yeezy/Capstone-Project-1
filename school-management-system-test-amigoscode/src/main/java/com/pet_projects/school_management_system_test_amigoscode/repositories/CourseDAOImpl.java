package com.pet_projects.school_management_system_test_amigoscode.repositories;

import com.pet_projects.school_management_system_test_amigoscode.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseDAOImpl {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Course> getAllCourses() {

        Session session = sessionFactory.getCurrentSession();
        List<Course> allCourses = session.createQuery("FROM Course", Course.class).getResultList();

        return allCourses;
    }
}
