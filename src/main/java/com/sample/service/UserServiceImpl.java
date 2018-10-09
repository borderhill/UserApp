package com.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.controller.UserSpecifications;
import com.sample.data.User;
import com.sample.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Boolean emailExists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	@Override
	public Boolean usernameExists(String userName) {
		return userRepository.findByUserName(userName).isPresent();
	}

	@Override
	public List<User> getUsers(String keyword, String email, String username) {

		System.out.println("keywork "+keyword+ " email "+ email + " username "+ username);
		List<User> users = userRepository.findAll(UserSpecifications.byKeyword(keyword, email, username));
		
		//List<User> users = new ArrayList<User>();
		//for(User user : userRepository.findAll()) {
		//	users.add(user);
		//}
		
		/*
		List<User> users = new ArrayList<User>();
		User user = new User("Ari","Rajamaki");
		user.setId(1L);
		users.add(user);
		
		user = new User("John", "Smith");
		user.setId(2L);
		users.add(user);		
		*/
 		return users;
	}
	
	@Override
	public User getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new RuntimeException("User not found");
		}
		return user.get();
	}
	
	@Override
	public User createUser(User userInfo) {
		User user = userRepository.save(userInfo);
		return user;
	}
	
	@Override
	public User updateUser(User userInfo) {
		Optional<User> user = userRepository.findById(userInfo.getId());
		if(!user.isPresent()) {
			throw new RuntimeException("User not found");
		}
		return userRepository.save(userInfo);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
