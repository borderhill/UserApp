package com.sample.service;

import java.util.List;

import com.sample.data.User;

public interface UserService {

	public List<User> getUsers();
	
	public User getUser(Long id);
	
	public User setUser(User userInfo);

}
