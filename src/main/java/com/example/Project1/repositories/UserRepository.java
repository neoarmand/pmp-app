package com.example.Project1.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project1.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}