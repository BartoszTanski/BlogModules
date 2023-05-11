package com.bartosztanski.userservice.service;

import org.springframework.stereotype.Service;

import com.bartosztanski.userservice.entity.User;
import com.bartosztanski.userservice.model.UserModel;

@Service
public interface UserService {

	User register(UserModel userModel);

	void saveVerificationTokenForUser(String token, User user);

	String validateVerificationToken(String token);

}
