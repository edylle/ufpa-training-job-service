package com.freela.job.validator;

import com.freela.job.dto.in.JobIn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JobValidator implements ConstraintValidator<BudgetConstraint, JobIn> {

    @Override
    public boolean isValid(JobIn job, ConstraintValidatorContext constraintValidatorContext) {
        return (job.budgetMin != null && job.budgetMax != null && job.budgetMax > job.budgetMin);
    }
}
