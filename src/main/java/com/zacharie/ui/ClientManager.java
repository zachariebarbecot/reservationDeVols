/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.ui;

import com.zacharie.utils.Session;
import com.zacharie.data.Client;
import com.zacharie.data.Reservation;
import com.zacharie.data.VolAvn;
import com.zacharie.facade.ClientFacade;
import com.zacharie.facade.ReservationFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Zacharie
 */
@DeclareRoles({"admin", "client"})
@Named(value = "clientManager")
@SessionScoped
public class ClientManager implements Serializable {

    private Client cl;
    private String email;
    private String password;
    @EJB
    private ClientFacade clFacade;
    @EJB
    private ReservationFacade rFacade;

    public ClientManager() {
    }

    public String login() {
        HttpServletRequest request = Session.getRequest();
        try {
            request.login(this.email, this.password);
            String principal = request.getUserPrincipal().toString();
            List<Client> clL = this.clFacade.findByEmail(principal);
            this.email = this.password = null;
            this.cl = clL.get(0);
            return "toIndex";
        } catch (ServletException e) {
            this.email = this.password = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Impossible de se connecter", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "login?faces-redirect=true";
        }
    }

    @RolesAllowed({"admin", "client"})
    public String logout() {
        try {
            this.cl = null;
            HttpServletRequest request = Session.getRequest();
            request.logout();
        } catch (ServletException ex) {
            return "toIndex";
        }
        return "toIndex";
    }

    @RolesAllowed({"admin", "client"})
    public String updateProfil() {
        this.clFacade.edit(this.cl);
        return "toProfil";
    }

    @RolesAllowed({"admin", "client"})
    public String deleteReservation(Reservation r
    ) {
        this.cl.getReservationCollection().remove(r);
        this.rFacade.remove(r);
        return "toProfil";
    }

    @RolesAllowed({"admin", "client"})
    public boolean isReserved(VolAvn va) {
        for (Reservation res : this.cl.getReservationCollection()) {
            if (res.getVolAvnId().getVolAvnId() == va.getVolAvnId()) {
                return true;
            }
        }
        return false;
    }

    @RolesAllowed({"admin", "client"})
    public String doReservation(VolAvn va
    ) {
        Reservation r = new Reservation();
        r.setClId(this.cl);
        r.setRsvDate(new Date());
        r.setVolAvnId(va);
        this.cl.getReservationCollection().add(r);
        this.rFacade.create(r);
        return "toProfil";
    }

    public boolean isLoggedIn() {
        return this.cl != null;
    }

    public Client getCl() {
        return cl;
    }

    public void setCl(Client cl) {
        this.cl = cl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientFacade getClFacade() {
        return clFacade;
    }

    public void setClFacade(ClientFacade clFacade) {
        this.clFacade = clFacade;
    }

    public ReservationFacade getrFacade() {
        return rFacade;
    }

    public void setrFacade(ReservationFacade rFacade) {
        this.rFacade = rFacade;
    }

}
