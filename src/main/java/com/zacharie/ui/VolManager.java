/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.ui;

import com.zacharie.data.VolAvn;
import com.zacharie.facade.VolAvnFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Zacharie
 */
@Named(value = "volManager")
@SessionScoped
public class VolManager implements Serializable {

    private VolAvn va;
    private Object id;

    @EJB
    private VolAvnFacade vaFacade;

    public VolManager() {
    }

    public String requestVol() {
        this.va = this.vaFacade.find(this.id);
        return "vol?faces-redirect=true";
    }

    public VolAvn getVa() {
        return va;
    }

    public void setVa(VolAvn va) {
        this.va = va;
    }

    public VolAvnFacade getVaFacade() {
        return vaFacade;
    }

    public void setVaFacade(VolAvnFacade vaFacade) {
        this.vaFacade = vaFacade;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

}
