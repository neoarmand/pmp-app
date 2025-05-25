package com.example.Project1.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.Project1.models.Problem;




public interface ProblemService {

    List<Problem> getAllProblems();

    void saveProblem(Problem problem);
    
    Problem getproblemById(long id);
    
    void deleteProblemById(long id);
    
    Page<Problem> findPaginatedPro(int pageNo,int pageSize,String sortField,String sortDirection);
}
