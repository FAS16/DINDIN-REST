package org.fahadali.dindin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*
 * Restauranternes likes
 * Overvej om de bestemte brugere også skal mappes til likes
 */

@Path("/") //Valgfrit
public class FavoredResource {
	
	@GET
	public String test() {
		return "test in subresource!";
	}
	
	//http://localhost:8080/dindin/webapi/restaurants/2/comments/2
	@GET
	@Path("/{likes}")
	public String test2() {
		return "Method to return comment ID"; 
	}
	

}
