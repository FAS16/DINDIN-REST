package org.fahadali.dindin.resources;

import javax.ws.rs.QueryParam;


/*
 * Eksempel på hvordan man bruger BeanParam
 * 
 */

public class RestaurantFilterBean {
	
	private @QueryParam("zipcode") int zipcode; 
	private @QueryParam("start") int start;
	private @QueryParam("size") int size;
	
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
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
