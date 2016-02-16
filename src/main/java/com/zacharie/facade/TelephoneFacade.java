/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.facade;

import com.zacharie.data.Telephone;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Zacharie
 */
@Stateless
public class TelephoneFacade extends AbstractFacade<Telephone> {

    @PersistenceContext(unitName = "reservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelephoneFacade() {
        super(Telephone.class);
    }

    public List<Telephone> findByTelNum(String telNum) {
        List<Telephone> telL = this.em.createNamedQuery("Telephone.findByTelNumero", Telephone.class).setParameter("telNumero", telNum).getResultList();
        return telL;
    }

}
