/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.ui;

import com.zacharie.data.AvnCpn;
import com.zacharie.data.Compagnie;
import com.zacharie.data.Vol;
import com.zacharie.data.VolAvn;
import com.zacharie.facade.AvnCpnFacade;
import com.zacharie.facade.CompagnieFacade;
import com.zacharie.facade.VolAvnFacade;
import com.zacharie.facade.VolFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Zacharie
 */
@Named(value = "addVoyageManager")
@SessionScoped
public class addVoyageManager implements Serializable {

    private int cpnId, avnCpnId, volId;
    private Vol v;
    private VolAvn va;
    private Compagnie cpn;
    private AvnCpn ac;
    private List<Vol> vCollection;
    private List<Compagnie> cpnCollection;
    private List<AvnCpn> acCollection;

    @EJB
    private VolAvnFacade vaFacade;
    @EJB
    private VolFacade vFacade;
    @EJB
    private CompagnieFacade cpnFacade;
    @EJB
    private AvnCpnFacade acFacade;

    public addVoyageManager() {

    }

    @PostConstruct
    public void init() {
        this.va = new VolAvn();
        this.vCollection = this.vFacade.findAll();
        this.cpnCollection = this.cpnFacade.findAll();
    }

    public void listAvion(AjaxBehaviorEvent event) {
        this.cpn = this.cpnFacade.find(this.cpnId);
        this.acCollection = this.acFacade.findByCpn(this.cpn);
    }

    public String addVoyage() {
        this.v = this.vFacade.find(this.volId);
        this.ac = this.acFacade.find(this.avnCpnId);
        this.va.setVolId(this.v);
        this.va.setAvnCpnId(this.ac);
        this.vaFacade.create(this.va);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Voyage ajouté", null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "toAddVoyage";
    }

    public int getAvnCpnId() {
        return avnCpnId;
    }

    public void setAvnCpnId(int avnCpnId) {
        this.avnCpnId = avnCpnId;
    }

    public int getVolId() {
        return volId;
    }

    public void setVolId(int volId) {
        this.volId = volId;
    }

    public Vol getV() {
        return v;
    }

    public void setV(Vol v) {
        this.v = v;
    }

    public int getCpnId() {
        return cpnId;
    }

    public void setCpnId(int cpnId) {
        this.cpnId = cpnId;
    }

    public VolAvn getVa() {
        return va;
    }

    public void setVa(VolAvn va) {
        this.va = va;
    }

    public Compagnie getCpn() {
        return cpn;
    }

    public void setCpn(Compagnie cpn) {
        this.cpn = cpn;
    }

    public List<Vol> getvCollection() {
        return vCollection;
    }

    public void setvCollection(List<Vol> vCollection) {
        this.vCollection = vCollection;
    }

    public List<Compagnie> getCpnCollection() {
        return cpnCollection;
    }

    public void setCpnCollection(List<Compagnie> cpnCollection) {
        this.cpnCollection = cpnCollection;
    }

    public VolAvnFacade getVaFacade() {
        return vaFacade;
    }

    public void setVaFacade(VolAvnFacade vaFacade) {
        this.vaFacade = vaFacade;
    }

    public VolFacade getvFacade() {
        return vFacade;
    }

    public void setvFacade(VolFacade vFacade) {
        this.vFacade = vFacade;
    }

    public CompagnieFacade getCpnFacade() {
        return cpnFacade;
    }

    public void setCpnFacade(CompagnieFacade cpnFacade) {
        this.cpnFacade = cpnFacade;
    }

    public AvnCpn getAc() {
        return ac;
    }

    public void setAc(AvnCpn ac) {
        this.ac = ac;
    }

    public List<AvnCpn> getAcCollection() {
        return acCollection;
    }

    public void setAcCollection(List<AvnCpn> acCollection) {
        this.acCollection = acCollection;
    }

    public AvnCpnFacade getAcFacade() {
        return acFacade;
    }

    public void setAcFacade(AvnCpnFacade acFacade) {
        this.acFacade = acFacade;
    }

}
