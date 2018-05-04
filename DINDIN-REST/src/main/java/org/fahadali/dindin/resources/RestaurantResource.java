package org.fahadali.dindin.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

import org.fahadali.dindin.FavoredResource;
import org.fahadali.dindin.auth.Secured;
import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.services.RestaurantService;

//XMLtransient angives på de felter, der ikke skal med når man søger på en path f.eks. /restaurants
@Path("/restaurants") //Definerer endpointet
@Consumes(MediaType.APPLICATION_JSON) // accepts json
@Produces(MediaType.APPLICATION_JSON) //returns/supports json & xml//De metoder der skriver/ændrer bruger consumes (fordi de tager i mod noget ny data) 
public class RestaurantResource {
	
	RestaurantService restaurantService = new RestaurantService();
	
	
//	@GET
//	public List<Restaurant> getRestaurants(	@QueryParam("zipcode") int zipcode, 
//											@QueryParam("start") int start, 
//											@QueryParam("size") int size) {
//		
//		//@QueryParam er til filtrering - f.eks.: /restaurants?zipcode=2300
//		if(zipcode > 0) {
//			return restaurantService.getRestaurantsByZipcode(zipcode);
//		}
//		
//		if(start >= 0 && size > 0) {
//			return restaurantService.getRestaurantsPaginated(start, size);
//		}
//		
//		
//		return restaurantService.getAllRestaurants();
//	}
	
	//BeanParam eksempel
		@GET
		@Produces(MediaType.APPLICATION_JSON) //returns/supports json & xml
		public List<Restaurant> getJsonRestaurants(@BeanParam RestaurantFilterBean filterBean ) {
			System.out.println("JSON methods called");
			//@QueryParam er til filtrering - f.eks.: /restaurants?zipcode=2300
		
			if(filterBean.getZipcodes().size() > 0 || filterBean.getCuisines().size() > 0 || filterBean.getBudget().size() > 0) {
				System.out.println("QUERIES RECIEVED "+filterBean.getZipcodes().toString() +", " + filterBean.getCuisines().toString() + ", " + filterBean.getBudget().toString());
				
				List<Restaurant> requested = restaurantService.getRestaurantsByQuery(filterBean.getZipcodes(), filterBean.getCuisines(), filterBean.getBudget());
				if(requested.isEmpty()) throw new NotFoundException();
				System.out.println("SENDER FØLGENDE TIL KLIENTEN" + requested.toString());
				 return requested;
			}

			
			return restaurantService.getAllRestaurants();
		}
		
//		@GET
//		@Produces(MediaType.TEXT_XML) //returns/supports json & xml
//		public List<Restaurant> getXmlRestaurants(@BeanParam RestaurantFilterBean filterBean ) {
//			System.out.println("XML methods called");
//			
//			if(filterBean.getZipcode() > 0) {
//				return restaurantService.getRestaurantsByZipcode(filterBean.getZipcode());
//			}
//			
//			if(filterBean.getStart() >= 0 && filterBean.getSize()> 0) {
//				return restaurantService.getRestaurantsPaginated(filterBean.getStart(), filterBean.getSize());
//			}
//			
//			
//			return restaurantService.getAllRestaurants();
//		}
//		
//		
	
	@GET
	@Path("/{restaurantId}") //Denne del af URLen er variabel
	public Restaurant getRestaurant(@PathParam("restaurantId") long id) {
		return restaurantService.getRestaurant(id);
	}

	
//	@POST
//	public Restaurant addRestaurant(Restaurant restaurant) { //svaret bliver konverteret til json
//		return restaurantService.addRestaurant(restaurant);
//	}
	
	
	
	/*
	 * Response Builder - bruges til fx. at modificere/ændre HTTP-status kode (CREATED = 201), og header,
	 * samtidig med at at lave en ny restaurant og retunere den.
	 * 
	 * Sætte header value - kan gøres med Response.header, eller endnu bedre
	 * med Response.created, som både sender status kode 201 og Location header value (URIen til den nye restaurant)
	 */
	@POST
	public Response addRestaurant(Restaurant restaurant, @Context UriInfo uriInfo) { //svaret bliver konverteret til json
		
		Restaurant newRestaurant = restaurantService.addRestaurant(restaurant);
		String newId = String.valueOf(newRestaurant.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
		.entity(newRestaurant)
		.build();
	}
	
	@PUT
	@Path("/{restaurantId}")
	public Restaurant updateRestaurant(@PathParam("restaurantId") long id, Restaurant restaurant) {
		restaurant.setId(id); //Id gives ikke med i put-metodens body (json-objektet), men hentes fra URLen
		return restaurantService.updateRestaurant(restaurant);
	
	}
	
	@DELETE
	@Path("/{restaurantId}")
	@Secured
	public void deleteRestaurant(@PathParam("restaurantId") long id) {
		restaurantService.removeRestaurant(id);
	}
	
	@Path("/{restaurantId}/likers")
	public RestaurantLikersResource getRestaurantLikersResource() {//JAX-RS ser at returrypen er en anden sub-resource, og vil derfor bruge den til at besvare forespørgsler
		return new RestaurantLikersResource();
		
	}

}
