/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.facade;

import com.zacharie.data.Site;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Zacharie
 */
@Stateless
public class SiteFacade extends AbstractFacade<Site> {
    @PersistenceContext(unitName = "reservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SiteFacade() {
        super(Site.class);
    }
    
}
