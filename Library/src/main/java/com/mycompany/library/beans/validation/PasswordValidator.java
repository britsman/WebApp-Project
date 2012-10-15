package com.mycompany.library.beans.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate passwords in application.
 * @author Fredrik
 */
@FacesValidator("PasswordValidator")
public class PasswordValidator implements Validator {

    // Constraints.
    private static final String REG_EXP = "[a-zA-Z0-9]*";
    private static final int MIN = 3;
    private static final int MAX = 8;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String password = (String) value;
        
        // Check length constraints.
        int usernameLength = password.length();
        if (usernameLength == 0) {
            String summary = "Fältet är obligatoriskt.";
            String detail = "Fältet är obligatoriskt.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        } else if (usernameLength < MIN || usernameLength > MAX) {
            String summary = "Lösenordet måste vara mellan " + MIN + " och "+ MAX + " tecken långt.";
            String detail = "Lösenordet måste vara mellan " + MIN + " och "+ MAX + " tecken långt.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        
        // Check contents constraints.
        Pattern mask = Pattern.compile(REG_EXP);
        Matcher matcher = mask.matcher(password);
        if (!matcher.matches()) {
            String summary = "Lösenordet är inte giltigt.";
            String detail = "Ett Lösenordet kan endast bestå av stora och små bokstäver från a-z och siffror.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
