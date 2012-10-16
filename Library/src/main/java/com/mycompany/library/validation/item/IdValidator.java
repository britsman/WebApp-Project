package com.mycompany.library.validation.item;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate ids in application.
 * @author estelius
 */
@FacesValidator("IdValidator")
public class IdValidator implements Validator {

    // Constraints.
    private static final String ISBN_REGEXP = "";
    private static final int MIN_LENGTH = 1;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String id = (String) value;
        
        // Check length constraints.
        if (id.length() < MIN_LENGTH) {
            String summary = "Fältet är obligatoriskt.";
            String detail = "Fältet är obligatoriskt.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
