/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.data;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zacharie
 */
@Entity
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByRsvId", query = "SELECT r FROM Reservation r WHERE r.rsvId = :rsvId"),
    @NamedQuery(name = "Reservation.findByRsvDate", query = "SELECT r FROM Reservation r WHERE r.rsvDate = :rsvDate")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rsv_id")
    private Integer rsvId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rsv_date")
    @Temporal(TemporalType.DATE)
    private Date rsvDate;
    @JoinColumn(name = "cl_id", referencedColumnName = "cl_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Client clId;
    @JoinColumn(name = "vol_avn_id", referencedColumnName = "vol_avn_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private VolAvn volAvnId;

    public Reservation() {
    }

    public Reservation(Integer rsvId) {
        this.rsvId = rsvId;
    }

    public Reservation(Integer rsvId, Date rsvDate) {
        this.rsvId = rsvId;
        this.rsvDate = rsvDate;
    }

    public Integer getRsvId() {
        return rsvId;
    }

    public void setRsvId(Integer rsvId) {
        this.rsvId = rsvId;
    }

    public Date getRsvDate() {
        return rsvDate;
    }

    public void setRsvDate(Date rsvDate) {
        this.rsvDate = rsvDate;
    }

    public Client getClId() {
        return clId;
    }

    public void setClId(Client clId) {
        this.clId = clId;
    }

    public VolAvn getVolAvnId() {
        return volAvnId;
    }

    public void setVolAvnId(VolAvn volAvnId) {
        this.volAvnId = volAvnId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rsvId != null ? rsvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.rsvId == null && other.rsvId != null) || (this.rsvId != null && !this.rsvId.equals(other.rsvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.Reservation[ rsvId=" + rsvId + " ]";
    }
    
}
