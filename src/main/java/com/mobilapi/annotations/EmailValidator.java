package com.mobilapi.annotations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;


@Target({FIELD,ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidatorChecker.class)
public @interface EmailValidator {

    String message() default "email must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
