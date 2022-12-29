package com.lsio.springboot.repositories;

import com.lsio.springboot.entities.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    
    public Course findById(int id);
}
