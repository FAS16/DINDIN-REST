package org.fahadali.dindin.services;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.fahadali.dindin.database.DatabaseClass;
import org.fahadali.dindin.exception.DataNotFoundException;
import org.fahadali.dindin.model.Budget;
import org.fahadali.dindin.model.Restaurant;

//Denne klasse (service) skal forbinde til databasen og hente alle restauranter

public class RestaurantService {

	private Map<Long, Restaurant> restaurants = DatabaseClass.getRestaurants(); // Dette skal reelt set ske til
																				// databasen, og ikke til mockup-klassen

	public RestaurantService() {

		restaurants.put(1L, new Restaurant(1, "Silas kebab",2300, "København", Budget.LOW, "Tyrkisk"));
		restaurants.put(2L, new Restaurant(2, "Noma",1432, "København",Budget.HIGH, "Gourmet"));
		restaurants.put(3L, new Restaurant(3, "Amager kebab",2300, "København", Budget.LOW, "Tyrkisk"));

	}

	public List<Restaurant> getAllRestaurants() {
		return new ArrayList<Restaurant>(restaurants.values()); // Passing til en ArrayList
	}

	public List<Restaurant> getRestaurantsByZipcode(int zipcode) {
		List<Restaurant> restaurantsByZipcode = new ArrayList<>();

		for (Restaurant r : restaurants.values()) {
			if (r.getZipcode() == zipcode) {
				restaurantsByZipcode.add(r);
			}
		}
		return restaurantsByZipcode;

	}

	public List<Restaurant> getRestaurantsPaginated(int start, int size) {
		ArrayList<Restaurant> list = new ArrayList<Restaurant>(restaurants.values());
		if (start + size > list.size())
			return new ArrayList<Restaurant>(); // returnerer tom list, hvis ingen resultater
		return list.subList(start, start + size);

	}

	public Restaurant getRestaurant(long id) {
		Restaurant restaurant = restaurants.get(id);
			if(restaurant == null) {
				throw new DataNotFoundException("Restaurant with id "+ id + " not found" );
				
			}
			return restaurant;
		 
	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurant.setId(restaurants.size() + 1);
		restaurants.put(restaurant.getId(), restaurant);
		return restaurant;
	}

	public Restaurant updateRestaurant(Restaurant restaurant) {
		if (restaurant.getId() <= 0) {
			return null;
		}
		restaurants.put(restaurant.getId(), restaurant);
		return restaurant;
	}

	public Restaurant removeRestaurant(long id) {
		return restaurants.remove(id);
	}

}
