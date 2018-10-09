package com.sample.service;

import java.util.List;

import com.sample.data.User;

public interface UserService {

	public Boolean emailExists(String email);
	public Boolean usernameExists(String username);
	
	public List<User> getUsers(String keyword, String email, String username);
	
	public User getUser(Long id);
	
	public User createUser(User user);
	public void deleteUser(Long id);
	public User updateUser(User user);

}
