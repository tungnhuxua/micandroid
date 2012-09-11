package ningbo.media.springmvc.validator.constraints;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ningbo.media.springmvc.validator.constraints.impl.EmptyOrSizeValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = EmptyOrSizeValidator.class)
@Documented
public @interface EmptyOrSize {

	String message() default "{ningbo.media.springmvc.validator.constraints.EmptyOrSize.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * @return size the element must be higher or equal to
	 */
	int min() default 0;

	/**
	 * @return size the element must be lower or equal to
	 */
	int max() default Integer.MAX_VALUE;
}
