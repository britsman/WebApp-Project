package com.mycompany.library.validation.item;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate fees in application.
 * @author estelius
 */
@FacesValidator("FeeValidator")
public class FeeValidator implements Validator {

    // Constraints.
    private static final double MIN = 0.0;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
