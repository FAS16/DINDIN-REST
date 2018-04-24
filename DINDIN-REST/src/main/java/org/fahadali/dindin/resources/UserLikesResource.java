package org.fahadali.dindin.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.services.UserService;

@Path("/likes")
@Produces(MediaType.APPLICATION_JSON) // returns json
@Consumes(MediaType.APPLICATION_JSON) // accepts json
public class UserLikesResource {

	private UserService userService = new UserService();

	@GET
	public List<Restaurant> getLikes(@PathParam("userId") int userId) {
		return userService.getLikedRestaurants(userId);

	}

}
