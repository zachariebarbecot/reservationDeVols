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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zacharie
 */
@Entity
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByClId", query = "SELECT c FROM Client c WHERE c.clId = :clId"),
    @NamedQuery(name = "Client.findByClPrenom", query = "SELECT c FROM Client c WHERE c.clPrenom = :clPrenom"),
    @NamedQuery(name = "Client.findByClNom", query = "SELECT c FROM Client c WHERE c.clNom = :clNom"),
    @NamedQuery(name = "Client.findByClEmail", query = "SELECT c FROM Client c WHERE c.clEmail = :clEmail"),
    @NamedQuery(name = "Client.findByClPassword", query = "SELECT c FROM Client c WHERE c.clPassword = :clPassword")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cl_id")
    private Integer clId;
    @Basic(optional = false)
    @NotNull(message = "Champ prénom vide")
    @Size(min = 1, max = 25)
    @Column(name = "cl_prenom")
    private String clPrenom;
    @Basic(optional = false)
    @NotNull(message = "Champ nom vide")
    @Size(min = 1, max = 32)
    @Column(name = "cl_nom")
    private String clNom;
    @Basic(optional = false)
    @NotNull(message = "Champ email vide")
    @Pattern(regexp = "([^.@]+)(\\.[^.@+]+)*@([^.@]+\\.)+([^.@]+)", message = "Email non valide")
    @Size(min = 1, max = 100)
    @Column(name = "cl_email")
    private String clEmail;
    @Basic(optional = false)
    @NotNull(message = "Champ password vide")
    @Size(min = 1, max = 255)
    @Column(name = "cl_password")
    private String clPassword;
    @OneToMany(mappedBy = "clId", fetch = FetchType.EAGER)
    private Collection<Telephone> telephoneCollection;
    @OneToMany(mappedBy = "clId", fetch = FetchType.EAGER)
    private Collection<Adresse> adresseCollection;
    @OneToMany(mappedBy = "clId", fetch = FetchType.EAGER)
    private Collection<Reservation> reservationCollection;
    @OneToMany(mappedBy = "clId", fetch = FetchType.EAGER)
    private Collection<Groupe> groupeCollection;

    public Client() {
    }

    public Client(Integer clId) {
        this.clId = clId;
    }

    public Client(Integer clId, String clPrenom, String clNom, String clEmail, String clPassword) {
        this.clId = clId;
        this.clPrenom = clPrenom;
        this.clNom = clNom;
        this.clEmail = clEmail;
        this.clPassword = clPassword;
    }

    public Integer getClId() {
        return clId;
    }

    public void setClId(Integer clId) {
        this.clId = clId;
    }

    public String getClPrenom() {
        return clPrenom;
    }

    public void setClPrenom(String clPrenom) {
        this.clPrenom = clPrenom;
    }

    public String getClNom() {
        return clNom;
    }

    public void setClNom(String clNom) {
        this.clNom = clNom;
    }

    public String getClEmail() {
        return clEmail;
    }

    public void setClEmail(String clEmail) {
        this.clEmail = clEmail;
    }

    public String getClPassword() {
        return clPassword;
    }

    public void setClPassword(String clPassword) {
        this.clPassword = clPassword;
    }

    @XmlTransient
    public Collection<Telephone> getTelephoneCollection() {
        return telephoneCollection;
    }

    public void setTelephoneCollection(Collection<Telephone> telephoneCollection) {
        this.telephoneCollection = telephoneCollection;
    }

    @XmlTransient
    public Collection<Adresse> getAdresseCollection() {
        return adresseCollection;
    }

    public void setAdresseCollection(Collection<Adresse> adresseCollection) {
        this.adresseCollection = adresseCollection;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @XmlTransient
    public Collection<Groupe> getGroupeCollection() {
        return groupeCollection;
    }

    public void setGroupeCollection(Collection<Groupe> groupeCollection) {
        this.groupeCollection = groupeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clId != null ? clId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clId == null && other.clId != null) || (this.clId != null && !this.clId.equals(other.clId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zacharie.data.Client[ clId=" + clId + " ]";
    }

}
