package com.pay.my.budy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="user")
public class User {
	

	public User() {}
	
	public User(String firstname, String name, LocalDate birthdate, String address, String email,
			String password, List<Relationships> friends, double moneyAvailable) {
		this.firstName = firstname;
		this.name = name;
		this.birthDate = birthdate;
		this.address = address;
		this.email = email;
		this.password = password;
		this.friends = friends;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	@Column(name="firstName")
	private String firstName;
	
	@NotBlank(message = "Name can't be blank")
	@Column(name="name")
	private String name;
	
	@Column(name="birthdate")
	private LocalDate birthDate;
	
	@Column(name="address")
	private String address;
	
	@Email(message = "invalid email")
	@NotBlank(message = "Email can't be blank")
	@Column(name="email")
	private String email;
	

	@Size(min = 2, max = 350, message = "Password must be between 2 and * caracteres")
	@Column(name="password")
	private String password;
	
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Relationships.class)
	@JoinColumn(name = "friend")
	private List<Relationships> friends = new ArrayList<Relationships>();

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFisrtName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<Relationships> getFriends() {
		return friends;
	}

	public void setFriends(List<Relationships> friends) {
		this.friends = friends;
	}

}
