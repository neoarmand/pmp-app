package com.example.Project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Project1.models.Problem;

@Repository
public interface ProblemRepository extends JpaRepository <Problem,Long>{
    
}
