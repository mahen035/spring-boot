package com.example.elearningproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.elearningproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}

