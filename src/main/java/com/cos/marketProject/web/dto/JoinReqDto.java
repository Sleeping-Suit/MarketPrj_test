package com.cos.marketProject.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.marketProject.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinReqDto {
	@Size(min = 2, max = 20)
	@NotBlank
	private String username;
	
	@Size(min = 4, max = 50)
	@NotBlank
	private String password;
	
	@Size(min = 4, max = 70)
	@NotBlank
	private String name;
	
	@Size(min = 8, max = 8)
	@NotBlank
	private String birth;
	
	@Size(min = 4, max = 70)
	@NotBlank
	private String contact;

	public User toEntity() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setBirth(birth);
		user.setContact(contact);
		return user;
	}
}
