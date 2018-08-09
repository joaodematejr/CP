package br.com.casadaspeliculas.common;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

	String message() default "{label.email.nao.valido}";

	Class<?>[] group() default {};

	Class<? extends Payload>[] payload() default {};

}
