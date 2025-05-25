package com.example.Project1.services;

import java.util.List;

import com.example.Project1.dto.UserDto;
import com.example.Project1.models.User;


public interface UserService {
	
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
    
    List<UserDto> findAllRenters();
    
    void deleteUserById(long id);

}
