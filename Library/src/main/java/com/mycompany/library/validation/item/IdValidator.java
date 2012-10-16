package com.mycompany.library.validation.item;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate Ids in application.
 * @author estelius
 */
public class IdValidator implements Validator {

    // Constraints.
    private static final String ISBN_REGEXP = "";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
