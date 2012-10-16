package com.mycompany.library.validation.item;

import javax.faces.application.FacesMessage;
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
        
        int yearRelease = (int) value;
        
        // Check length constraints.
        if (yearRelease > CURRENT_YEAR) {
            String summary = "Utgivningsåret kan inte vara högre än nuvarande år (" + CURRENT_YEAR + ").";
            String detail = "Utgivningsåret kan inte vara högre än nuvarande år (" + CURRENT_YEAR + ").";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
