package com.bartosztanski.userservice.event.listener;

import java.util.UUID;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.bartosztanski.userservice.entity.User;
import com.bartosztanski.userservice.event.RegistrationCompleteEvent;
import com.bartosztanski.userservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RegistrationCompleteEventListener  implements ApplicationListener<RegistrationCompleteEvent>{
	
	UserService userService;
	
	public RegistrationCompleteEventListener(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		// Create verification token for user with link
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		userService.saveVerificationTokenForUser(token,user);
		//Send email to user
		String url = event.getApplicationUrl()
				+"/verifyRegistration?token="
				+token;
		
		//sendVerificationEmail() {} TODO
		log.info("Click link to verify your account: {}", url);
	}

}
