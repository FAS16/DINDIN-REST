package org.fahadali.dindin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Restaurant {

	private long id;
	private String name;
	private int zipcode;
	private String address;
	private Budget budget;
	private String cuisine;
	private List<User> likers;
	private int visits;
	private String created;


	public Restaurant() {

	}

	public Restaurant(long id, String name, int zipcode, String address, String cuisine, Budget budget, String created, int visits) {
		super();
		this.id = id;
		this.name = name;
		this.zipcode = zipcode;
		this.address = address;
		this.budget = budget;
		this.cuisine = cuisine;
		this.likers = new ArrayList<>();
		this.visits = visits;
		this.created = new Date().toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCuisine() {
		return cuisine;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	

	public List<User> getLikers() {
		return likers;
	}

	public void setLikers(List<User> likers) {
		this.likers = likers;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created.toString();
	}

}
