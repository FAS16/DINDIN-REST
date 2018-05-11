package org.fahadali.dindin.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;

public class DBTest {

	public static void main(String[] args) {

		RestaurantDAOImp r = new RestaurantDAOImp();
		UserDAOImp u = new UserDAOImp();
		ArrayList<Restaurant> restaurants = null;
		ArrayList<User> users = null;
		ArrayList<User> likers = null;
		Restaurant rest = new Restaurant();
		rest.setId(2);

		try {
			restaurants = r.selectAllRestaurants();
			users = u.selectAllUsers();
			likers = r.selectAllLikers((int) rest.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("\n__________ALL RESTAURANTS________________\n");
		for (Restaurant re : restaurants) {

			System.out.println(re.getName());

		}

		System.out.println("\n__________ALL USERS________________\n");
		for (User us : users) {
			System.out.println(us.getFirstName());

		}

		System.out.println("\n____________LIKERS______________\n");
		for (User us : likers) {
			System.out.println(us.getFirstName());

		}

	}

}
