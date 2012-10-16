package com.mycompany.library.validation.item;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate quantities in application.
 * @author estelius
 */
@FacesValidator("QuantityValidator")
public class QuantityValidator implements Validator {

    // Constraints.
    private static final int MIN = 0;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        int quantity = (int) value;
        
        // Check length constraints.
        if (quantity < MIN) {
            String summary = "Kvantiteten kan inte understiga 0.";
            String detail = "Kvantiteten kan inte understiga 0.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
