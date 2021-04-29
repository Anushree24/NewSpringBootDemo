package com.POC.demoProject.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.SQLDelete;

/**
 * @author Anushree This class is model class for user. It takes all the
 *         parameters along with certain validations that user fields will have.
 *  This class will acts as entity.
 */
@Entity(name = "Usertable")

//this is just used for soft delete
//@SQLDelete(sql = "UPDATE Usertable SET deleted=true WHERE user_id=?")
public class UserModel implements Serializable {

	@Id
	private int userId;

	private boolean deleted;

	@NotNull
	@Size(max = 60, min = 4)
	private String firstName;

	@NotNull
	@Size(max = 60, min = 4)
	private String lastName;

	@NotNull
	@Email
	@Column(unique = true)
	private String email;

	@NotNull
	@Size(max = 15)
	private String password;

	@Size(max = 15, min = 6)
	private String phoneNumber;

	private String gender;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Temporal(TemporalType.DATE)
	private Date dateOfJoining;

	@Size(max = 50)
	private String streetAddress;

	@Size(max = 50)
	private String city;

	@Size(max = 50)
	private String state;

	@Size(max = 50)
	private String country;

	@Size(max = 50)
	private String pinCode;

	// This is the default constructor
	public UserModel() {

	}

	public UserModel(int userId, String firstName, String lastName, String email, String password, String phoneNumber,
			String gender, Date dateOfBirth, Date dateOfJoining, String streetAddress, String city, String state,
			String country, String pinCode, Boolean deleted) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.deleted = deleted;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
