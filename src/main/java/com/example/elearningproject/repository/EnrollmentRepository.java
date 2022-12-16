package com.example.elearningproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearningproject.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

}
