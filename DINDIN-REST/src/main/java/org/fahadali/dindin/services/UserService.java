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

	// private Map<Long, User> users = DatabaseClass.getUsers(); // Dette skal reelt
	// set ske til databasen, og ikke til mockup-klassen
	private UserDAOImp userDAO;
	private Map<Long, User> users = new HashMap<>();

	public UserService() {

		userDAO = new UserDAOImp();

	}

	public List<User> getAllUsers() {
		
		try {

			for (User u : userDAO.selectAllUsers()) {
				users.put(u.getId(), u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<User>(users.values()); 
	}

	/*
	 * Eksempel på WebApplicationException, der ikke behøves at mappe Kan sende
	 * Status.x, response.x osv.
	 */
	public User getUser(long id) {
		User user = users.get(id);
		if (user == null) {
			// Lav respons
			ErrorMessage e = new ErrorMessage("Not found", 404);
			Response r = Response.status(Status.NOT_FOUND).entity(e).build();

			// Kast respons
			throw new WebApplicationException(r); // kunne også kaste NotFoundException(r); så behøvede man ikke at
													// definerer status i ovenstående respons - se javadoc for WebApEx

		}
		return user;
	}

	public User addUser(User user) {
		user.setId(users.size() + 1);
		users.put(user.getId(), user);
		
		try {
			userDAO.insertUser(user);
			getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User updateUser(User user) {
		if (user.getId() <= 0) {
			return null;
		}
		users.put(user.getId(), user);
		try {
			userDAO.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User removeUser(long id) {
		User user = users.get(id);
		try {
			userDAO.deleteUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users.remove(id);
	}
	
	public ArrayList<Restaurant> getLikedRestaurants(long id){
		try {
			return userDAO.selectAllLikedRestaurants(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
