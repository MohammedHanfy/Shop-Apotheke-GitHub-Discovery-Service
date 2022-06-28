package com.mah.shop.apotheke.validators;


import com.mah.shop.apotheke.rest.dto.SearchRepositoriesRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SearchRepositoriesRequestDTOValidator implements ConstraintValidator<ValidSearchRepositoriesRequestDTO, SearchRepositoriesRequestDTO> {

    private static final String MESSAGE = "message";

    @Override
    public void initialize(ValidSearchRepositoriesRequestDTO constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(SearchRepositoriesRequestDTO searchRepositoriesRequestDTO, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.isBlank(searchRepositoriesRequestDTO.getCreationDate()) || StringUtils.isBlank(searchRepositoriesRequestDTO.getLanguage())) {

            ((ConstraintValidatorContextImpl) constraintValidatorContext).addMessageParameter(MESSAGE, "Either creationDate or language must not be null.");
            return false;
        }

        return true;
    }
}
