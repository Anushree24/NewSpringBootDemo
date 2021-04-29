package com.POC.demoProject.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Anushree
 * This class works as a controller for application.
 * when any request gets hit it goes into the specific route from where service logic gets called
 */
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

	@RequestMapping("/users/sortByDateOfBirth")
	public List<UserModel> sortUserByDateOfBirth() {
		return userService.sortUserByDateOfBirth();
	}

	@RequestMapping("/users/sortByDateOfJoining")
	public List<UserModel> sortUserByDateOfJoining() {
		return userService.sortUserByDateOfJoining();
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
		return new Response(userModel.getUserId() + "inserted", Boolean.TRUE);
	}

	@PutMapping(value = "/users/{id}")
	public Response updateUser(@RequestBody UserModel user, @PathVariable int id) {
		userService.updateUser(id, user);
		return new Response(user.getUserId() + " is updated", Boolean.TRUE);
	}

	@DeleteMapping(value = "/users/{id}")
	public Response deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return new Response(id + " is deleted", Boolean.TRUE);
	}
}
