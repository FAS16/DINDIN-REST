package org.fahadali.dindin.resources;

import java.util.List;

import javax.ws.rs.QueryParam;


/*
 * Bruger BeanParam
 * 
 */

public class RestaurantFilterBean {
	
	private @QueryParam("zipcode") List<Integer> zipcodes;
	private @QueryParam("cuisine") List<String> cuisines;
	private @QueryParam("budget") List<String> budget;
	private @QueryParam("start") int start;
	private @QueryParam("size") int size;
	
	public List<Integer> getZipcodes() {
		return zipcodes;
	}
	public void setZipcode(List<Integer> zipcodes) {
		this.zipcodes = zipcodes;
	}
	
	public List<String> getCuisines() {
		return cuisines;
	}
	public void setCuisines(List<String> cuisines) {
		this.cuisines = cuisines;
	}
	public List<String> getBudget() {
		return budget;
	}
	public void setBudget(List<String> budget) {
		this.budget = budget;
	}
	public void setZipcodes(List<Integer> zipcodes) {
		this.zipcodes = zipcodes;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	
	
	

}
