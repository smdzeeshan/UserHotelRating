package com.mac.UserService.services.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mac.UserService.entities.User;
import com.mac.UserService.exceptions.ResourceNotFoundException;
import com.mac.UserService.repositories.UserRespository;
import com.mac.UserService.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRespository repo;
	
	@Override
	public User saveUser(User user) {
		String id = UUID.randomUUID().toString();
		user.setUserId(id);
		repo.save(user);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public User getUser(String userId) {
		return repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id = "+ userId +" not found in our records!"));
		
	}

}
