package com.mycompany.library.validation.item;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author sjoholmf
 */
@FacesValidator("PageNumValidator")
public class PageNumValidator implements Validator{
    
    private static final int MIN = 1;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        int pageNum = (int) value;
        
        if(pageNum < MIN){
            String summary = "Antal sidor måste vara minst " + MIN + ".";
            String detail = "Antal sidor måste vara minst " + MIN + ".";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
        }
        
    }
    
}
