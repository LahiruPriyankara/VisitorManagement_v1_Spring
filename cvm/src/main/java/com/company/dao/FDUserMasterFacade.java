/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.common.ApplicationConstants;
import com.company.dto.FdUserMaster;
import com.company.models.FdUserModel;
import com.company.common.SBLException;
import org.springframework.stereotype.Repository;

@Repository("fDUserTmpFacade")
public class FDUserMasterFacade extends AbstractFacade<FdUserMaster> implements FDUserMasterFacadeLocal {

    private static Logger dedLog = LogManager.getLogger(FDUserMasterFacade.class);

    @PersistenceContext(unitName = "DEDirectory-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FDUserMasterFacade() {
        super(FdUserMaster.class);
    }

    @Override
    public Map<Integer, FdUserModel> getAllFdUsersByUserIds(List<Integer> ids) throws Exception {
        dedLog.info("ENTERED | DedFDUserMasterFacade.getFdUsers()");
        Map<Integer, FdUserModel> dfumsMap = new HashMap();
        List<FdUserMaster> dfumsList = new ArrayList<>();
        Query jql = null;
        try {
            ids = (ids != null) ? ids : new ArrayList<>();
            dedLog.info("MESSAGE | Ids.size() : " + ids.size());
            if (ids.isEmpty()) {
                //String hql = "SELECT tbl FROM FdUserMaster tbl WHERE tbl.userStatus = :userStatus";
                //jql.setParameter("userStatus", ApplicationConstants.STATUS_ACTIVE);
                String hql = "SELECT tbl FROM FdUserMaster tbl ORDER BY tbl.userStatus";
                jql = em.createQuery(hql);
            } else {
                String hql = "SELECT tbl FROM FdUserMaster tbl WHERE tbl.fdUserMasterId IN :fdUserMasterId";
                jql = em.createQuery(hql);
                jql.setParameter("fdUserMasterId", ids);
            }
            dedLog.info("JQL     | " + jql.toString());
            dfumsList = jql.getResultList();
            dedLog.info("MESSAGE | dfumsList.size() : " + dfumsList.size());

            // Converting to PayModeHelper list
            dfumsMap = objectToModel(dfumsList);
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to fetch list of object from temp table." + ex.getMessage(), ex);
            throw new SBLException("Unable to fetch data.");
        }
        dedLog.info("LEFT    | DedFDUserMasterFacade.getFdUsers()");
        return dfumsMap;
    }

    @Override
    public Map<Integer, FdUserModel> getFdUserByUserName(List<String> getFdUserByUserNames) throws Exception {
        dedLog.info("ENTERED | DedFDUserMasterFacade.getFdUsers()");
        Map<Integer, FdUserModel> dfumsMap = new HashMap();
        List<FdUserMaster> dfumsList = new ArrayList<>();
        List<String> userNames;
        Query jql = null;
        try {
            userNames = (getFdUserByUserNames != null) ? getFdUserByUserNames : new ArrayList<>();
            dedLog.info("MESSAGE | Ids.size() : " + getFdUserByUserNames.size());

            if (userNames.size() == 0) {
                throw new SBLException("Recived Empty user names.");
            } else {
                String hql = "SELECT tbl FROM FdUserMaster tbl WHERE tbl.fdUserName IN :fdUserName";
                jql = em.createQuery(hql);
                jql.setParameter("fdUserName", userNames);
            }
            dedLog.info("JQL     | " + jql.toString());

            dfumsList = jql.getResultList();
            dedLog.info("MESSAGE | dfumsTemp.size() : " + dfumsList.size());

            // Converting to PayModeHelper list
            dfumsMap = objectToModel(dfumsList);
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to fetch list of object from temp table." + ex.getMessage(), ex);
            throw new SBLException("Unable to fetch data.");
        }
        dedLog.info("LEFT    | DedFDUserMasterFacade.getFdUsers()");
        return dfumsMap;
    }

    public Map<Integer, FdUserModel> objectToModel(List<FdUserMaster> dfumsTempList) throws Exception {
        dedLog.info("ENTERED | DedFDUserMasterFacade.objectToModel()");
        Map<Integer, FdUserModel> dfumsMap = new HashMap();
        try {
            dfumsTempList = dfumsTempList != null ? dfumsTempList : new ArrayList<>();
            for (FdUserMaster dfumsMaster : dfumsTempList) {
                dfumsMap.put(dfumsMaster.getFdUserMasterId(), FdUserModel.objectToModel(dfumsMaster, ApplicationConstants.MASTER_DATA));
            }
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to convert master to model." + ex.getMessage(), ex);
            throw new SBLException("Object Conversion error.Please try again.");
        }
        dedLog.info("LEFT    | DedFDUserMasterFacade.objectToModel()");
        return dfumsMap;
    }
}
