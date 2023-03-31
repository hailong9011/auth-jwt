package com.a2m.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a2m.lab.entities.UserEntity;
import com.a2m.lab.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping(value = "/register")
	ResponseEntity<?> register(@RequestBody UserEntity userEntity) {
		return ResponseEntity.ok(userService.saveUser(userEntity));
	}

	@PostMapping(value = "/login")
	ResponseEntity<?> login(@RequestBody UserEntity userEntity) {
		return null;
	}
}
