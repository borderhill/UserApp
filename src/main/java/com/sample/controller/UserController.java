package com.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.data.User;
import com.sample.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/users")
	@ResponseBody
	public List<User> getUsers() {
		
		List<User> users = userService.getUsers();
		//List<User> users = new ArrayList<User>();
		//users.add(new User("Ari","Rajamaki"));
		//users.add(new User("Ari2","Rajamaki"));		
		return users;
	}

	@GetMapping(value = "/users/{id}")
	@ResponseBody
	public User getUser(@PathVariable String id) {
		User user = userService.getUser(new Long(id));
		user = new User("Ari","Rajamaki");
		user.setId(new Long(id));
		return user;
	}
}
