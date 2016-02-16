/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Zacharie
 */
@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator {

    private static final String champConfirmPassword = "confirmPassword";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput confirmPassword = (UIInput) component.getAttributes().get(PasswordValidator.champConfirmPassword);
        String password = (String) confirmPassword.getValue();
        String confirm = (String) value;
        if (confirm != null && !confirm.equals(password)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password différent", null));
        }
    }

}
