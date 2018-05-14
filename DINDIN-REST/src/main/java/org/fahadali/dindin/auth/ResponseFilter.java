
package org.fahadali.dindin.auth;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

// ResponseFilter - tilader cross domain requests

@Provider
public class ResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		
		System.out.println("CORS-filter invoked*");
		String origin = requestContext.getHeaderString("origin");
		if (origin != null) {
			responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
			System.out.println("Header added to response in ResponseFilter");
		}
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Access-Control-Expose-Headers", "Authorization");
		responseContext.getHeaders().putSingle("Access-Control-Allow-Methods",
				"OPTIONS, GET, POST, PUT, DELETE, PATCH");
	}
}
