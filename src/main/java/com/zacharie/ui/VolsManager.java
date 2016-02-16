/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.ui;

import com.zacharie.data.VolAvn;
import com.zacharie.facade.VolAvnFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Zacharie
 */
@Named(value = "volsManager")
@RequestScoped
public class VolsManager implements Serializable {

    private List<VolAvn> vaCollection;
    @EJB
    private VolAvnFacade vaFacade;

    public VolsManager() {
    }

    @PostConstruct
    public void init() {
        this.vaCollection = this.vaFacade.findAll();
    }

    public List<VolAvn> getVaCollection() {
        return vaCollection;
    }

    public void setVaCollection(List<VolAvn> vaCollection) {
        this.vaCollection = vaCollection;
    }

    public VolAvnFacade getvaFacade() {
        return vaFacade;
    }

    public void setvaFacade(VolAvnFacade vFacade) {
        this.vaFacade = vFacade;
    }

}
