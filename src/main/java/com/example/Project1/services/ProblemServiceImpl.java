package com.example.Project1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Project1.models.Problem;
import com.example.Project1.repositories.ProblemRepository;

@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemRepository problemRepository;

	@Override
	public List<Problem> getAllProblems() {
		return problemRepository.findAll();
	}

	@Override
	public void saveProblem(Problem problem) {
		this.problemRepository.save(problem);
	}

	@Override
	public Problem getproblemById(long id) {
		Optional<Problem> optional = problemRepository.findById(id);
		Problem problem = null;
		if (optional.isPresent()) {
			problem = optional.get();
		} else {
			throw new RuntimeException("Problem not found by id : :" + id);
		}
		return problem;
	}

	@Override
	public void deleteProblemById(long id) {
		this.problemRepository.deleteById(id);
	}
	
    @Override
    public Page<Problem> findPaginatedPro(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.problemRepository.findAll(pageable);
    }
}