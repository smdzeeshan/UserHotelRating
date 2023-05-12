package com.mac.UserService.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mac.UserService.entities.User;
@Service
public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUser(String userId);
	

}
