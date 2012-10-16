package com.mycompany.library.validation.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate email addresses in application.
 * @author Fredrik
 */
@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

    // Constraints.
    private static final String regex = "^[_a-zA-Z0-9]+(\\.[_a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String email = (String) value;
        
        // Check length constraints.
        int usernameLength = email.length();
        if (usernameLength == 0) {
            String summary = "Fältet är obligatoriskt.";
            String detail = "Fältet är obligatoriskt.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        
        // Check contents constraints.
        Pattern mask = Pattern.compile(regex);
        Matcher matcher = mask.matcher(email);
        if (!matcher.matches()) {
            String summary = "Email adressen är inte giltig.";
            String detail = "En email adress måste ha formen [string](.[string]@[string].[string].";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
