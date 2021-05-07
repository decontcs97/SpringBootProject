package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserServices;

@RestController
public class Controller {

	@Autowired
	UserServices userService;
	
    
	@GetMapping("/users")
	private List<User> getAllUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/users/{id}")
	private User getUser(@PathVariable("id") int id) {
		return userService.getUserById(id);
	}
	
	@DeleteMapping("/users/{id}")
	private void deleteUser(@PathVariable("id") int id) {
		userService.delete(id);
	}
	
	@PostMapping("/users")
	private int addUser(@RequestBody User user) {
		userService.addUser(user);
		return user.getId();
		
	}
}
