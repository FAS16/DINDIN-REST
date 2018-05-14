package org.fahadali.dindin.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.fahadali.dindin.auth.Secured;
import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;
import org.fahadali.dindin.services.UserService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserLikesResource {

	private UserService userService;

	public UserLikesResource(UserService userService) {
		System.out.println("Constructor in UserLikesResource invoked");
		this.userService = userService;
	}

	@GET
	public List<Restaurant> getLikes(@PathParam("userId") int userId) {

		return userService.getLikedRestaurants(userId);
	}

	@PUT
	public Response addLike(@PathParam("userId") int userId, Restaurant restaurant) {
		System.out.println("REQUEST FOR ADD LIKE");
		User user = this.userService.likeRestaurant(userId, restaurant.getId());

		return Response.ok().entity(user).build();

	}

	@DELETE
	public Response deleteLike(@PathParam("userId") int userId, Restaurant restaurant) {
		System.out.println("REQUEST FOR DELETE LIKE");
		User user = this.userService.unLikeRestaurant(userId, restaurant.getId());

		return Response.ok().entity(user).build();
	}
}
