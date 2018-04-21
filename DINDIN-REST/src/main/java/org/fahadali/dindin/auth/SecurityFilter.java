package org.fahadali.dindin.auth;

import java.io.IOException;
import java.security.Key;

import javax.annotation.Priority;
import javax.crypto.KeyGenerator;
import javax.inject.Inject;
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

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer";
	private static final String UNATHORIZED_MSG = "User not authorized";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Retrieving the HTTP Authorization header from the request
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (authHeader != null && authHeader.length() > 0) {
			// Retrieving the token from the Authorization header
			String token = authHeader.substring("AUTHORIZATION_HEADER_PREFIX".length()).trim();

			try {
				// Verifying the token
				verifyJWT(token);

			} catch (Exception e) {

				Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
						.entity(UNATHORIZED_MSG)
						.build();

				requestContext.abortWith(unauthorizedStatus);

			}
		}

	}

	private void verifyJWT(String token) {

		Claims claims = Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
				.parseClaimsJws(token)
				.getBody();

	}

}
