package com.example.jwt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jwt.entity.Signup;

@Repository
public interface SignupRepository extends JpaRepository<Signup, UUID> {
	
	@Query(value = "select * from signup  where email=:email and password=:password",nativeQuery = true)
	Signup findbyemailandpassword(String email, String password);

}
