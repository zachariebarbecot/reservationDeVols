/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.ui;

import com.zacharie.data.Compagnie;
import com.zacharie.facade.AdresseFacade;
import com.zacharie.facade.CompagnieFacade;
import com.zacharie.facade.TelephoneFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Zacharie
 */
@DeclareRoles({"admin", "client"})
@Named(value = "compagniesManager")
@RequestScoped
public class CompagniesManager implements Serializable {

    private List<Compagnie> cpnCollection;
    private Compagnie cpnRemove;

    @EJB
    private CompagnieFacade cpnFacade;
    @EJB
    private AdresseFacade adrFacade;
    @EJB
    private TelephoneFacade telFacade;

    public CompagniesManager() {
    }

    @PostConstruct
    public void init() {
        this.cpnCollection = this.cpnFacade.findAll();
    }

    @RolesAllowed({"admin"})
    public String removeCompagnie() {
        return "toCompagniesList";
    }

    public List<Compagnie> getCpnCollection() {
        return cpnCollection;
    }

    public void setCpnCollection(List<Compagnie> cpnCollection) {
        this.cpnCollection = cpnCollection;
    }

    public CompagnieFacade getCpnFacade() {
        return cpnFacade;
    }

    public void setCpnFacade(CompagnieFacade cpnFacade) {
        this.cpnFacade = cpnFacade;
    }

    public Compagnie getCpnRemove() {
        return cpnRemove;
    }

    public void setCpnRemove(Compagnie cpnRemove) {
        this.cpnRemove = cpnRemove;
    }

    public AdresseFacade getAdrFacade() {
        return adrFacade;
    }

    public void setAdrFacade(AdresseFacade adrFacade) {
        this.adrFacade = adrFacade;
    }

    public TelephoneFacade getTelFacade() {
        return telFacade;
    }

    public void setTelFacade(TelephoneFacade telFacade) {
        this.telFacade = telFacade;
    }

}
