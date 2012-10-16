package com.mycompany.library.validation.item;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator class used to validate images in application.
 * @author estelius
 */
@FacesValidator("ImageValidator")
public class ImageValidator implements Validator {

    // Constraints.
    private static final String URI_REGEXP = "";
    private static final String IMAGE_EXTENSION_REGEXP = "";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
