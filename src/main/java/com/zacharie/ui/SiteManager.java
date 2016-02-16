/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zacharie.ui;

import com.zacharie.data.Site;
import com.zacharie.facade.SiteFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Zacharie
 */
@Named(value = "siteManager")
@ApplicationScoped
public class SiteManager implements Serializable {

    private Site s;
    @EJB
    private SiteFacade sFacade;

    public SiteManager() {
    }

    @PostConstruct
    public void init() {
        this.s = this.sFacade.find(1);
    }

    public Site getS() {
        return s;
    }

    public void setS(Site s) {
        this.s = s;
    }

    public SiteFacade getsFacade() {
        return sFacade;
    }

    public void setsFacade(SiteFacade sFacade) {
        this.sFacade = sFacade;
    }

}
