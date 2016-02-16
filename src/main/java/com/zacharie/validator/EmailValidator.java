/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.validator;

import com.zacharie.data.Client;
import com.zacharie.facade.ClientFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Zacharie
 */
@Named(value = "emailValidator")
@RequestScoped
public class EmailValidator implements Validator {

    private static final String champConfirmEmail = "confirmEmail";

    @EJB
    private ClientFacade clFacade;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput confirmEmail = (UIInput) component.getAttributes().get(EmailValidator.champConfirmEmail);
        String email = (String) confirmEmail.getValue();
        List<Client> clL = this.clFacade.findByEmail(email);
        if (!clL.isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "L'email existe déjà", null));
        }
        String confirm = (String) value;
        if (confirm != null && !confirm.equals(email)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password différent", null));
        }
    }

}
