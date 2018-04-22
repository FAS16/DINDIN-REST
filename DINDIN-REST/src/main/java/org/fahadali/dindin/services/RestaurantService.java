package org.fahadali.dindin.services;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.fahadali.dindin.database.DatabaseClass;
import org.fahadali.dindin.database.RestaurantDAOImp;
import org.fahadali.dindin.exception.DataNotFoundException;
import org.fahadali.dindin.model.Budget;
import org.fahadali.dindin.model.Restaurant;

//Denne klasse (service) skal forbinde til databasen og hente alle restauranter

public class RestaurantService {

	private RestaurantDAOImp restaurantDAO;
	private ArrayList<Restaurant> restaurants;

	public RestaurantService() {

		restaurantDAO = new RestaurantDAOImp();
		restaurants = getAllRestaurants();

	}

	public ArrayList<Restaurant> getAllRestaurants() {
		try {
			return restaurantDAO.selectAllRestaurants();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Restaurant> getRestaurantsByZipcode(int zipcode) {
		List<Restaurant> restaurantsByZipcode = new ArrayList<>();

		for (Restaurant r : restaurants) {
			if (r.getZipcode() == zipcode) {
				restaurantsByZipcode.add(r);
			}
		}
		return restaurantsByZipcode;

	}

	public List<Restaurant> getRestaurantsPaginated(int start, int size) {
		if (start + size > restaurants.size())
			return new ArrayList<Restaurant>(); // returnerer tom list, hvis ingen resultater
		return restaurants.subList(start, start + size);

	}

	public Restaurant getRestaurant(long id) {
		Restaurant restaurant = null;
		for (Restaurant r : restaurants) {
			if (r.getId() == id)
				restaurant = r;
		}

		if (restaurant == null) {
			throw new DataNotFoundException("Restaurant with id " + id + " not found");

		}
		return restaurant;

	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		restaurant.setId(restaurants.size() + 1);
		restaurants.add(restaurant);
		try {
			restaurantDAO.insertRestaurant(restaurant);
			this.restaurants = getAllRestaurants();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}

	public Restaurant updateRestaurant(Restaurant restaurant) {
		if (restaurant.getId() <= 0) return null;
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getId() == restaurant.getId()) {
				restaurants.set(i, restaurant);
				break;
			}
		}
		try {
			restaurantDAO.updateRestaurant(restaurant);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.restaurants = getAllRestaurants();
		return restaurant;
	}

	public Restaurant removeRestaurant(long id) {
		Restaurant toBeRemoved = null;
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getId() == id) {
				toBeRemoved = restaurants.get(i);
				restaurants.remove(i);
			}
			try {
				restaurantDAO.deleteRestaurant(toBeRemoved);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return toBeRemoved;
	}

}
