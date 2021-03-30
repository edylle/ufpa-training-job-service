package com.freela.job.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = JobValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BudgetConstraint {

    String message() default "BudgetMax must be greater than BudgetMin";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
