package org.fahadali.dindin.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Credentials implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1138996582111382336L;
	private String username;
	private String password;
	
	public Credentials() {
		
	}
	
	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
