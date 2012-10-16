package com.mycompany.library.validation.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;

/**
 * Validator class used to validate usernames in application.
 * @author Fredrik
 */
@FacesValidator("UsernameValidator")
public class UsernameValidator implements Validator {
    
    // Constraints.
    private static final String regex = "[a-zA-Z]*";
    private static final int min = 3;
    private static final int max = 8;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String username = (String) value;
        
        // Check length constraints.
        int usernameLength = username.length();
        if (usernameLength == 0) {
            String summary = "Fältet är obligatoriskt.";
            String detail = "Fältet är obligatoriskt.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        } else if (usernameLength < min || usernameLength > max) {
            String summary = "Användarnamnet måste vara mellan " + min + " och "+ max + " tecken långt.";
            String detail = "Användarnamnet måste vara mellan " + min + " och "+ max + " tecken långt.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        
        // Check contents constraints.
        Pattern mask = Pattern.compile(regex);
        Matcher matcher = mask.matcher(username);
        if (!matcher.matches()) {
            String summary = "Användarnamnet är inte giltigt.";
            String detail = "Ett användarnamn kan endast bestå av stora och små bokstäver från a-z.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
    }
}
