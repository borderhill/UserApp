package com.sample.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sample.data.User;
import com.sample.service.UserService;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
    @GetMapping(value = "exists")
    public ResponseEntity<Object> exists(
        @RequestParam(name = "username", required = false) String username,
        @RequestParam(name = "email", required = false) String email
    ) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(email)) {
            return badRequest().build();
        }

        if (StringUtils.hasText(username) && userService.usernameExists(username)) {
        	throw new RuntimeException(username + "already taken");
        }

        if (StringUtils.hasText(email) && userService.emailExists(email)) {
            throw new RuntimeException(email + "already taken");
        }

        return ok().build();
    }

	@GetMapping(value = "")
	@ResponseBody
	public List<User> getAll(
	        @RequestParam(value = "q", required = false) String q,
	        @RequestParam(value = "email", required = false) String email,
	        @RequestParam(value = "username", required = false) String username
			) {
		
		// Specification<User> specs = UserSpecifications.byKeyword(q, email, username);
		List<User> users = userService.getUsers(q, email, username);
		//List<User> users = new ArrayList<User>();
		//users.add(new User("Ari","Rajamaki"));
		//users.add(new User("Ari2","Rajamaki"));		
		return users;
	}

	@GetMapping(value = "{id}")
	@ResponseBody
	public User getUser(@PathVariable Long id) {
		User user = userService.getUser(id);
		return user;
	}
	
    @PostMapping(value = {""})
    public ResponseEntity<User> createUser(
    		@RequestBody User user) {
        log.debug("user data@" + user);

        if (StringUtils.hasText(user.getUserName()) && userService.usernameExists(user.getUserName())) {
        	throw new RuntimeException(user.getUserName() + "already taken");
        }

        if (StringUtils.hasText(user.getEmail()) && userService.emailExists(user.getEmail())) {
            throw new RuntimeException(user.getEmail() + "already taken");
        }

        User saved = userService.createUser(user);

        //URI createdUri = ServletUriComponentsBuilder
        //    .fromContextPath(req)
        //    .path("/users/{username}")
        //    .buildAndExpand(saved.getId()).toUri();
        URI createdUri = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(saved.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(createdUri);
        ResponseEntity<User> result = new ResponseEntity<User>(headers, HttpStatus.CREATED);
        return ok(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(
    		@PathVariable Long id,
    		@RequestBody User user) {
        log.debug("user data@" + user);

        try {
	        User currentUser = userService.getUser(id);
        } catch (RuntimeException ex) {
        	return notFound().build();
        }
        
	        User saved = userService.updateUser(user);
        //URI createdUri = ServletUriComponentsBuilder
        //    .fromContextPath(req)
        //    .path("/users/{username}")
        //    .buildAndExpand(saved.getId()).toUri();
        //URI createdUri = ServletUriComponentsBuilder.fromCurrentRequest();

        return ok(saved);
    }

    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        User user = userService.getUser(Long.valueOf(id));
        
        userService.deleteUser(user.getId());

        return noContent().build();
    }

    /*
    @GetMapping(value = "/{username}")
    public ResponseEntity getById(@PathVariable("username") String username) {
        User _user = this.userRepository.findByUsername(username).orElseThrow(
            () -> {
                return new UserNotFoundException(username);
            }
        );

        return ok(_user);
    }

    @PostMapping(value = "/{username}/lock")
    public ResponseEntity lockUser(@PathVariable("username") String username) {

        log.debug("locking user:" + username);

        this.userService.lock(username);

        return noContent().build();
    }

    @DeleteMapping(value = "/{username}/lock")
    public ResponseEntity unlockUser(@PathVariable("username") String username) {

        log.debug("unlocking user:" + username);

        this.userService.unlock(username);

        return noContent().build();
    }
	*/
}
