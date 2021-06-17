package com.pay.my.budy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;
import com.sun.istack.NotNull;

@Component
@Entity
@Table(name="user")
public class User {
	

	public User() {}
	
	public User(String firstname, String name, LocalDate birthdate, String address, String email,
			String password, Set<Integer> friends, double moneyAvailable) {
		this.firstName = firstname;
		this.name = name;
		this.birthDate = birthdate;
		this.address = address;
		this.email = email;
		this.password = password;
		this.friends = friends;
		this.moneyAvailable = moneyAvailable;
	}
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idUser")
	private long id;

//	@NotNull
//	@NotBlank(message = "FirstName can't be blank")
//	@Size(min = 2, max = 20, message = "Firstname must be between 2 and 20 caracteres")
	@Column(name="firstName")
	private String firstName;
	
//	@NotNull
//	@NotBlank(message = "Name can't be blank")
//	@Size(min = 2, max = 20, message = "Firstname must be between 2 and 20 caracteres")
	@Column(name="name")
	private String name;
	
//	@NotBlank(message = "Name can't be blank")
	@Column(name="birthdate")
	private LocalDate birthDate;
	
	@Column(name="address")
	private String address;
	
	@NotNull
	@Email(message = "invalid email")
	@NotBlank(message = "Email can't be blank")
	@Column(name="email")
	private String email;
	
	@NotNull
//	@NotBlank(message = "Password can't be blank")
	@Size(min = 2, max = 350, message = "Password must be between 2 and * caracteres")
	@Column(name="password")
	private String password;
	
	@Column(name="moneyavailable")
	private double moneyAvailable;
	
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Friends.class)
	@JoinColumn(name = "idUser")
	private Set<Integer> friends = new HashSet<Integer>();

	
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

	public Set<Integer> getFriends() {
		return friends;
	}

	public void setFriends(Set<Integer> friends) {
		this.friends = friends;
	}

	public double getMoneyAvailable() {
		return moneyAvailable;
	}

	public void setMoneyAvailable(double moneyAvailable) {
		this.moneyAvailable = moneyAvailable;
	}


}
