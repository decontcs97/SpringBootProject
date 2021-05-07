package com.example.demo.tests;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.controller.Controller;
import com.example.demo.model.User;
import com.example.demo.model.UserRepo;
import com.example.demo.service.UserServices;

@Import(Controller.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRepo.class)
class ControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserServices userService;
	
	@Mock
	private UserRepo userRepo;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Test
	public void getUsersTest() throws Exception {
		Mockito.when(userService.getUsers()).thenReturn(Arrays.asList(
				new User("Chris", "D"),
				new User("Jeff", "A"),
				new User("Dave", "B")
				));
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mvc.perform(
				MockMvcRequestBuilders.get("/users"))
				.andExpect(status().isOk())
				.andExpect(content().json(("[{}, {}, {}]")));
		
	}
	
	@Test
	public void findByIdTest() throws Exception {
		Mockito.when(userRepo.findById(1)).thenReturn(
				Optional.of(new User("Chris", "D")));
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mvc.perform(
				MockMvcRequestBuilders.get("/users/1"))
				.andExpect(status().isOk());
		
	}
	
	@Test
	public void deleteById() throws Exception {
		Mockito.when(userRepo.findById(1)).thenReturn(
				Optional.of(new User("Chris", "D")));
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mvc.perform(
				MockMvcRequestBuilders.delete("/users/1"))
				.andExpect(status().isOk());
	}
	

}
