package com.POC.demoProject.testController;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.POC.demoProject.model.Response;
import com.POC.demoProject.model.UserController;
import com.POC.demoProject.model.UserModel;
import com.POC.demoProject.model.UserRepository;
import com.POC.demoProject.model.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
@TestInstance(Lifecycle.PER_CLASS)

public class TestController {

	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@BeforeAll
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	public static List<UserModel> userDetails() {
		UserModel user1 = new UserModel();
		user1.setUserId(10);
		user1.setFirstName("Neoo");
		user1.setLastName("Softt");
		user1.setCity("Bhilwara");
		user1.setDeleted(false);
		user1.setCountry("India");
		user1.setGender("Feamle");
		user1.setPinCode("121");

		UserModel user2 = new UserModel();
		user2.setUserId(10);
		user2.setFirstName("Neoo");
		user2.setLastName("Softt");
		user2.setCity("Bhilwara");
		user2.setDeleted(false);
		user2.setCountry("India");
		user2.setGender("Feamle");
		user2.setPinCode("123");

		List<UserModel> users = new ArrayList<UserModel>();
		users.add(user1);
		users.add(user2);
		return users;
	}

	@Test
	public void addUserTest() throws Exception {
		UserModel user = new UserModel();
		user.setFirstName("ANushre");
		user.setLastName("agarwal");
		user.setCity("Bhilwara");
		user.setCountry("India");
		user.setGender("Female");
		String jsonRequest = objectMapper.writeValueAsString(user);
		MvcResult result = mockMvc.perform(post("/users").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(resultContent, Response.class);
		org.junit.Assert.assertTrue(response.getStatus() == Boolean.TRUE);

	}

	@Test
	public void getAllUsersTest() throws Exception {
		List<UserModel> listUsers = TestController.userDetails();
		String response = objectMapper.writeValueAsString(listUsers);
		Mockito.when(userService.getAllUsers()).thenReturn(listUsers);
		MvcResult result = mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		// Response response1 = objectMapper.readValue(resultContent, Response.class);
		// assertTrue(response1.getStatus()==Boolean.TRUE);
		System.out.println(userService.getAllUsers().size());
		assertEquals(response, resultContent);
	}

	@Test
	public void deleteUserTest() throws Exception {
		MvcResult result = mockMvc.perform(delete("/users/706").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		int resultContent = result.getResponse().getStatus();
		System.out.println(">>>" + result.getResponse().getContentAsString());
		assertEquals(200, resultContent);

	}

	@Test
	public void findByFirstName() throws Exception {
		UserModel user1 = new UserModel();
		user1.setUserId(10);
		user1.setFirstName("Neoo");
		user1.setLastName("Softt");
		user1.setCity("Bhilwara");
		user1.setDeleted(false);
		user1.setCountry("India");
		user1.setGender("Feamle");
		user1.setPinCode("121");
		List<UserModel> users = new ArrayList<UserModel>();
		users.add(user1);
		String response = objectMapper.writeValueAsString(users);
		Mockito.when(userService.getUserByFirstName("Neoo")).thenReturn(users);
		MvcResult result = mockMvc
				.perform(get("/users/searchByFirstName/Neoo").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println("hello" + response);
		assertEquals(200, result.getResponse().getStatus());

	}

	@Test
	public void findByLastName() throws Exception {
		UserModel user1 = new UserModel();
		user1.setUserId(10);
		user1.setFirstName("Neoo");
		user1.setLastName("Softt");
		user1.setCity("Bhilwara");
		user1.setDeleted(false);
		user1.setCountry("India");
		user1.setGender("Feamle");
		user1.setPinCode("121");
		List<UserModel> users = new ArrayList<UserModel>();
		users.add(user1);
		String response = objectMapper.writeValueAsString(users);
		Mockito.when(userService.getUserByLastName("Softt")).thenReturn(users);
		MvcResult result = mockMvc
				.perform(get("/users/searchBySurName/Softt").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}

	@Test
	public void findByPinCode() throws Exception {
		UserModel user1 = new UserModel();
		user1.setUserId(10);
		user1.setFirstName("Neoo");
		user1.setLastName("Softt");
		user1.setCity("Bhilwara");
		user1.setDeleted(false);
		user1.setCountry("India");
		user1.setGender("Feamle");
		user1.setPinCode("121");
		List<UserModel> users = new ArrayList<UserModel>();
		users.add(user1);
		String response = objectMapper.writeValueAsString(users);
		Mockito.when(userService.getUserByPinCode("121")).thenReturn(users);
		MvcResult result = mockMvc
				.perform(get("/users/searchByPinCode/121").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println("hello" + response);
		assertEquals(200, result.getResponse().getStatus());

	}
	
	@Test
	public void sortByDateOfBirthTest() throws Exception {
		UserModel user1 = new UserModel();
		user1.setUserId(10);
		user1.setFirstName("Neoo");
		user1.setLastName("Softt");
		user1.setCity("Bhilwara");
		user1.setDeleted(false);
		user1.setCountry("India");
		user1.setGender("Feamle");
		user1.setPinCode("121");
		user1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").parse("2021-04-28 00:00:00"));
		user1.setDateOfJoining(new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").parse("2021-04-28 00:00:00"));
		List<UserModel> users = new ArrayList<UserModel>();
		users.add(user1);
		String response = objectMapper.writeValueAsString(users);
		Mockito.when(userService.getUserByLastName("Softt")).thenReturn(users);
		MvcResult result = mockMvc
				.perform(get("/users/searchBySurName/Softt").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}

}
