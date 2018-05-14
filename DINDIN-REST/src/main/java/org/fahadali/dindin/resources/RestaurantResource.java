package org.fahadali.dindin.resources;

import java.net.URI;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.fahadali.dindin.auth.Secured;
import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.services.RestaurantService;

@Path("/restaurants") //endpoint
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)  
public class RestaurantResource {
	
	RestaurantService restaurantService = new RestaurantService();
	
	
	/*
	 * Med BeanParam
	 */
		@GET
		@Produces(MediaType.APPLICATION_JSON) 
		public List<Restaurant> getRestaurants(@BeanParam RestaurantFilterBean filterBean ) {
			if(filterBean.getZipcodes().size() > 0 || filterBean.getCuisines().size() > 0 || filterBean.getBudget().size() > 0) {
				System.out.println("QUERIES RECIEVED "+filterBean.getZipcodes().toString() +", " + filterBean.getCuisines().toString() + ", " + filterBean.getBudget().toString());
				
				List<Restaurant> requested = restaurantService.getRestaurantsByQuery(filterBean.getZipcodes(), filterBean.getCuisines(), filterBean.getBudget());
				if(requested.isEmpty()) throw new NotFoundException();
				System.out.println("SENDER FØLGENDE TIL KLIENTEN" + requested.toString());
				 return requested;
			}

			
			return restaurantService.getAllRestaurants();
		}

	
	@GET
	@Path("/{restaurantId}") 
	public Restaurant getRestaurant(@PathParam("restaurantId") long id) {
		return restaurantService.getRestaurant(id);
	}

	
	
	@POST
	@Secured
	public Response addRestaurant(Restaurant restaurant) { //svaret bliver konverteret til json
		restaurant.setCreated(new Date().toString());
		System.out.println("POST: " + restaurant.getName());
		Restaurant newRestaurant = restaurantService.addRestaurant(restaurant);
		String newId = String.valueOf(newRestaurant.getId());
		
		return Response.status(201)
		.entity(newRestaurant)
		.build();
	}
	
	@PUT
	@Secured
	@Path("/{restaurantId}")
	public Restaurant updateRestaurant(@PathParam("restaurantId") long id, Restaurant restaurant) {
		restaurant.setId(id); 
		return restaurantService.updateRestaurant(restaurant);
	
	}
	
	@DELETE
	@Secured
	@Path("/{restaurantId}")
	public void deleteRestaurant(@PathParam("restaurantId") long id) {
		restaurantService.removeRestaurant(id);
	}
	
	/*
	 * Sub-resource
	 */
	@Path("/{restaurantId}/likers")
	public RestaurantLikersResource getRestaurantLikersResource() { 
		return new RestaurantLikersResource();
		
	}

}
