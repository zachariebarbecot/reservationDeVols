/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.ui;

import com.zacharie.data.Vol;
import com.zacharie.facade.VolFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Zacharie
 */
@Named(value = "addVolManager")
@RequestScoped
public class AddVolManager implements Serializable {

    private Vol v = new Vol();

    @EJB
    private VolFacade vFacade;

    public AddVolManager() {
    }

    public String addVol() {
        List<Vol> vL = this.vFacade.findAll();
        for (Vol vL1 : vL) {
            if (vL1.getVolDepart().equals(this.v.getVolDepart())) {
                if (vL1.getVolArrivee().equals(this.v.getVolArrivee())) {
                    if (vL1.getVolPrix() == this.v.getVolPrix()) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Vol déjà dans la bdd", null));
                        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                        return "toAddVol";
                    }
                }
            }
        }
        this.vFacade.create(this.v);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vol ajouté", null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "toAddVol";
    }

    public Vol getV() {
        return v;
    }

    public void setV(Vol v) {
        this.v = v;
    }

    public VolFacade getvFacade() {
        return vFacade;
    }

    public void setvFacade(VolFacade vFacade) {
        this.vFacade = vFacade;
    }

}
