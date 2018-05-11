package org.fahadali.dindin.database;

import java.sql.SQLException;
import java.util.ArrayList;

import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;

public interface RestaurantDAOI {

	ArrayList<Restaurant> selectAllRestaurants() throws SQLException;

	Restaurant selectRestaurant(int id) throws SQLException;

	void insertRestaurant(Restaurant restaurant) throws SQLException;

	void updateRestaurant(Restaurant restaurant) throws SQLException;

	void deleteRestaurant(Restaurant restaurant) throws SQLException;

	ArrayList<User> selectAllLikers(long restaurantId) throws SQLException;

}
