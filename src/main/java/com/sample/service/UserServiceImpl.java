package com.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.data.User;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setId(1L);
		user.setName("ARI");
		users.add(user);
		
		user.setId(2L);
		user.setName("ARI2");
		users.add(user);		
 		return users;
	}
	
	@Override
	public User getUser(Long id) {
		User user = new User();
		return user;
	}
	
	@Override
	public User setUser(User userInfo) {
		User user = new User();
		user.setId(userInfo.getId());
		user.setName(userInfo.getName());
		//user.setLastName(userInfo.getLastName());
		return user;
	}
}
