package org.fahadali.dindin.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fahadali.dindin.model.User;
import org.fahadali.dindin.services.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON) //returns json
@Consumes(MediaType.APPLICATION_JSON) // accepts json
//De metoder der skriver/ændrer bruger consumes (fordi de tager i mod noget ny data)
public class UserResource {
	
	private UserService userService = new UserService();
	
	@GET
	public List<User> getUser() {
		return userService.getAllUsers();
	}
	
	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") long id) {
		return userService.getUser(id);
	}
	
	@POST
	public User addUser(User user) {
		return userService.addUser(user);
	}
	
	@PUT
	@Path("/{userId}")
	public User updateUser(@PathParam("userId") long id, User user) {
		user.setId(id);
		return userService.updateUser(user);
		
	}
	
	@DELETE
	@Path("/{userId}")
	public void deleteUser(@PathParam("userId") long id) {
		userService.removeUser(id);
	}

}
