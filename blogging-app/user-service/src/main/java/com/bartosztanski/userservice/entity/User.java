package com.bartosztanski.userservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "users")
public class User {
	@Id
	private Long id;
	private String username;
	private String password;
	private String email;
	private String role;
	@Builder.Default
	private boolean enabled = false; 
}
