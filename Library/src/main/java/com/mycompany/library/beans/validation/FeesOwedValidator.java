package com.mycompany.library.beans.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate feesOwed in application.
 * @author estelius
 */
@FacesValidator("FeesOwedValidator")
public class FeesOwedValidator implements Validator {

    // Constraints.
    private static final double MIN = 0.0;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        double feesOwed = (double) value;
        
        // Checking constraints.
        if (feesOwed < MIN) {
            String summary = "Skulden kan inte understiga " + MIN + " kr.";
            String detail = "Skulden kan inte understiga " + MIN + " kr.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
