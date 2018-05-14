package org.fahadali.dindin.resources;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import java.util.Date;
import org.fahadali.dindin.model.Credentials;
import org.fahadali.dindin.model.User;
import org.fahadali.dindin.services.LoginService;
import org.fahadali.dindin.services.UserService;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("login")
public class LoginResource {

	private LoginService loginService = new LoginService();
	private UserService userService = new UserService();
	private Bruger bruger; // Javabog
	private User user; // Egen database

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response login(Credentials credentials) {
		Brugeradmin ba = loginService.getBrugerAdmin();

		try {
			this.bruger = ba.hentBruger(credentials.getUsername(), credentials.getPassword());

			// Check if in own database, if not then admit
			if (!userService.checkIfUserIsInDatabase(bruger.brugernavn)) {
				user = new User(bruger.brugernavn, bruger.email, bruger.fornavn, bruger.efternavn);
				userService.addUser(user);
				System.out.println(
						"User exists in Javabog's user authentication module, but not in the database. User is added to database");
			} else {
				user = userService.getUserByUsername(credentials.getUsername());
			}

			// Issue token
			String token = createJWT(credentials.getUsername());
			System.out.println("Issuing token: " + token);

			Response res = Response.ok().entity(user).header("Authorization", "Bearer " + token).build();
			return res;

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}

	}

	private String createJWT(String username) {

		// Used to sign the token
		SignatureAlgorithm sigAlgo = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new java.util.Date(nowMillis);

		// JWT ttl (3 hours)
		long ttlMillis = 7200000;
		long expMillis = nowMillis + ttlMillis;
		Date expiration = new Date(expMillis);

		// Dummy key
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secret");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, sigAlgo.getJcaName());

		// Defining JWT
		JwtBuilder builder = Jwts.builder().setIssuedAt(now).setIssuer("DINDIN").claim("username", username)
				.signWith(sigAlgo, signingKey).setExpiration(expiration);

		return builder.compact();

	}

}
