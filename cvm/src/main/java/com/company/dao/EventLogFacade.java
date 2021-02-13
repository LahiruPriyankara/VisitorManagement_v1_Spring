/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import com.company.dto.EventLogTbl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 *
 * @author sits_lahirupr
 */
@Repository("eventLogFacade")
public class EventLogFacade extends AbstractFacade<EventLogTbl> implements EventLogFacadeLocal {

    @PersistenceContext(unitName = "DEDirectory-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventLogFacade() {
        super(EventLogTbl.class);
    }
}
