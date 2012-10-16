package com.mycompany.library.validation.item;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate year released in application.
 * @author estelius
 */
@FacesValidator("YearReleaseValidator")
public class YearReleaseValidator implements Validator {

    // Constraints.
    private static final int CURRENT_YEAR = 2012;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
