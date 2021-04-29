package com.POC.demoProject.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/users")
	public List<UserModel> getAllUsers() {
		return userService.getAllUsers();
	}
	@RequestMapping("/users/findByDateOfBirth")
	public List<UserModel> getUserByDateOfBirth() {
		return userService.getUserByDateOfBirth();
	}
	
	@RequestMapping("/users/findByDateOfJoining")
	public List<UserModel> getUserByDateOfJoining() {
		return userService.getUserByDateOfJoining();
	}

	@RequestMapping("/users/searchByFirstName/{firstName}")
	public List<UserModel> getUserByFirstName(@PathVariable String firstName) {
		return userService.getUserByFirstName(firstName);
	}
	@RequestMapping("/users/searchBySurName/{lastName}")
	public List<UserModel> getUserByLastName(@PathVariable String lastName) {
		return userService.getUserByLastName(lastName);
	}
	@RequestMapping("/users/searchByPinCode/{pinCode}")
	public List<UserModel> getUserByPinCode(@PathVariable String pinCode) {
		return userService.getUserByPinCode(pinCode);
	}


	@PostMapping(value = "/users", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Response addUser(@RequestBody UserModel userModel) {
		userRepository.save(userModel);
		return new Response(userModel.getUserId()+"inserted", Boolean.TRUE);
		//userService.addUser(userModel);
	}

	@PutMapping(value = "/users/{id}")
	public void updateUser(@RequestBody UserModel user, @PathVariable int id) {
		userService.updateUser(id, user);
	}

	@DeleteMapping(value = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
}
