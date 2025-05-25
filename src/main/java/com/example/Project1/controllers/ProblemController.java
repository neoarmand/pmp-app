package com.example.Project1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Project1.models.Problem;
import com.example.Project1.models.Property;
import com.example.Project1.services.ProblemService;
import com.example.Project1.services.PropertyService;

@Controller
@RequestMapping("/maintenance")

public class ProblemController {

	@Autowired
	private ProblemService problemService;

	@GetMapping
	public String viewMaintenancePage(Model model) {
	    return findPaginatedPro(1, "registerDate", "asc", model);
	}

	@Autowired
	private PropertyService propertyService;

	@GetMapping("/showNewProblemForm")
	public String showNewProblemForm(Model model) {
	    Problem problem = new Problem();
	    model.addAttribute("problem", problem);

	    // Add this line to fetch and pass properties to the model
	    List<Property> properties = propertyService.getAllProperties(); 
	    model.addAttribute("properties", properties);

	    return "register_problem";
	}


	@PostMapping("/saveProblem")
	public String saveProblem(@ModelAttribute("problem") Problem problem) {
		problemService.saveProblem(problem);
		return "redirect:/maintenance";
	}

	@GetMapping("/showFormForUpdatePro/{id}")
	public String showFormForUpdatePro(@PathVariable(value = "id") long id, Model model) {
		Problem problem = problemService.getproblemById(id);
		model.addAttribute("problem", problem);
		return "register_problem";
	}

	@GetMapping("/deleteProblem/{id}")
	public String deleteProblem(@PathVariable(value = "id") long id) {
		this.problemService.deleteProblemById(id);
		return "redirect:/maintenance";
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginatedPro(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortField") String sortDir, Model model) {
		int pageSize = 6;
		Page<Problem> page = problemService.findPaginatedPro(pageNo, pageSize, sortField, sortDir);
		List<Problem> listProblems = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listProblems", listProblems);
		return "maintenance";
	}
}