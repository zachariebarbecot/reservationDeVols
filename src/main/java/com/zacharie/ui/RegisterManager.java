/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.ui;

import com.zacharie.data.Adresse;
import com.zacharie.data.Client;
import com.zacharie.data.Groupe;
import com.zacharie.data.Telephone;
import com.zacharie.facade.AdresseFacade;
import com.zacharie.facade.ClientFacade;
import com.zacharie.facade.GroupeFacade;
import com.zacharie.facade.TelephoneFacade;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Zacharie
 */
@Named(value = "registerManager")
@RequestScoped
public class RegisterManager implements Serializable {

    private Client cl;
    private Adresse adr;
    private Telephone tel;
    private Groupe g;

    @EJB
    private ClientFacade clFacade;
    @EJB
    private AdresseFacade adrFacade;
    @EJB
    private TelephoneFacade telFacade;
    @EJB
    private GroupeFacade gFacade;

    public RegisterManager() {
    }

    @PostConstruct
    public void init() {
        this.cl = new Client();
        this.adr = new Adresse();
        this.tel = new Telephone();
        this.g = new Groupe();
    }

    public String register() throws NoSuchAlgorithmException {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        md.update(this.cl.getClPassword().getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        this.cl.setClPassword(sb.toString());
        List<Telephone> telL = this.telFacade.findByTelNum(this.tel.getTelNumero());
        if (telL.isEmpty()) {
            this.telFacade.create(this.tel);
            telL.add(this.tel);
            this.cl.setTelephoneCollection(telL);
        } else {
            this.cl.setTelephoneCollection(telL);
        }
        List<Adresse> adrL = this.adrFacade.findByAdr(this.adr.getAdrNumero(), this.adr.getAdrRue(), this.adr.getAdrZip(), this.adr.getAdrVille());
        if (adrL.isEmpty()) {
            this.adrFacade.create(this.adr);
            adrL.add(this.adr);
            this.cl.setAdresseCollection(adrL);
        } else {
            this.cl.setAdresseCollection(adrL);
        }
        this.g.setGNom("Client");
        this.g.setClId(this.cl);
        this.clFacade.create(this.cl);
        this.gFacade.create(g);
        this.cl = null;
        this.adr = null;
        this.tel = null;
        this.g = null;
        return "toIndex";
    }

    public Client getCl() {
        return cl;
    }

    public void setCl(Client cl) {
        this.cl = cl;
    }

    public Adresse getAdr() {
        return adr;
    }

    public void setAdr(Adresse adr) {
        this.adr = adr;
    }

    public Telephone getTel() {
        return tel;
    }

    public void setTel(Telephone tel) {
        this.tel = tel;
    }

    public ClientFacade getClFacade() {
        return clFacade;
    }

    public void setClFacade(ClientFacade clFacade) {
        this.clFacade = clFacade;
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

    public Groupe getG() {
        return g;
    }

    public void setG(Groupe g) {
        this.g = g;
    }

    public GroupeFacade getgFacade() {
        return gFacade;
    }

    public void setgFacade(GroupeFacade gFacade) {
        this.gFacade = gFacade;
    }

}
