package com.example.Project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project1.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}