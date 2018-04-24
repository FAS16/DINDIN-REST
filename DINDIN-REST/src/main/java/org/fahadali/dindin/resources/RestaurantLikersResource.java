package org.fahadali.dindin.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;
import org.fahadali.dindin.services.RestaurantService;
import org.fahadali.dindin.services.UserService;

@Path("/likers")
@Produces(MediaType.APPLICATION_JSON) //returns json
@Consumes(MediaType.APPLICATION_JSON) // accepts json
public class RestaurantLikersResource {

	private RestaurantService restaurantService = new RestaurantService();
	
	@GET
	public List<User> getLikers(@PathParam("restaurantId") int restaurantId){	
		return restaurantService.getAllLikers(restaurantId);
		
	}

	
	

}
