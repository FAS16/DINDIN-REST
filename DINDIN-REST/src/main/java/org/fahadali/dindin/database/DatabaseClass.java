package org.fahadali.dindin.database;

import java.util.HashMap;
import java.util.Map;

import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;

/*
 * Database mockup - skal erstattes med en rigtig database 
 */

public class DatabaseClass {

	private static Map<Long, Restaurant> restaurants = new HashMap<>();
	private static Map<Long, User> users = new HashMap<>();

	public static Map<Long, Restaurant> getRestaurants() {
		return restaurants;
	}

	public static Map<Long, User> getUsers() {
		return users;
	}

}
