package org.fahadali.dindin.resources;

import java.rmi.RemoteException;

import java.security.Key;

import javax.crypto.KeyGenerator;
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
import org.fahadali.dindin.services.LoginService;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * TODO
 * Add user to database, when he is authorized.
 */

@Path("login")
public class LoginResource {

	private LoginService loginService = new LoginService();
	private Bruger bruger;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response login(Credentials credentials) {
		Brugeradmin ba = loginService.getBrugerAdmin();

		try {
			this.bruger = ba.hentBruger(credentials.getUsername(), credentials.getPassword());
			// Issue token
			String token = createJWT(credentials.getUsername());
			
			// Return token
			return Response.ok().entity(bruger).header("Authorization", "Bearer "+token).build();

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
		JwtBuilder builder = Jwts.builder()
				.setIssuedAt(now)
				.setIssuer("DINDIN")
				.claim("username", username)
				.signWith(sigAlgo, signingKey)
				.setExpiration(expiration);

		return builder.compact();

	}

}
