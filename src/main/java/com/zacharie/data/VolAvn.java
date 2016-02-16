/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zacharie
 */
@Entity
@Table(name = "vol_avn")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VolAvn.findAll", query = "SELECT v FROM VolAvn v"),
    @NamedQuery(name = "VolAvn.findByVolAvnId", query = "SELECT v FROM VolAvn v WHERE v.volAvnId = :volAvnId"),
    @NamedQuery(name = "VolAvn.findByVolAvnDated", query = "SELECT v FROM VolAvn v WHERE v.volAvnDated = :volAvnDated"),
    @NamedQuery(name = "VolAvn.findByVolAvnDatea", query = "SELECT v FROM VolAvn v WHERE v.volAvnDatea = :volAvnDatea")})
public class VolAvn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vol_avn_id")
    private Integer volAvnId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vol_avn_dated")
    @Temporal(TemporalType.DATE)
    private Date volAvnDated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vol_avn_datea")
    @Temporal(TemporalType.DATE)
    private Date volAvnDatea;
    @OneToMany(mappedBy = "volAvnId", fetch = FetchType.EAGER)
    private Collection<Reservation> reservationCollection;
    @JoinColumn(name = "avn_cpn_id", referencedColumnName = "avn_cpn_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private AvnCpn avnCpnId;
    @JoinColumn(name = "vol_id", referencedColumnName = "vol_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Vol volId;

    public VolAvn() {
    }

    public VolAvn(Integer volAvnId) {
        this.volAvnId = volAvnId;
    }

    public VolAvn(Integer volAvnId, Date volAvnDated, Date volAvnDatea) {
        this.volAvnId = volAvnId;
        this.volAvnDated = volAvnDated;
        this.volAvnDatea = volAvnDatea;
    }

    public Integer getVolAvnId() {
        return volAvnId;
    }

    public void setVolAvnId(Integer volAvnId) {
        this.volAvnId = volAvnId;
    }

    public Date getVolAvnDated() {
        return volAvnDated;
    }

    public void setVolAvnDated(Date volAvnDated) {
        this.volAvnDated = volAvnDated;
    }

    public Date getVolAvnDatea() {
        return volAvnDatea;
    }

    public void setVolAvnDatea(Date volAvnDatea) {
        this.volAvnDatea = volAvnDatea;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    public AvnCpn getAvnCpnId() {
        return avnCpnId;
    }

    public void setAvnCpnId(AvnCpn avnCpnId) {
        this.avnCpnId = avnCpnId;
    }

    public Vol getVolId() {
        return volId;
    }

    public void setVolId(Vol volId) {
        this.volId = volId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (volAvnId != null ? volAvnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VolAvn)) {
            return false;
        }
        VolAvn other = (VolAvn) object;
        if ((this.volAvnId == null && other.volAvnId != null) || (this.volAvnId != null && !this.volAvnId.equals(other.volAvnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.VolAvn[ volAvnId=" + volAvnId + " ]";
    }
    
}
