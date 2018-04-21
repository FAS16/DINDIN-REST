package org.fahadali.dindin.auth;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * Dette interface har ansvaret for "name-binding", altså er det en annotation til at binde til filteret
 */

@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Secured {
}