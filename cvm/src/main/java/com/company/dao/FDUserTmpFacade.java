/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.common.ApplicationConstants;
import com.company.dto.FdUserTmp;
import com.company.models.FdUserModel;
import com.company.common.SBLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sits_lahirupr
 */
@Repository("fDUserTmpFacade")
public class FDUserTmpFacade extends AbstractFacade<FdUserTmp> implements FDUserTmpFacadeLocal {

    private static Logger dedLog = LogManager.getLogger(FDUserTmpFacade.class);

    @PersistenceContext(unitName = "DEDirectory-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FDUserTmpFacade() {
        super(FdUserTmp.class);
    }

    @Override
    public Map<Integer, FdUserModel> getTempFdUsers(List<Integer> ids, String tableType) throws Exception {
        dedLog.info("ENTERED | DedFDUserTmpFacade.getTempFdUsers()");
        Map<Integer, FdUserModel> dfumsMap = new HashMap();
        List<FdUserTmp> dfumsList = new ArrayList<>();
        List<String> recStatusList;
        Query jql = null;
        try {
            ids = (ids != null) ? ids : new ArrayList<>();
            dedLog.info("MESSAGE | Ids.size() : " + ids.size());
            if (tableType.equalsIgnoreCase(ApplicationConstants.TEMP_DATA)) {
                if (ids.isEmpty()) {
                    recStatusList = Arrays.asList(ApplicationConstants.RECORD_STATUS_PENDING, ApplicationConstants.RECORD_STATUS_REJECT);
                    String hql = "SELECT tmp FROM FdUserTmp tmp WHERE tmp.recStatus IN :recStatus OR tmp.securepassUserStatus = :securepassUserStatus ORDER BY tmp.recStatus DESC";
                    jql = em.createQuery(hql);
                    jql.setParameter("recStatus", recStatusList);
                    jql.setParameter("securepassUserStatus", ApplicationConstants.SECUREPASS_USER_SET_PASSWORD);
                } else {
                    String hql = "SELECT tmp FROM FdUserTmp tmp WHERE tmp.fdUserTmpId IN :fdUserTmpId";
                    jql = em.createQuery(hql);
                    jql.setParameter("fdUserTmpId", ids);
                }

            } else if (tableType.equalsIgnoreCase(ApplicationConstants.MASTER_DATA)) {
                if (ids.isEmpty()) {
                    throw new SBLException("Recived Empty userId.");
                } else {
                    recStatusList = Arrays.asList(ApplicationConstants.RECORD_STATUS_PENDING, ApplicationConstants.RECORD_STATUS_REJECT);
                    String hql = "SELECT tmp FROM FdUserTmp tmp WHERE tmp.fdUserMasterId IN :fdUserMasterIds AND tmp.recStatus IN :recStatus";
                    jql = em.createQuery(hql);
                    jql.setParameter("fdUserMasterIds", ids);
                    jql.setParameter("recStatus", recStatusList);
                }
            }

            dedLog.info("JQL     | " + jql.toString());

            dedLog.info("JQL     | " + jql.toString());

            dfumsList = jql.getResultList();
            dedLog.info("MESSAGE | dfumsTemp.size() : " + dfumsList.size());

            // Converting to PayModeHelper list
            dfumsMap = objectToModel(dfumsList);
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to fetch list of object from temp table." + ex.getMessage(), ex);
            throw new SBLException("Unable to fetch data.");
        }
        dedLog.info("LEFT    | DedFDUserTmpFacade.getTempFdUsers()");
        return dfumsMap;
    }

    public Map<Integer, FdUserModel> objectToModel(List<FdUserTmp> dfumsTempList) throws Exception {
        dedLog.info("ENTERED    | DedFDUserTmpFacade.objectToModel()");
        FdUserModel dfum = null;
        Map<Integer, FdUserModel> dfumsMap = new HashMap();
        try {
            dfumsTempList = dfumsTempList != null ? dfumsTempList : new ArrayList<>();
            for (FdUserTmp dfumsTemp : dfumsTempList) {
                dfumsMap.put(dfumsTemp.getFdUserTmpId(), FdUserModel.objectToModel(dfumsTemp, ApplicationConstants.TEMP_DATA));
            }
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to convert temp to model." + ex.getMessage(), ex);
            throw new SBLException("Object Conversion error.Please try again.");
        }
        dedLog.info("LEFT    | DedFDUserTmpFacade.objectToModel()");
        return dfumsMap;
    }
}
