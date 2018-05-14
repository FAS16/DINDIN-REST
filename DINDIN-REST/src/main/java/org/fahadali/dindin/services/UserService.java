package org.fahadali.dindin.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.fahadali.dindin.database.UserDAOImp;
import org.fahadali.dindin.model.ErrorMessage;
import org.fahadali.dindin.model.Restaurant;
import org.fahadali.dindin.model.User;

public class UserService {

	private UserDAOImp userDAO;
	private ArrayList<User> users;

	public UserService() {

		userDAO = new UserDAOImp();
		this.users = getAllUsers();
		
		// users.put( 1L, new User(1, "Fahadbrugernav", "fahad@mail.dk", "Fahad",
		// "Sajad", "i dag"));
		// users.put( 2L, new User(2, "ANSO", "fahad@mail.dk", "Fahad", "Sajad", "i
		// dag"));

	}

	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = null;
		try {
			users =  userDAO.selectAllUsers();
			for (User u : users) {
				u.setLikedRestaurants(getLikedRestaurants(u.getId()));
				System.out.println(u.getLikedRestaurants());
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Eksempel på WebApplicationException, der ikke behøves at mappe Kan sende
	 * Status.x, response.x osv.
	 */
	public boolean checkIfUserIsInDatabase(String username) {
		boolean existing = false;
		try {
			existing = userDAO.checkIfUserExists(username);
			if (existing) {
				System.out.println("DB: User with username " + username + " does exist in database");
			} else {
				System.out.println("DB: User with username " + username + " does NOT exist in database");
			}	

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		return existing;
	}
	
	public User getUserByUsername(String username) {
		User user = null;
		try {
			user = userDAO.selectUserByUsername(username);
			user.setLikedRestaurants(this.getLikedRestaurants(user.getId()));
			if (user == null) {
				// Lav respons
				ErrorMessage e = new ErrorMessage("Not found", 404);
				Response r = Response.status(Status.NOT_FOUND).entity(e).build();

				// Kast respons
				throw new WebApplicationException(r); // kunne også kaste NotFoundException(r); så behøvede man ikke at
														// definerer status i ovenstående respons - se javadoc for
														// WebApEx
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		System.out.println("Got specific user: " + user.getId() + ", " + user.getFirstName());

		return user;
	}

	public User getUserById(int id) {
		User user = null;
		try {
			user = userDAO.selectUserById(id);
			user.setLikedRestaurants(this.getLikedRestaurants(user.getId()));
			if (user == null) {
				// Lav respons
				ErrorMessage e = new ErrorMessage("Not found", 404);
				Response r = Response.status(Status.NOT_FOUND).entity(e).build();

				// Kast respons
				throw new WebApplicationException(r); // kunne også kaste NotFoundException(r); så behøvede man ikke at
														// definerer status i ovenstående respons - se javadoc for
														// WebApEx
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		System.out.println("Got specific user: " + user.getId() + ", " + user.getFirstName());

		return user;
	}

	public User addUser(User user) {
//		user.setId(users.size() + 1);
		users.add(user);

		try {
			userDAO.insertUser(user);
			this.users = getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User updateUser(User user) {
		if (user.getId() <= 0) {
			return null;
		}
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == user.getId()) {
				users.set(i, user);
				break;
			}
		}
		
		try {
			userDAO.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User removeUser(long id) {
		User toBeRemoved = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				toBeRemoved = users.get(i);
				users.remove(i);
			}
		}
		
		try {
			userDAO.deleteUser(toBeRemoved);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toBeRemoved;
	}

	public ArrayList<Restaurant> getLikedRestaurants(long id) {
		try {
			return userDAO.selectAllLikedRestaurants(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public User likeRestaurant(long userId, long restaurantId) {
		if(restaurantId <= 0 || userId <= 0 ) {
			return null;
		}
		
		try {
			userDAO.insertLikedRestaurant(userId, restaurantId);
			this.users = getAllUsers(); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		for(User u: this.users) {
			if(u.getId() == userId)
				return u;
		}
		return null;
	}
	
	public User unLikeRestaurant(long userId, long restaurantId) {
		if(restaurantId <= 0 || userId <= 0 ) {
			return null;
		}
		
		try {
			
			userDAO.deleteLikedRestaurant(userId, restaurantId);
			this.users = getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(User u: this.users) {
			if(u.getId() == userId)
				return u;
		}
		return null;
	}
	
//	public User updateUser(User user) {
//		if (user.getId() <= 0) {
//			return null;
//		}
//		for (int i = 0; i < users.size(); i++) {
//			if (users.get(i).getId() == user.getId()) {
//				users.set(i, user);
//				break;
//			}
//		}
//		
//		try {
//			userDAO.updateUser(user);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//	
//	ublic User addUser(User user) {
////		user.setId(users.size() + 1);
//		users.add(user);
//
//		try {
//			userDAO.insertUser(user);
//			this.users = getAllUsers();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}

}
