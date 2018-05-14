package org.fahadali.dindin.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.fahadali.dindin.model.Budget;
import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;

public class RestaurantDAOImp implements RestaurantDAOI {

	private Connector connector = new Connector();
	private PreparedStatement prep;

	@Override
	public ArrayList<Restaurant> selectAllRestaurants() throws SQLException {
		ArrayList<Restaurant> restaurants = new ArrayList<>();
		final String SELECT_ALL_RESTAURANTS = "SELECT * FROM restaurants;";
		ResultSet rs = connector.doQuery(SELECT_ALL_RESTAURANTS);

		while (rs.next()) {

			restaurants.add(new Restaurant(rs.getInt("id"), rs.getString("name"), rs.getInt("zipcode"),
					rs.getString("address"), rs.getString("cuisine"), Budget.valueOf(rs.getString("budget")), rs.getString("created"),
					rs.getInt("visits"), rs.getString("phone"), rs.getString("website"), rs.getString("instagram"), rs.getString("description")));
		}
		System.out.println("DB: Retrieved restaurants from database");
		rs.close();
		return restaurants;
	}

	@Override
	public Restaurant selectRestaurant(int id) throws SQLException {

		Restaurant restaurant = null;
		final String SELECT_USER = "SELECT * FROM restaurants WHERE id = ?;";
		prep = connector.getConnection().prepareStatement(SELECT_USER);
		prep.setLong(1, id);
		ResultSet rs = prep.executeQuery();

		while (rs.next()) {

			restaurant = new Restaurant(rs.getInt("id"), rs.getString("name"), rs.getInt("zipcode"),
					rs.getString("address"), rs.getString("cuisine"), Budget.valueOf(rs.getString("budget")), rs.getString("created"),
					rs.getInt("visits"), rs.getString("phone"), rs.getString("website"), rs.getString("instagram"), rs.getString("description"));
		}

		System.out.println("DB: Retrieved restaurant with id " + id + " from database");
		rs.close();

		return restaurant;
	}

	@Override
	public void insertRestaurant(Restaurant restaurant) throws SQLException {
		final String INSERT_USER = "INSERT INTO restaurants "
				+ "(name, zipcode, address, cuisine, budget, created, visits, phone, website) " + "VALUES (?,?,?,?,?,?,?,?,?);";

		prep = connector.getConnection().prepareStatement(INSERT_USER);
		prep.setString(1, restaurant.getName());
		prep.setInt(2, restaurant.getZipcode());
		prep.setString(3, restaurant.getAddress());
		prep.setString(4, restaurant.getCuisine());
		prep.setString(5, restaurant.getBudget().getValue());
		prep.setString(6, restaurant.getCreated());
		prep.setInt(7, restaurant.getVisits());
		prep.setString(8, restaurant.getPhone());
		prep.setString(9, restaurant.getWebsite());
		prep.executeUpdate();
		prep.close();
		System.out.println("DB: Restaurant inserted to database");

	}

	@Override
	public void updateRestaurant(Restaurant restaurant) throws SQLException {
		final String UPDATE_RESTAURANT = "UPDATE restaurants " + "SET name = ?, zipcode = ?, address = ?, "
				+ "cuisine = ?, budget = ?, created = ?, visits = ?, phone = ?, website = ? WHERE id = ?;";

		prep = connector.getConnection().prepareStatement(UPDATE_RESTAURANT);
		prep.setString(1, restaurant.getName());
		prep.setInt(2, restaurant.getZipcode());
		prep.setString(3, restaurant.getAddress());
		prep.setString(4, restaurant.getCuisine());
		prep.setString(5, restaurant.getBudget().getValue());
		prep.setString(6, restaurant.getCreated());
		prep.setInt(7, restaurant.getVisits());
		prep.setString(8, restaurant.getPhone());
		prep.setString(9, restaurant.getWebsite());
		prep.setLong(10, restaurant.getId());
		prep.executeUpdate();
		prep.close();
		System.out.println("DB: Restaurant updated in database");
	}

	@Override
	public ArrayList<User> selectAllLikers(long restaurantId) throws SQLException {

		ArrayList<User> likers = new ArrayList<>();
		final String SELECT_ALL_LIKERS = "SELECT users.id, users.username, users.email, users.first_name, users.last_name, users.created "
				+ "FROM likes INNER JOIN users ON users.id = likes.user_id " + "WHERE likes.restaurant_id = ?";

		prep = connector.getConnection().prepareStatement(SELECT_ALL_LIKERS);
		prep.setLong(1, restaurantId);
		ResultSet rs = prep.executeQuery();

		while (rs.next()) {

			likers.add(new User(rs.getLong("id"), rs.getString("username"), rs.getString("email"),
					rs.getString("first_name"), rs.getString("last_name"), rs.getString("created")));
		}
		System.out.println("DB: Retrieved likers of restaurant with id " + restaurantId + " from database");
		rs.close();
		prep.close();
		return likers;

	}

	@Override
	public void deleteRestaurant(Restaurant restaurant) throws SQLException {
		
		final String DELETE_RESTAURANT = "DELETE FROM restaurants WHERE id= ?;";

		prep = connector.getConnection().prepareStatement(DELETE_RESTAURANT);
		prep.setLong(1, restaurant.getId());
		prep.executeUpdate();
		prep.close();
		System.out.println("DB: Restaurant with id "+restaurant.getId()+" succesfully deleted in database");
		
		
	}

}
