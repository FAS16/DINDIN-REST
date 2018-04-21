package org.fahadali.dindin.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.fahadali.dindin.model.ErrorMessage;

/*
 * Når en exception kastes fra resource til service, så håndterer JAX-RS 
 * denne ved at slå op i alle klasser annoteret med @Provider, og matcher exceptionen med en Mappers exception
 * Så kaldes toResponse, og dette returneres som svar på brugerens forespørgsel 
 */

@Provider //Fortæller JAX-RS er det er en vigtig klasse den skal bruge!
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		//Custom response when an exception is thrown
		ErrorMessage e = new ErrorMessage(ex.getMessage(), 404);
		return Response.status(Status.NOT_FOUND)
				.entity(e)
				.build();
	}

	
	
	

}
