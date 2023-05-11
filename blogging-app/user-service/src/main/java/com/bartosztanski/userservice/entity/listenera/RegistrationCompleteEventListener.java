package com.bartosztanski.userservice.entity.listenera;

import java.util.UUID;

import org.springframework.context.ApplicationListener;

import com.bartosztanski.userservice.entity.User;
import com.bartosztanski.userservice.event.RegistrationCompleteEvent;

public class RegistrationCompleteEventListener  implements ApplicationListener<RegistrationCompleteEvent>{

	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		// Create verification token for user with link
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		//Send email to user
		
	}

}
