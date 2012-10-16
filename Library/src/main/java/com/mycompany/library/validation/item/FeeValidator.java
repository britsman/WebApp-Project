package com.mycompany.library.validation.item;

import javax.faces.application.FacesMessage;
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
        
        int fee = (int) value;
        
        // Check length constraints.
        if (fee < MIN) {
            String summary = "Skulden kan inte vara mindre än " + MIN + " kr.";
            String detail = "Skulden kan inte vara mindre än " + MIN + " kr.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
