package com.example.demo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.example.demo.model.User;
import com.example.demo.model.UserRepo;
import com.example.demo.service.UserServices;
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRepo.class)
class UserServicesTest {

	
	@InjectMocks
	private UserServices userService;
	@Mock
	private UserRepo userRepo;
	
	@Test
	void getUsersTest() throws Exception {
		Mockito.when(userRepo.findAll()).thenReturn(Arrays.asList(
				new User("Chris", "D"),
				new User("Dave", "B"),
				new User("Jeff", "A")
				));
		List<User> users = userService.getUsers();
		assertEquals("Jeff", users.get(0).getFirstname());
		assertEquals("Dave", users.get(1).getFirstname());
		assertEquals("Chris", users.get(2).getFirstname());
	}
	
	@Test
	void getUserByIdTest() throws Exception {
		Mockito.when(userRepo.findById(1)).thenReturn(
				Optional.of(new User("Chris", "D")));
		User user = userService.getUserById(1);
		assertEquals("D", user.getLastName());
	}
	
	@Test
	void deleteUserByIdTest() throws Exception {
		User user = new User("Chris", "D");
		userService.addUser(user);
		userService.delete(1);
		verify(userRepo, times(1)).deleteById(1);
	}

}
