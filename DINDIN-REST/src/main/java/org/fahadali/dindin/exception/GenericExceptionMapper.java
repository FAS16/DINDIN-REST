package org.fahadali.dindin.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.fahadali.dindin.model.ErrorMessage;

/*
 * Denne klasse ALLE exception (som ikke har deres egen mapper)
 */
	
	//Slået fra@Provider //Fortæller JAX-RS er det er en vigtig klasse den skal bruge! slået fra da webapplicationexception skulle prøves
	public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

		@Override
		public Response toResponse(Throwable ex) {
			//Custom response when an exception is thrown
			ErrorMessage e = new ErrorMessage(ex.getMessage(), 500);
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		
		

}
