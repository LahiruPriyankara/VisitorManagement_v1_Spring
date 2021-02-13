/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.company.dto.UserReference;

/**
 *
 * @author sits_lahirupr
 */
@Repository("userReferenceFacade")
public class UserReferenceFacade extends AbstractFacade<UserReference> implements UserReferenceFacadeLocal {

    private static Logger dedLog = LogManager.getLogger(UserReferenceFacade.class);

    @PersistenceContext(unitName = "DEDirectory-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserReferenceFacade() {
        super(UserReference.class);
    }

    @Override
    public UserReference getReferenceByUserId(int uId) throws Exception {
        dedLog.info("ENTERED | DedUserReferenceFacade.getReferenceByUserId()");
        UserReference dur = null;
        Query jql = null;
        try {
            dedLog.info("MESSAGE | UserId : " + uId);
            String hql = "SELECT ur FROM UserReference ur WHERE ur.userId = :userId";
            jql = em.createQuery(hql);
            jql.setParameter("userId", uId);
            dedLog.info("JQL     | " + jql.toString());

            dur = (UserReference) jql.getSingleResult();
        } catch (Exception ex) {
            dedLog.error("ERROR   | No match data in reference table.");
            //throw new SBLException("Unable to fetch data.");
        }
        dedLog.info("LEFT    | DedUserReferenceFacade.getReferenceByUserId()");
        return dur;
    }
}
