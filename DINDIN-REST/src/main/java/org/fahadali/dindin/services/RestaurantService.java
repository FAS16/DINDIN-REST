package org.fahadali.dindin.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import org.fahadali.dindin.database.DatabaseClass;
import org.fahadali.dindin.database.RestaurantDAOImp;
import org.fahadali.dindin.exception.DataNotFoundException;
import org.fahadali.dindin.model.Budget;
import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;

//Denne klasse (service) skal forbinde til databasen og hente alle restauranter

public class RestaurantService {

	private RestaurantDAOImp restaurantDAO;
	private ArrayList<Restaurant> restaurants;

	public RestaurantService() {

		restaurantDAO = new RestaurantDAOImp();
		restaurants = getAllRestaurants();

	}

	public ArrayList<Restaurant> getAllRestaurants() {
		ArrayList<Restaurant> restaurants = null;
		try {
			restaurants = restaurantDAO.selectAllRestaurants();
			for (Restaurant r : restaurants)
				r.setLikers(getAllLikers(r.getId()));
			return restaurants;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Restaurant> getRestaurantsByZipcode(List<Integer> list) {
		ArrayList<Restaurant> reqRestaurants = new ArrayList<>();

		for (int i = 0; i < restaurants.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (this.restaurants.get(i).getZipcode() == list.get(j)) {
					reqRestaurants.add(this.restaurants.get(i));

				}
			}

		}
		return reqRestaurants;
	}

	public List<Restaurant> getRestaurantsByZipcodeAndCuisines(List<Integer> zipcodes, List<String> cuisines) {
		ArrayList<Restaurant> reqRestaurants = new ArrayList<>();

		for (int i = 0; i < restaurants.size(); i++) {
			for (int j = 0; j < zipcodes.size(); j++) {
				if (this.restaurants.get(i).getZipcode() == zipcodes.get(j)) {
					reqRestaurants.add(this.restaurants.get(i));
				}
			}
		}

		for (int i = 0; i < restaurants.size(); i++) {
			for (int j = 0; j < cuisines.size(); j++) {
				if (this.restaurants.get(i).getCuisine().equals(cuisines.get(j))) {
					if (!reqRestaurants.contains(this.restaurants.get(i))) {
						reqRestaurants.add(this.restaurants.get(i));
					}
				}
			}
		}

		return reqRestaurants;
	}

	public List<Restaurant> getRestaurantsByQuery(List<Integer> zipcodes, List<String> cuisines, List<String> budget) {
		ArrayList<Restaurant> reqRestaurants = new ArrayList<>();

		if (!zipcodes.isEmpty()) {
			for (int i = 0; i < restaurants.size(); i++) {
				for (int j = 0; j < zipcodes.size(); j++) {
					if (this.restaurants.get(i).getZipcode() == zipcodes.get(j)) {
						reqRestaurants.add(this.restaurants.get(i));
					}
				}
			}
		}

		if (!cuisines.isEmpty()) {
			for (int l = 0; l < reqRestaurants.size(); l++) {
				for (int m = 0; m < cuisines.size(); m++) {

					if (reqRestaurants.get(l) != null) {
						if (!(cuisines.contains(reqRestaurants.get(l).getCuisine().toLowerCase()))) {

							reqRestaurants.set(l, null);
						}

					}
				}
			}
		}

		if (!budget.isEmpty()) {
			for (int n = 0; n < reqRestaurants.size(); n++) {

				for (int o = 0; o < budget.size(); o++) {
					if (reqRestaurants.get(n) != null) {
						if (!(budget.contains(reqRestaurants.get(n).getBudget().getValue().toLowerCase()))) {
							reqRestaurants.set(n, null);
						}

					}
				}
			}
		}

		System.out.println("FØR NULL FJERNES: " + reqRestaurants.toString());
		while (reqRestaurants.remove(null))
			;
		System.out.println("EFT NULL FJERNES: " + reqRestaurants.toString());
		return reqRestaurants;
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
			throw new DataNotFoundException("Restaurant  with id " + id + " not found");

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
		if (restaurant.getId() <= 0)
			return null;
		for (int i = 0; i < restaurants.size(); i++) {
			if (restaurants.get(i).getId() == restaurant.getId()) {
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
			if (restaurants.get(i).getId() == id) {
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

	public ArrayList<User> getAllLikers(long id) {
		try {
			return restaurantDAO.selectAllLikers(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void printArray(List<Restaurant> l) {
		System.out.println("____________RESTAURANTER________________");
		for (Restaurant r : l) {
			System.out.println();
		}

		System.out.println("________________________________________");

	}

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(null);

		System.out.println(a.size());
	}

}
