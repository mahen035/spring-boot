package com.example.elearningproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearningproject.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
