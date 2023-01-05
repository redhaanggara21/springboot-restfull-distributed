package com.lsio.springboot.repositories.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.lsio.springboot.entities.users.RefreshToken;
import com.lsio.springboot.entities.users.User;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	RefreshToken findByRefreshToken(String token);

	Optional<RefreshToken> findByToken(String token);
	
	@Modifying
	int deleteByUser(User user);

}
