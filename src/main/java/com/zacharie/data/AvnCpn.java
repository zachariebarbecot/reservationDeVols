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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zacharie
 */
@Entity
@Table(name = "avn_cpn")
@XmlRootElement
@NamedQueries({
     @NamedQuery(name = "AvnCpn.findByCpn", query = "SELECT a FROM AvnCpn a WHERE a.cpnId= :cpnId"),
    @NamedQuery(name = "AvnCpn.findAll", query = "SELECT a FROM AvnCpn a"),
    @NamedQuery(name = "AvnCpn.findByAvnCpnId", query = "SELECT a FROM AvnCpn a WHERE a.avnCpnId = :avnCpnId")})
public class AvnCpn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "avn_cpn_id")
    private Integer avnCpnId;
    @JoinColumn(name = "avn_id", referencedColumnName = "avn_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Avion avnId;
    @JoinColumn(name = "cpn_id", referencedColumnName = "cpn_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Compagnie cpnId;
    @OneToMany(mappedBy = "avnCpnId", fetch = FetchType.EAGER)
    private Collection<VolAvn> volAvnCollection;

    public AvnCpn() {
    }

    public AvnCpn(Integer avnCpnId) {
        this.avnCpnId = avnCpnId;
    }

    public Integer getAvnCpnId() {
        return avnCpnId;
    }

    public void setAvnCpnId(Integer avnCpnId) {
        this.avnCpnId = avnCpnId;
    }

    public Avion getAvnId() {
        return avnId;
    }

    public void setAvnId(Avion avnId) {
        this.avnId = avnId;
    }

    public Compagnie getCpnId() {
        return cpnId;
    }

    public void setCpnId(Compagnie cpnId) {
        this.cpnId = cpnId;
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
        hash += (avnCpnId != null ? avnCpnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvnCpn)) {
            return false;
        }
        AvnCpn other = (AvnCpn) object;
        if ((this.avnCpnId == null && other.avnCpnId != null) || (this.avnCpnId != null && !this.avnCpnId.equals(other.avnCpnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.AvnCpn[ avnCpnId=" + avnCpnId + " ]";
    }
    
}
