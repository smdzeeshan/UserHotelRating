package com.mac.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mac.UserService.entities.User;
@Repository
public interface UserRespository extends JpaRepository<User, String>{

}
