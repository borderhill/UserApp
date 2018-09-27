package com.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.data.User;
import com.sample.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getUsers() {
		
		List<User> users = new ArrayList<User>();
		for(User user : userRepository.findAll()) {
			users.add(user);
		}
		
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
		User user = new User("Ari","Rajamaki");
		return user;
	}
	
	@Override
	public User setUser(User userInfo) {
		User user = new User("Ari","Rajamaki");
		user.setId(userInfo.getId());
		user.setFirstName(userInfo.getFirstName());
		user.setLastName(userInfo.getLastName());
		return user;
	}
}
