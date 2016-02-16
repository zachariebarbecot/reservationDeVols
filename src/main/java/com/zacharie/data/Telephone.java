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
@Table(name = "telephone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telephone.findAll", query = "SELECT t FROM Telephone t"),
    @NamedQuery(name = "Telephone.findByTelId", query = "SELECT t FROM Telephone t WHERE t.telId = :telId"),
    @NamedQuery(name = "Telephone.findByTelNumero", query = "SELECT t FROM Telephone t WHERE t.telNumero = :telNumero")})
public class Telephone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tel_id")
    private Integer telId;
    @Size(max = 20)
    @NotNull(message = "Champ numero vide")
    @Column(name = "tel_numero")
    private String telNumero;
    @JoinTable(name = "cpn_tel", joinColumns = {
        @JoinColumn(name = "tel_id", referencedColumnName = "tel_id")}, inverseJoinColumns = {
        @JoinColumn(name = "cpn_id", referencedColumnName = "cpn_id")})
    @ManyToOne(fetch = FetchType.EAGER)
    private Compagnie cpnId;
    @JoinTable(name = "cl_tel", joinColumns = {
        @JoinColumn(name = "tel_id", referencedColumnName = "tel_id")}, inverseJoinColumns = {
        @JoinColumn(name = "cl_id", referencedColumnName = "cl_id")})
    @ManyToOne(fetch = FetchType.EAGER)
    private Client clId;

    public Telephone() {
    }

    public Telephone(Integer telId) {
        this.telId = telId;
    }

    public Integer getTelId() {
        return telId;
    }

    public void setTelId(Integer telId) {
        this.telId = telId;
    }

    public String getTelNumero() {
        return telNumero;
    }

    public void setTelNumero(String telNumero) {
        this.telNumero = telNumero;
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
        hash += (telId != null ? telId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telephone)) {
            return false;
        }
        Telephone other = (Telephone) object;
        if ((this.telId == null && other.telId != null) || (this.telId != null && !this.telId.equals(other.telId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.Telephone[ telId=" + telId + " ]";
    }

}
