package com.cos.marketProject.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "insert into user (username, password, name, birth, contact) values (:username, :password, :name, :birth, :contact)", nativeQuery = true)
	void join(String username, String password, String name, String birth, String contact);

	@Query(value = "select * from user where username=:username and password=:password", nativeQuery = true)
	User mLogin(String username, String password);
	
	// mIdFind 추가
//	@Query(value = "select * from user where username=:username and birth=:birth and contact=:contact", nativeQuery = true)
//	User mIdFind(String name, String birth, String contact);
//	
//	@Query(value = "select * from user where username=:username and name=:name and birth=:birth and contact=:contact", nativeQuery = true)
//	User mPWFind(String username, String name, String birth, String contact);
//	
//	@Query(value = "update user set password = :password  where id = :id", nativeQuery = true)
//	User mChangePw(int id, String password);

}