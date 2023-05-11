package com.bartosztanski.userservice.service;

import java.util.Calendar;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bartosztanski.userservice.entity.User;
import com.bartosztanski.userservice.entity.VerificationToken;
import com.bartosztanski.userservice.model.UserModel;
import com.bartosztanski.userservice.repository.UserRepository;
import com.bartosztanski.userservice.repository.VerificationTokenRepository;


@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			VerificationTokenRepository verificationTokenRepository) {
		this.userRepository = userRepository;
		this.verificationTokenRepository = verificationTokenRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public User register(UserModel userModel) {
		User user = User.builder()
				.username(userModel.getUsername())
				.password(passwordEncoder.encode(userModel.getPassword()))
				.email(userModel.getEmail())
				.role("USER")
				.build();
		userRepository.insert(user);
		return user;
	}

	@Override
	public void saveVerificationTokenForUser(String token, User user) {
		VerificationToken verificationToken
			= new VerificationToken(user,token);
		verificationTokenRepository.insert(verificationToken);
	}

	@Override
	public String validateVerificationToken(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
		if(verificationToken == null) return "invalid";
		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if((verificationToken.getExpiationTime().getTime()
				- cal.getTime().getTime()) <=0) {
			verificationTokenRepository.delete(verificationToken);
			return "expired";
		}
		user.setEnabled(true);
		userRepository.save(user);
		return "valid";
	}

}
