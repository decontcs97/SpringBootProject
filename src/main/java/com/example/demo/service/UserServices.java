package com.example.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserRepo;

@Service
public class UserServices {

	@Autowired
	UserRepo userRepo;
	
	public UserServices(UserRepo repo) {
	    this.userRepo = repo;
	  }

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		userRepo.findAll().forEach(user -> users.add(user));
		users.sort(Comparator.comparing(User::getLastName));
		return users;
	}
	
	public void delete(int id) {
		userRepo.deleteById(id);
	}
	
	public User getUserById(int id) {
		
		return userRepo.findById(id).get();
	}
	
	public void addUser(User user) {
		userRepo.save(user);
	}
	
}
