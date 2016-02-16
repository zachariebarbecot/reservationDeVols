/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zacharie
 */
@Entity
@Table(name = "adresse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresse.findByAdr", query = "SELECT a FROM Adresse a WHERE a.adrNumero = :adrNumero AND a.adrRue = :adrRue AND a.adrZip = :adrZip AND a.adrVille = :adrVille"),
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByAdrId", query = "SELECT a FROM Adresse a WHERE a.adrId = :adrId"),
    @NamedQuery(name = "Adresse.findByAdrNumero", query = "SELECT a FROM Adresse a WHERE a.adrNumero = :adrNumero"),
    @NamedQuery(name = "Adresse.findByAdrRue", query = "SELECT a FROM Adresse a WHERE a.adrRue = :adrRue"),
    @NamedQuery(name = "Adresse.findByAdrZip", query = "SELECT a FROM Adresse a WHERE a.adrZip = :adrZip"),
    @NamedQuery(name = "Adresse.findByAdrVille", query = "SELECT a FROM Adresse a WHERE a.adrVille = :adrVille")})
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adr_id")
    private Integer adrId;
    @Basic(optional = false)
    @NotNull(message = "Champ numero vide")
    @Column(name = "adr_numero")
    private int adrNumero;
    @Basic(optional = false)
    @NotNull(message = "Champ rue vide")
    @Size(min = 1, max = 255)
    @Column(name = "adr_rue")
    private String adrRue;
    @Basic(optional = false)
    @NotNull(message = "Champ zip vide")
    @Column(name = "adr_zip")
    private int adrZip;
    @Basic(optional = false)
    @NotNull(message = "Champ ville vide")
    @Size(min = 1, max = 50)
    @Column(name = "adr_ville")
    private String adrVille;
    @JoinTable(name = "cpn_adr", joinColumns = {
        @JoinColumn(name = "adr_id", referencedColumnName = "adr_id")}, inverseJoinColumns = {
        @JoinColumn(name = "cpn_id", referencedColumnName = "cpn_id")})
    @ManyToOne(fetch = FetchType.EAGER)
    private Compagnie cpnId;
    @JoinTable(name = "cl_adr", joinColumns = {
        @JoinColumn(name = "adr_id", referencedColumnName = "adr_id")}, inverseJoinColumns = {
        @JoinColumn(name = "cl_id", referencedColumnName = "cl_id")})
    @ManyToOne(fetch = FetchType.EAGER)
    private Client clId;

    public Adresse() {
    }

    public Adresse(Integer adrId) {
        this.adrId = adrId;
    }

    public Adresse(Integer adrId, int adrNumero, String adrRue, int adrZip, String adrVille) {
        this.adrId = adrId;
        this.adrNumero = adrNumero;
        this.adrRue = adrRue;
        this.adrZip = adrZip;
        this.adrVille = adrVille;
    }

    public Integer getAdrId() {
        return adrId;
    }

    public void setAdrId(Integer adrId) {
        this.adrId = adrId;
    }

    public int getAdrNumero() {
        return adrNumero;
    }

    public void setAdrNumero(int adrNumero) {
        this.adrNumero = adrNumero;
    }

    public String getAdrRue() {
        return adrRue;
    }

    public void setAdrRue(String adrRue) {
        this.adrRue = adrRue;
    }

    public int getAdrZip() {
        return adrZip;
    }

    public void setAdrZip(int adrZip) {
        this.adrZip = adrZip;
    }

    public String getAdrVille() {
        return adrVille;
    }

    public void setAdrVille(String adrVille) {
        this.adrVille = adrVille;
    }

    public Compagnie getCpnId() {
        return cpnId;
    }

    public void setCpnId(Compagnie cpnId) {
        this.cpnId = cpnId;
    }

    public Client getClId() {
        return clId;
    }

    public void setClId(Client clId) {
        this.clId = clId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adrId != null ? adrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.adrId == null && other.adrId != null) || (this.adrId != null && !this.adrId.equals(other.adrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.Adresse[ adrId=" + adrId + " ]";
    }

}
