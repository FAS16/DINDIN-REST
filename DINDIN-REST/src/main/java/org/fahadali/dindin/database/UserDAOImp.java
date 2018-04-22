package org.fahadali.dindin.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.fahadali.dindin.model.Budget;
import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;

public class UserDAOImp implements UserDAOI {

	private Connector connector = new Connector();
	private PreparedStatement prep;

	@Override
	public ArrayList<User> selectAllUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		final String SELECT_ALL_USERS = "SELECT * FROM users;";
		ResultSet rs = connector.doQuery(SELECT_ALL_USERS);

		while (rs.next()) {

			users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
					rs.getString("first_name"), rs.getString("last_name"), rs.getString("created")));
		}
		System.out.println("DB: Retrieved users from database");
		rs.close();
		return users;

	}
	
	@Override
	public User selectUser(int id) throws SQLException {
		
		User user = null;
		final String SELECT_USER = "SELECT * FROM users WHERE id = ?;";
		prep = connector.getConnection().prepareStatement(SELECT_USER);
		prep.setLong(1, id);
		ResultSet rs = prep.executeQuery();

		
		while (rs.next()) {

			user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
					rs.getString("first_name"), rs.getString("last_name"), rs.getString("created"));
		}
		
		System.out.println("DB: Retrieved user with id "+ id +" from database");
		rs.close();
		
		
		return user;
	}

	@Override
	public void insertUser(User u) throws SQLException {
		final String INSERT_USER = "INSERT INTO users " + "(id, username, email, first_name, last_name, created) "
				+ "VALUES (?,?,?,?,?,?);";

		prep = connector.getConnection().prepareStatement(INSERT_USER);
		prep.setLong(1, u.getId());
		prep.setString(2, u.getUserName());
		prep.setString(3, u.getEmail());
		prep.setString(4, u.getFirstName());
		prep.setString(5, u.getLastName());
		prep.setString(6, u.getCreated());
		prep.executeUpdate();
		prep.close();
		System.out.println("DB: User inserted to database");

	}

	@Override
	public void updateUser(User u) throws SQLException {
		final String UPDATE_USER = "UPDATE users " + "SET username = ?, email = ?, first_name = ?, "
				+ "last_name = ? WHERE id = ?;";

		prep = connector.getConnection().prepareStatement(UPDATE_USER);
		prep.setString(1, u.getUserName());
		prep.setString(2, u.getEmail());
		prep.setString(3, u.getFirstName());
		prep.setString(4, u.getLastName());
		prep.setLong(5, u.getId());
		prep.executeUpdate();
		prep.close();
		System.out.println("DB: User updated in database");
	}

	@Override
	public ArrayList<Restaurant> selectAllLikedRestaurants(long userId) throws SQLException {
		ArrayList<Restaurant> restaurants = new ArrayList<>();
		final String SELECT_ALL_LIKED_RESTAURANTS = "SELECT restaurants.id, restaurants.name, restaurants.zipcode, "
				+ "restaurants.address, restaurants.cuisine, restaurants.budget, "
				+ "restaurants.created, restaurants.visits " + "FROM likes INNER JOIN restaurants "
				+ "ON restaurants.id = likes.restaurant_id WHERE likes.user_id = ?";

		prep = connector.getConnection().prepareStatement(SELECT_ALL_LIKED_RESTAURANTS);
		prep.setLong(1, userId);
		ResultSet rs = prep.executeQuery();

		while (rs.next()) {
			
			restaurants.add(new Restaurant(rs.getLong("id"), rs.getString("name"), rs.getInt("zipcode"),
					rs.getString("address"), rs.getString("cuisine"), Budget.valueOf(rs.getString("budget")), rs.getString("created"),
					rs.getInt("visits")));
		}
		System.out.println("DB:  Retrieved liked restaurants of user with id " +userId+ " from database");
		rs.close();
		prep.close();
		return restaurants;
	}

	@Override
	public void insertLikedRestaurant(User u, Restaurant r) throws SQLException {
		final String INSERT_LIKED_RESTAURANT = "INSERT INTO likes (user_id, restaurant_id) VALUES (?, ?);";

		prep = connector.getConnection().prepareStatement(INSERT_LIKED_RESTAURANT);
		prep.setLong(1, u.getId());
		prep.setLong(2, r.getId());
		prep.executeUpdate();
		prep.close();
		System.out.println("DB: New like inserted into database");
		
		
		
	}

	@Override
	public void deleteUser(User user) throws SQLException {
		
		final String DELETE_USER = "DELETE FROM users WHERE id= ?;";

		prep = connector.getConnection().prepareStatement(DELETE_USER);
		prep.setLong(1, user.getId());
		prep.executeUpdate();
		prep.close();
		System.out.println("DB: User with id "+user.getId()+" succesfully deleted in database");
		
		
	}


}
