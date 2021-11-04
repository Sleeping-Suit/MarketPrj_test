package com.cos.marketProject.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 20, unique = true)
	private String username;
	@Column(nullable = false, length = 20)
	private String password;
	@Column(nullable = false, length = 8)
	private String name;
	@Column(nullable = false, length = 8)
	private String birth;
	@Column(nullable = false, length = 12)
	private String contact;
}
