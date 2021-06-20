package com.bookmark.main.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmark.main.models.User;
import com.bookmark.main.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	UserService userService = new UserService();
	
//	for get all users
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return list;
	}
	
//	for login
	 
	 @PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		if(userService.login(user)) {
			return ResponseEntity.ok().body("Login Sucessfull");
		} else {
			return ResponseEntity.ok().body("Login failed");
		}
	}
	
//	for register
	 
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user)  {
		if(userService.signup(user)) {
			return ResponseEntity.ok().body("SignUp Sucessfull");
		} else {
			return ResponseEntity.ok().body("SignUp failed");
		}
	}
}
