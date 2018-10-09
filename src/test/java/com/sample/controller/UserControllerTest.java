package com.sample.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sample.data.User;
import com.sample.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = com.sample.controller.UserController.class, secure = false)
//@SpringBootTest
//@AutoConfigureMockMvc
@ContextConfiguration(classes= {com.sample.controller.UserController.class})
public class UserControllerTest {

    @MockBean
    UserService userServiceMock;

    @Autowired
    private MockMvc mvc;
    
    @Test
    public void getUsers() throws Exception {
		List<User> users = new ArrayList<User>();
		User user1 = new User("Ari", "Rajamaki");
		user1.setId(1L);
		//users.add(user);
		
		User user2 = new User("John", "Smith");
		user2.setId(2L);
		//users.add(user);		
		when(userServiceMock.getUsers(null, null, null)).thenReturn(Arrays.asList(user1, user2));    	
    	
		Object result = mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
        System.out.println("Class " + result.getClass().getName());

        MvcResult result2 = mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
		
        System.out.println("Class " + result2.getClass().getName());
        System.out.println("getResponse " + result2.getResponse());
        MockHttpServletResponse response = result2.getResponse();
        HttpHeaders headers = new HttpHeaders();
        for (String name : response.getHeaderNames()) {
        	List<String> values = response.getHeaders(name);
        	for (String value : values) {
        		System.out.println("header name " + name + ", value" + value);
        		headers.add(name, value);
        	}
        }  
        
        System.out.println("response.getContentAsString() " + response.getContentAsString());
        
    }

}
