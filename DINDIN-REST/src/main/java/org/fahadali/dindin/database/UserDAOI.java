package org.fahadali.dindin.database;

import java.sql.SQLException;
import java.util.ArrayList;

import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;

public interface UserDAOI {

	ArrayList<User> selectAllUsers() throws SQLException;
	
	boolean checkIfUserExists(String username) throws SQLException;
	
	User selectUserById(int id) throws SQLException;
	
	User selectUserByUsername(String username) throws SQLException;

	void insertUser(User user) throws SQLException;

	void updateUser(User user) throws SQLException;
	
	void deleteUser(User user) throws SQLException;
	
	ArrayList<Restaurant> selectAllLikedRestaurants(long userId) throws SQLException;
	
	void insertLikedRestaurant(User user,Restaurant restaurants) throws SQLException;

}
