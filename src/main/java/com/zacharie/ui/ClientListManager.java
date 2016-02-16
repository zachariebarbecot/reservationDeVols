/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.ui;

import com.zacharie.data.Adresse;
import com.zacharie.data.Client;
import com.zacharie.data.Groupe;
import com.zacharie.data.Reservation;
import com.zacharie.data.Telephone;
import com.zacharie.facade.AdresseFacade;
import com.zacharie.facade.ClientFacade;
import com.zacharie.facade.GroupeFacade;
import com.zacharie.facade.ReservationFacade;
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
@Named(value = "clientListManager")
@RequestScoped
public class ClientListManager implements Serializable {

    private List<Client> clientCollection;
    private Client clientRemove;

    @EJB
    private ClientFacade clFacade;
    @EJB
    private GroupeFacade gFacade;
    @EJB
    private AdresseFacade adrFacade;
    @EJB
    private TelephoneFacade telFacade;
    @EJB
    private ReservationFacade rFacade;

    public ClientListManager() {
    }

    @PostConstruct
    public void init() {
        this.clientCollection = this.clFacade.findAll();
    }

    @RolesAllowed({"admin"})
    public String removeClient() {
        List<Groupe> gL = this.gFacade.findAll();
        for (Groupe gL1 : gL) {
            if (gL1.getClId().equals(this.clientRemove)) {
                this.gFacade.remove(gL1);
            }
        }
        List<Adresse> adrL = this.adrFacade.findAll();
        for (Adresse adrL1 : adrL) {
            if (adrL1.getClId() != null && adrL1.getClId().equals(this.clientRemove)) {
                this.adrFacade.remove(adrL1);
            }
        }
        List<Telephone> telL = this.telFacade.findAll();
        for (Telephone telL1 : telL) {
            if (telL1.getClId() != null && telL1.getClId().equals(this.clientRemove)) {
                this.telFacade.remove(telL1);
            }
        }
        List<Reservation> rL = this.rFacade.findAll();
        for (Reservation rL1 : rL) {
            if (rL1.getClId().equals(this.clientRemove)) {
                this.rFacade.remove(rL1);
            }
        }
        this.clFacade.remove(this.clientRemove);
        return "toClientsList";
    }

    public List<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(List<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    public ClientFacade getClFacade() {
        return clFacade;
    }

    public void setClFacade(ClientFacade clFacade) {
        this.clFacade = clFacade;
    }

    public Client getClientRemove() {
        return clientRemove;
    }

    public void setClientRemove(Client clientRemove) {
        this.clientRemove = clientRemove;
    }

    public GroupeFacade getgFacade() {
        return gFacade;
    }

    public void setgFacade(GroupeFacade gFacade) {
        this.gFacade = gFacade;
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

    public ReservationFacade getrFacade() {
        return rFacade;
    }

    public void setrFacade(ReservationFacade rFacade) {
        this.rFacade = rFacade;
    }

}
