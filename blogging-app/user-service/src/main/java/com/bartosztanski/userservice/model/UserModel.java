package com.bartosztanski.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	
	private String username;
	private String matchingPassword;
	private String password;
	private String email;

}
