/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zacharie
 */
@Entity
@Table(name = "vol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vol.findAll", query = "SELECT v FROM Vol v"),
    @NamedQuery(name = "Vol.findByVolId", query = "SELECT v FROM Vol v WHERE v.volId = :volId"),
    @NamedQuery(name = "Vol.findByVolDepart", query = "SELECT v FROM Vol v WHERE v.volDepart = :volDepart"),
    @NamedQuery(name = "Vol.findByVolArrivee", query = "SELECT v FROM Vol v WHERE v.volArrivee = :volArrivee"),
    @NamedQuery(name = "Vol.findByVolPrix", query = "SELECT v FROM Vol v WHERE v.volPrix = :volPrix")})
public class Vol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vol_id")
    private Integer volId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "vol_depart")
    private String volDepart;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "vol_arrivee")
    private String volArrivee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vol_prix")
    private int volPrix;
    @OneToMany(mappedBy = "volId", fetch = FetchType.EAGER)
    private Collection<VolAvn> volAvnCollection;

    public Vol() {
    }

    public Vol(Integer volId) {
        this.volId = volId;
    }

    public Vol(Integer volId, String volDepart, String volArrivee, int volPrix) {
        this.volId = volId;
        this.volDepart = volDepart;
        this.volArrivee = volArrivee;
        this.volPrix = volPrix;
    }

    public Integer getVolId() {
        return volId;
    }

    public void setVolId(Integer volId) {
        this.volId = volId;
    }

    public String getVolDepart() {
        return volDepart;
    }

    public void setVolDepart(String volDepart) {
        this.volDepart = volDepart;
    }

    public String getVolArrivee() {
        return volArrivee;
    }

    public void setVolArrivee(String volArrivee) {
        this.volArrivee = volArrivee;
    }

    public int getVolPrix() {
        return volPrix;
    }

    public void setVolPrix(int volPrix) {
        this.volPrix = volPrix;
    }

    @XmlTransient
    public Collection<VolAvn> getVolAvnCollection() {
        return volAvnCollection;
    }

    public void setVolAvnCollection(Collection<VolAvn> volAvnCollection) {
        this.volAvnCollection = volAvnCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (volId != null ? volId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vol)) {
            return false;
        }
        Vol other = (Vol) object;
        if ((this.volId == null && other.volId != null) || (this.volId != null && !this.volId.equals(other.volId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.Vol[ volId=" + volId + " ]";
    }
    
}
