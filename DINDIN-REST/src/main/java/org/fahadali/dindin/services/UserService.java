package org.fahadali.dindin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fahadali.dindin.database.DatabaseClass;
import org.fahadali.dindin.model.ErrorMessage;
import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;

public class UserService {

private Map<Long, User> users = DatabaseClass.getUsers(); // Dette skal reelt set ske til databasen, og ikke til mockup-klassen
	
	
	
	public UserService() {
		
		users.put(1L, new User(1, "s160344", "s160344@student.dtu.dk","Fahad", "Ali", "Linux123"));
		users.put(2L, new User(2, "s123456", "s123456@student.dtu.dk", "Muhammad", "Ali", "Linux123"));
	
	}

	public List<User> getAllUsers() {
		return new ArrayList<User>(users.values());	//Passing til en ArrayList	
	}
	
	/*
	 * Eksempel på WebApplicationException, der ikke behøves at mappe
	 * Kan sende Status.x, response.x osv.
	 */
	public User getUser(long id) {
		User user = users.get(id);
		if (user == null){ 
			//Lav respons
			ErrorMessage e = new ErrorMessage("Not found", 404);
			Response r = Response.status(Status.NOT_FOUND)
					.entity(e)
					.build();
			
			//Kast respons
			throw new WebApplicationException(r); //kunne også kaste NotFoundException(r); så behøvede man ikke at definerer status i ovenstående respons - se javadoc for WebApEx 
			
		}
		return user;
	}
	
	public User addUser(User user) {
		user.setId(users.size() + 1);
		users.put(user.getId(), user);
		return user;
	}
	
	public User updateUser(User user) {
		if (user.getId() <= 0) {
			return null;
		}
		users.put(user.getId(), user);
		return user;
	}
	
	public User removeUser(long id) {
		return users.remove(id);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
