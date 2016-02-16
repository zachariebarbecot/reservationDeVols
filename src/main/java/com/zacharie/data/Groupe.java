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
@Table(name = "groupe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
    @NamedQuery(name = "Groupe.findByGId", query = "SELECT g FROM Groupe g WHERE g.gId = :gId"),
    @NamedQuery(name = "Groupe.findByGNom", query = "SELECT g FROM Groupe g WHERE g.gNom = :gNom")})
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "g_id")
    private Integer gId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "g_nom")
    private String gNom;
    @JoinColumn(name = "cl_id", referencedColumnName = "cl_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Client clId;

    public Groupe() {
    }

    public Groupe(Integer gId) {
        this.gId = gId;
    }

    public Groupe(Integer gId, String gNom) {
        this.gId = gId;
        this.gNom = gNom;
    }

    public Groupe(Integer gId, String gNom, Client clId) {
        this.gId = gId;
        this.gNom = gNom;
        this.clId = clId;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getgNom() {
        return gNom;
    }

    public void setgNom(String gNom) {
        this.gNom = gNom;
    }

    public Client getClId() {
        return clId;
    }

    public void setClId(Client clId) {
        this.clId = clId;
    }

    public Integer getGId() {
        return gId;
    }

    public void setGId(Integer gId) {
        this.gId = gId;
    }

    public String getGNom() {
        return gNom;
    }

    public void setGNom(String gNom) {
        this.gNom = gNom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gId != null ? gId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.gId == null && other.gId != null) || (this.gId != null && !this.gId.equals(other.gId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.Groupe[ gId=" + gId + " ]";
    }

}
