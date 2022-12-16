package com.example.elearningproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearningproject.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
