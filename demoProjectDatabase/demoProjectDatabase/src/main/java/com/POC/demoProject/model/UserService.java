package com.POC.demoProject.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Anushree Agarwal This class is used for orm.This maps fields into
 *         databases and save result in database.
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserModel> getAllUsers() {
		// List<UserModel> users = new ArrayList<>();
		// userRepository.findAll().forEach(users::add);
		// this will sort users based on dob
		return userRepository.findAll(Sort.by(Sort.Direction.DESC, "dateOfBirth"));
		// return users;
	}

	public List<UserModel> getUserByDateOfBirth() {
		return (userRepository).findAllByOrderByDateOfBirthAsc();
	}

	public List<UserModel> getUserByDateOfJoining() {
		return (userRepository).findAllByOrderByDateOfJoiningAsc();
	}

	public List<UserModel> getUserByFirstName(String firstName) {
		return (userRepository).findTopByFirstName(firstName);
	}

	public List<UserModel> getUserByLastName(String lastName) {
		return (userRepository).findTopByLastName(lastName);
	}

	public List<UserModel> getUserByPinCode(String pinCode) {
		return (userRepository).findTopByPinCode(pinCode);
	}

	public void addUser(UserModel user) {
		userRepository.save(user);
	}

	public UserModel updateUser(int id, UserModel user) {
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			throw new ResourceNotFoundException(
					"The ID " + id + " is not found in the database.Please try with new  id");
		}

	}

	public void deleteUser(int id) {
		try {
			System.out.println("id is-->" + id);
			userRepository.deleteById(id);

		} catch (Exception e) {
			throw new ResourceNotFoundException(
					"The ID " + id + " is not found in the database.Please try with new  id");
		}

	}

}
