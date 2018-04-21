package org.fahadali.dindin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private long id;
	private String userName;	
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private List<Restaurant> likedRestaurants;
	private Date created;

	public User() {

	}

	public User(long id, String userName, String email, String firstName, String lastName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.likedRestaurants = new ArrayList<>();
		this.created = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
