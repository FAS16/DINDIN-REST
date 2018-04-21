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
	private Date created;

	public Restaurant() {

	}

	public Restaurant(long id, String name, int zipcode, String address, Budget budget, String cuisine) {
		super();
		this.id = id;
		this.name = name;
		this.zipcode = zipcode;
		this.address = address;
		this.budget = budget;
		this.cuisine = cuisine;
		this.likers = new ArrayList<>();
		this.created = new Date();
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
