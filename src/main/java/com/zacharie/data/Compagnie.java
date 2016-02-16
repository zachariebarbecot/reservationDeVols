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
@Table(name = "compagnie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compagnie.findAll", query = "SELECT c FROM Compagnie c"),
    @NamedQuery(name = "Compagnie.findByCpnId", query = "SELECT c FROM Compagnie c WHERE c.cpnId = :cpnId"),
    @NamedQuery(name = "Compagnie.findByCpnNom", query = "SELECT c FROM Compagnie c WHERE c.cpnNom = :cpnNom"),
    @NamedQuery(name = "Compagnie.findByCpnEmail", query = "SELECT c FROM Compagnie c WHERE c.cpnEmail = :cpnEmail")})
public class Compagnie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cpn_id")
    private Integer cpnId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "cpn_nom")
    private String cpnNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cpn_email")
    private String cpnEmail;
    @OneToMany(mappedBy = "cpnId", fetch = FetchType.EAGER)
    private Collection<Adresse> adresseCollection;
    @OneToMany(mappedBy = "cpnId", fetch = FetchType.EAGER)
    private Collection<Telephone> telephoneCollection;
    @OneToMany(mappedBy = "cpnId", fetch = FetchType.EAGER)
    private Collection<AvnCpn> avnCpnCollection;

    public Compagnie() {
    }

    public Compagnie(Integer cpnId) {
        this.cpnId = cpnId;
    }

    public Compagnie(Integer cpnId, String cpnNom, String cpnEmail) {
        this.cpnId = cpnId;
        this.cpnNom = cpnNom;
        this.cpnEmail = cpnEmail;
    }

    public Integer getCpnId() {
        return cpnId;
    }

    public void setCpnId(Integer cpnId) {
        this.cpnId = cpnId;
    }

    public String getCpnNom() {
        return cpnNom;
    }

    public void setCpnNom(String cpnNom) {
        this.cpnNom = cpnNom;
    }

    public String getCpnEmail() {
        return cpnEmail;
    }

    public void setCpnEmail(String cpnEmail) {
        this.cpnEmail = cpnEmail;
    }

    @XmlTransient
    public Collection<Adresse> getAdresseCollection() {
        return adresseCollection;
    }

    public void setAdresseCollection(Collection<Adresse> adresseCollection) {
        this.adresseCollection = adresseCollection;
    }

    @XmlTransient
    public Collection<Telephone> getTelephoneCollection() {
        return telephoneCollection;
    }

    public void setTelephoneCollection(Collection<Telephone> telephoneCollection) {
        this.telephoneCollection = telephoneCollection;
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
        hash += (cpnId != null ? cpnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compagnie)) {
            return false;
        }
        Compagnie other = (Compagnie) object;
        if ((this.cpnId == null && other.cpnId != null) || (this.cpnId != null && !this.cpnId.equals(other.cpnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.Compagnie[ cpnId=" + cpnId + " ]";
    }

}
