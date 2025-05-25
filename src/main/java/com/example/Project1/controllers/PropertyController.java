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
import org.springframework.web.bind.annotation.RequestParam;
import com.example.Project1.models.Property;
import com.example.Project1.services.PropertyService;

@Controller

public class PropertyController {

	@Autowired
	private PropertyService propertyService;

	@GetMapping("/")
	public String ViewHomePage(Model model) {
		return findPaginated(1, "propertyName", "asc", model);
	}

	@GetMapping("/showNewPropertyForm")
	public String showNewPropertyForm(Model model) {
		Property property = new Property();
		model.addAttribute("property", property);
		return "new_property";
	}

	@PostMapping("/saveProperty")
	public String saveProperty(@ModelAttribute("property") Property property) {
		propertyService.saveProperty(property);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		Property property = propertyService.getpropertyById(id);
		model.addAttribute("property", property);
		return "update_property";
	}

	@GetMapping("/deleteProperty/{id}")
	public String deleteProperty(@PathVariable(value = "id") long id) {
		this.propertyService.deletePropertyById(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortField") String sortDir, Model model) {
		int pageSize = 6;
		Page<Property> page = propertyService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Property> listProperties = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listProperties", listProperties);
		return "index";
	}
}