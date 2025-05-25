package com.example.Project1.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Project1.dto.UserDto;
import com.example.Project1.services.UserService;
import com.example.Project1.models.User;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private UserService userService;

    public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    @GetMapping("/renters")
    public String renters(Model model){
        List<UserDto> renters = userService.findAllRenters();
        model.addAttribute("renters", renters);
        return "renters"; 
    }

    @GetMapping("/deleteRenter/{id}")
    public String deleteRenter(@PathVariable(value = "id") long id) {
        userService.deleteUserById(id);
        return "redirect:/renters";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}