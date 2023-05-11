package com.bartosztanski.userservice.entity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "verification_tokens")
public class VerificationToken {
	//expiration time 15 min
	private static final int EXPITATION_TIME = 15;
	@Id
	private long id;
	private String token;
	private Date expiationTime;
	private User user;
	
	public VerificationToken(User user, String token) {
		super();
		this.token = token;
		this.user = user;
		this.expiationTime = calculateExpirationDate(EXPITATION_TIME);
	}
	
	public VerificationToken(String token) {
		super();
		this.token = token; 
		this.expiationTime = calculateExpirationDate(EXPITATION_TIME);
	}

	private Date calculateExpirationDate(int expirationTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, expirationTime);
		return new Date(calendar.getTime().getTime());
	}
}
