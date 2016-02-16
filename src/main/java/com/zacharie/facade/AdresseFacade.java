/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.facade;

import com.zacharie.data.Adresse;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Zacharie
 */
@Stateless
public class AdresseFacade extends AbstractFacade<Adresse> {
    @PersistenceContext(unitName = "reservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdresseFacade() {
        super(Adresse.class);
    }
    
    public List<Adresse> findByAdr(int numero, String rue, int zip, String ville) {
        List<Adresse> adrL = this.em.createNamedQuery("Adresse.findByAdr", Adresse.class).setParameter("adrNumero", numero).setParameter("adrRue", rue).setParameter("adrZip", zip).setParameter("adrVille", ville).getResultList();
        return adrL;
    }
    
}
