package com.cos.marketProject.web.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
	
	@NotBlank
	@Size(min = 4, max = 70)
	private String password;
	
	@NotBlank
	@Size(min = 2, max = 20)
	private String contact;
}