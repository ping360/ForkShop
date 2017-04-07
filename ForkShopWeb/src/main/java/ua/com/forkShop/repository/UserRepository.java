package ua.com.forkShop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import ua.com.forkShop.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

//	User findByUsername(String username);
	
	User findByEmail(String username);
}
