package org.fahadali.dindin.auth;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/*
 * Filter defined for requests, that require authentication
 * 
 */

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
	private static final String UNATHORIZED_MSG = "User not authorized";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		System.out.println("Security filter invoked - Secured resource requested");

		// Retrieving the HTTP Authorization header from the request
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (authHeader == null || authHeader.length() == 0) {
			System.out.println("No Auth header found");
			throw new NotAuthorizedException("Authorization needed - no auth header found");
			
		}
			// Retrieving the token from the Authorization header
			System.out.println("Auth header found: " + authHeader);
			//String token = authHeader.substring("AUTHORIZATION_HEADER_PREFIX".length());
			String token = authHeader.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			System.out.println("Token retrieved: " + token);

			try {
				// Verifying the token
				validateJWT(token);
				
			} catch (Exception e) {
				System.out.println("Invalid token!");
				Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
						.build();

				requestContext.abortWith(unauthorizedStatus);

			}
		}

	

	private void validateJWT(String token) {

		System.out.println("Validating token...");
		Claims claims = Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
				.parseClaimsJws(token)
				.getBody();
		System.out.println("The token in valid!");

	}

}
