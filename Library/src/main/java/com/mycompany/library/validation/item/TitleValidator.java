package com.mycompany.library.validation.item;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate titles in application.
 * @author estelius
 */
@FacesValidator("TitleValidator")
public class TitleValidator implements Validator {

    // Constraints.
    private static final int MIN_LENGTH = 1;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String title = (String) value;
        
        // Check length constraints.
        if (title.length() < MIN_LENGTH) {
            String summary = "Fältet är obligatoriskt.";
            String detail = "Fältet är obligatoriskt.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
