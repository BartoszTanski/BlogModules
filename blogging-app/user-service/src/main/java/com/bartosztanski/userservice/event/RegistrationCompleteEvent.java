package com.bartosztanski.userservice.event;

import org.springframework.context.ApplicationEvent;

import com.bartosztanski.userservice.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = 6885179539789699252L;
	private User user;
	private String applicationUrl;
	
	public RegistrationCompleteEvent(User user, String applicationUri) {
		super(user);
		this.user = user;
		this.applicationUrl = applicationUri;
	}

}
