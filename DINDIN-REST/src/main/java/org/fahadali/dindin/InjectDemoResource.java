package org.fahadali.dindin;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo") //
@Produces(MediaType.TEXT_PLAIN) //returns json
@Consumes(MediaType.TEXT_PLAIN) // accepts json
public class InjectDemoResource {
	
	/*
	 * Eksempler på annotaioner: 
	 * Parametre bruges ude fra til at tilgå resource-klasser i et rest api
	 * 		MatrixParam - minder meget om QueryParam
	 * 		HeaderParam - Bruges til at få fat i info fra header - kan være brugbart v. fx. sikkerhed
	 * 		Bruges til at sende metadata om requestet - som fx. en authentication token
	 * 		CookieParam - bruges til at få fat i cookies og deres værdier
	 * 		FormParam - bruges til at få fat i værdier, fra HTML-forms
	 */
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(
			 @MatrixParam("param") String matrixParam,
			 @HeaderParam("customHeaderValue") String headerValue,
			 @CookieParam("cookieName") String cookie) {
		
		return "Matrix param: " + matrixParam + ", Header param: " + headerValue + ", Cookie: " + cookie; 
	}
	
	
	/*
	 * Context - bruges med få specielle klasser som UriInfo - Denne bruges til at håndterer URIen, der blev brug til at lave requestet
	 * HttpHeaders - bruges til at få header-information
	 */
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		
		String path = uriInfo.getAbsolutePath().toString();
		String cookie = headers.getCookies().toString();
		return "Path: "+ path + ", Cookie: "+ cookie;
	}

}
