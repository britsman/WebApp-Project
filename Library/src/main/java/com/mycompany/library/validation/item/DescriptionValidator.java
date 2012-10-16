package com.mycompany.library.validation.item;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate descriptions in application.
 * @author estelius
 */
@FacesValidator("DescriptionValidator")
public class DescriptionValidator implements Validator {

    // Constraints.
    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 300;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String description = (String) value;
        
        // Check length constraints.
        if (description.length() < MIN_LENGTH) {
            String summary = "Fältet är obligatoriskt.";
            String detail = "Fältet är obligatoriskt.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        } else if (description.length() > MAX_LENGTH) {
            String summary = "Beskrivningen är för lång.";
            String detail = "Beskrivningen är för lång.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
