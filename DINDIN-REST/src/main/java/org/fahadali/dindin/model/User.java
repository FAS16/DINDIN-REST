package org.fahadali.dindin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private long id;
	private String username;	
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private List<Restaurant> likedRestaurants;
	private String created;

	public User() {

	}

	public User(long id, String userame, String email, String firstName, String lastName, String created) {
		super();
		this.id = id;
		this.username = userame;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.likedRestaurants = new ArrayList<>();
		this.created = created;
	}
	
	//New user constructor
	public User(String username, String email, String firstName, String lastName) {
		super();
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.likedRestaurants = new ArrayList<>();
		this.created = new Date().toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Restaurant> getLikedRestaurants() {
		return likedRestaurants;
	}

	public void setLikedRestaurants(List<Restaurant> likedRestaurants) {
		this.likedRestaurants = likedRestaurants;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}


}
