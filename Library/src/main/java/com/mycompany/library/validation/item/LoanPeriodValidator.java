package com.mycompany.library.validation.item;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate loan periods in application.
 * @author estelius
 */
@FacesValidator("LoanPeriodValidator")
public class LoanPeriodValidator implements Validator {

    // Constraints.
    private static final int MIN = 0;
    private static final int MAX = 60;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        int loanPeriod = (int) value;
        
        // Check length constraints.
        if (loanPeriod < MIN) {
            String summary = "Utlåningstiden kan inte vara mindre än " + MIN + ".";
            String detail = "Utlåningstiden kan inte vara mindre än " + MIN + ".";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        } else if (loanPeriod > MAX) {
            String summary = "Utlåningstiden kan inte vara större än " + MAX + ".";
            String detail = "Utlåningstiden kan inte vara större än " + MAX + ".";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
