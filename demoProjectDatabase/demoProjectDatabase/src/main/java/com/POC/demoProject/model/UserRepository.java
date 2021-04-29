package com.POC.demoProject.model;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anushree Agarwal
 * This interface acts as repository and contains various methods which we can use for searching and sorting in JPA.
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	Page<UserModel> findByUserId(int id, Pageable pageable);
	//This will search result based on all 3 params and return the first result in case of more than 1 result.
	List<UserModel> findTopByFirstName(String firstName);
	List<UserModel> findTopByLastName(String lastName);
	List<UserModel> findTopByPinCode(String pinCode);
	List<UserModel> findAllByOrderByDateOfBirthAsc();
	List<UserModel> findAllByOrderByDateOfJoiningAsc();

}
