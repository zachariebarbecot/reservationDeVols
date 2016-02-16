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
@Table(name = "avion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avion.findAll", query = "SELECT a FROM Avion a"),
    @NamedQuery(name = "Avion.findByAvnId", query = "SELECT a FROM Avion a WHERE a.avnId = :avnId"),
    @NamedQuery(name = "Avion.findByAvnType", query = "SELECT a FROM Avion a WHERE a.avnType = :avnType"),
    @NamedQuery(name = "Avion.findByAvnCapacite", query = "SELECT a FROM Avion a WHERE a.avnCapacite = :avnCapacite")})
public class Avion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "avn_id")
    private Integer avnId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "avn_type")
    private String avnType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "avn_capacite")
    private int avnCapacite;
    @OneToMany(mappedBy = "avnId", fetch = FetchType.EAGER)
    private Collection<AvnCpn> avnCpnCollection;

    public Avion() {
    }

    public Avion(Integer avnId) {
        this.avnId = avnId;
    }

    public Avion(Integer avnId, String avnType, int avnCapacite) {
        this.avnId = avnId;
        this.avnType = avnType;
        this.avnCapacite = avnCapacite;
    }

    public Integer getAvnId() {
        return avnId;
    }

    public void setAvnId(Integer avnId) {
        this.avnId = avnId;
    }

    public String getAvnType() {
        return avnType;
    }

    public void setAvnType(String avnType) {
        this.avnType = avnType;
    }

    public int getAvnCapacite() {
        return avnCapacite;
    }

    public void setAvnCapacite(int avnCapacite) {
        this.avnCapacite = avnCapacite;
    }

    @XmlTransient
    public Collection<AvnCpn> getAvnCpnCollection() {
        return avnCpnCollection;
    }

    public void setAvnCpnCollection(Collection<AvnCpn> avnCpnCollection) {
        this.avnCpnCollection = avnCpnCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (avnId != null ? avnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avion)) {
            return false;
        }
        Avion other = (Avion) object;
        if ((this.avnId == null && other.avnId != null) || (this.avnId != null && !this.avnId.equals(other.avnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.Avion[ avnId=" + avnId + " ]";
    }
    
}
