package com.bartosztanski.userservice.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartosztanski.userservice.entity.User;
import com.bartosztanski.userservice.event.RegistrationCompleteEvent;
import com.bartosztanski.userservice.model.UserModel;
import com.bartosztanski.userservice.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//RequestMapping("/api/v1/")
//@CrossOrigin(origins = {"https://bartosztanski.azurewebsites.net/","http://localhost:3000"})
public class RegistrationController {
	
	private final UserService userService;
	private ApplicationEventPublisher publisher;
	
	public RegistrationController(UserService userService,
			ApplicationEventPublisher publisher) {
		this.userService = userService;
		this.publisher = publisher;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
		User user = userService.register(userModel);
		publisher.publishEvent(new RegistrationCompleteEvent(user,
				applicationUrl(request)));
		return ResponseEntity.ok("Registration successfull");
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/verifyRegistration")
	public String verifyRegistration(@RequestParam("token") String token) {
		String result = userService.validateVerificationToken(token);
		if(result.equalsIgnoreCase("valid")) {
			return "User verified successfully";
		}
		else return "Bad User";
	}
	
	private String applicationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":"
		+ request.getServerPort() + request.getContextPath();
	}
}
