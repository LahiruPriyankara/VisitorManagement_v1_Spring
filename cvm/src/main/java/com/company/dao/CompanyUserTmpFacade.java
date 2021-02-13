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
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.common.APPUtills;
import com.company.common.ApplicationConstants;
import com.company.dto.CompanyUserTmp;
import com.company.models.CompanyUserModel;
import com.company.common.SBLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.models.UserData;

/**
 *
 * @author sits_lahirupr
 */
@Repository
public class CompanyUserTmpFacade extends AbstractFacade<CompanyUserTmp> implements CompanyUserTmpFacadeLocal {

    private static Logger Log = LogManager.getLogger(CompanyUserTmpFacade.class);

    @Autowired
    private SeqNumberGeneratorBeanLocal seqNumberGeneratorBean;

    @PersistenceContext(unitName = "irectory-ejbPU")
    private EntityManager em;

    //@Resource
    //EJBContext context;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyUserTmpFacade() {
        super(CompanyUserTmp.class);
    }

    @Override
    public Map<String, CompanyUserModel> getTempCompanyUsers(List<String> ids, String depCode) throws Exception {
        Log.info("ENTERED | CompanyUserTmpFacade.getTempCompanyUsers()");
        Map<String, CompanyUserModel> dfumsMap = new HashMap();
        List<CompanyUserTmp> dfumsList = new ArrayList<>();
        List<String> recStatusList;
        Query jql = null;
        try {
            ids = (ids != null) ? ids : new ArrayList<>();
            Log.info("MESSAGE | Ids.size() : " + ids.size() + " ,depCode : " + depCode);
            
            if (ids.isEmpty() && !APPUtills.isThisStringValid(depCode)) {//empty - empty
                //dfumsList = findAll();
                Log.info("MESSAGE | Going to fetch all pending and rejected data.");
                String hql = "SELECT tmp FROM CompanyUserTmp tmp WHERE tmp.recStatus IN :recStatus ORDER BY tmp.recStatus DESC";
                jql = em.createQuery(hql);
                
            } else if (ids.isEmpty() && APPUtills.isThisStringValid(depCode)) {//empty - not empty
                Log.info("MESSAGE | Going to fetch all pending and rejected data for sol id "+depCode);
                String hql = "SELECT tmp FROM CompanyUserTmp tmp WHERE tmp.CompanyUserDivId =:CompanyUserDivId AND tmp.recStatus IN :recStatus ORDER BY tmp.recStatus DESC";
                jql = em.createQuery(hql);
                jql.setParameter("CompanyUserDivId", depCode);
                
            } else if (!ids.isEmpty() && !APPUtills.isThisStringValid(depCode)) {//not empty -  empty
                Log.info("MESSAGE | Going to fetch all pending and rejected data for emp ids "+Arrays.asList(ids));
                String hql = "SELECT tmp FROM CompanyUserTmp tmp WHERE tmp.CompanyUserEmpId IN :CompanyUserEmpId AND tmp.recStatus IN :recStatus ORDER BY tmp.recStatus DESC";
                jql = em.createQuery(hql);
                jql.setParameter("CompanyUserEmpId", ids);
                
            } else {//not empty - not empty
                Log.info("MESSAGE | Going to fetch data for emp ids "+Arrays.asList(ids)+" and sol id "+depCode);
                String hql = "SELECT tmp FROM CompanyUserTmp tmp WHERE tmp.CompanyUserEmpId IN :CompanyUserEmpId AND tmp.recStatus IN :recStatus ORDER BY tmp.recStatus DESC";
                jql = em.createQuery(hql);
                jql.setParameter("CompanyUserEmpId", ids);

            }

            /* if (!APPUtills.isThisStringEmptyValid(depCode)) {
                dfumsList = findAll();
            } else */
           /* 
            if (ids.isEmpty()) {
                Log.info("MESSAGE | Going to fetch all pending and rejected data.");
                String hql = "SELECT tmp FROM CompanyUserTmp tmp WHERE tmp.recStatus IN :recStatus ORDER BY tmp.recStatus DESC";
                jql = em.createQuery(hql);
            } else {
                Log.info("MESSAGE | Going to fetch data for given ids.");
                String hql = "SELECT tmp FROM CompanyUserTmp tmp WHERE tmp.CompanyUserEmpId IN :CompanyUserEmpId AND tmp.recStatus IN :recStatus ORDER BY tmp.recStatus DESC";
                jql = em.createQuery(hql);
                jql.setParameter("CompanyUserEmpId", ids);

            }*/
            recStatusList = Arrays.asList(ApplicationConstants.RECORD_STATUS_PENDING, ApplicationConstants.RECORD_STATUS_REJECT);
            jql.setParameter("recStatus", recStatusList);
            Log.info("JQL     | " + jql.toString());
            dfumsList = jql.getResultList();

            Log.info("MESSAGE | dfumsTemp.size() : " + dfumsList.size());

            // Converting to PayModeHelper list
            dfumsMap = objectToModel(dfumsList);
        } catch (Exception ex) {
            Log.error("ERROR   | Unable to fetch list of object from temp table." + ex.getMessage(), ex);
            throw new SBLException("Unable to fetch data.");
        }
        Log.info("LEFT    | CompanyUserTmpFacade.getTempCompanyUsers()");
        return dfumsMap;
    }

    public Map<String, CompanyUserModel> objectToModel(List<CompanyUserTmp> dfumsTempList) throws Exception {
        Log.info("ENTERED | CompanyUserTmpFacade.objectToModel()");
        Map<String, CompanyUserModel> dfumsMap = new HashMap();
        try {
            dfumsTempList = dfumsTempList != null ? dfumsTempList : new ArrayList<>();
            for (CompanyUserTmp dfumsTemp : dfumsTempList) {
                dfumsMap.put(dfumsTemp.getCompanyUserEmpId(), CompanyUserModel.objectToModel(dfumsTemp, ApplicationConstants.TEMP_DATA));
            }
        } catch (Exception ex) {
            Log.error("ERROR   | Unable to convert temp to model." + ex.getMessage(), ex);
            throw new SBLException("Object Conversion error.Please try again.");
        }
        Log.info("LEFT    | CompanyUserTmpFacade.objectToModel()");
        return dfumsMap;
    }

    @Override
    public boolean saveEmp(List<CompanyUserModel> CompanyUserModelList, UserData userData) throws Exception {
        Log.info("ENTERED | CompanyUserTmpFacade.saveEmp()");
        boolean isSuccess = false;
        try {
            for (CompanyUserModel CompanyUserModel : CompanyUserModelList) {
                CompanyUserModel.setModifiedBy(Integer.parseInt(userData.getUSER_ID()));
                CompanyUserModel.setModifiedDate(APPUtills.getCurrentDate());
                CompanyUserModel.setRecStatus(ApplicationConstants.RECORD_STATUS_PENDING);

                if (CompanyUserModel.getCompanyUserTmpId() == 0) {
                    //CompanyUserModel.setCompanyUserTmpId(seqNumberGeneratorBean.getNextCompanyUserTempId());
                    CompanyUserModel.setCreatedBy(Integer.parseInt(userData.getUSER_ID()));
                    CompanyUserModel.setCreatedDate(APPUtills.getCurrentDate());
                    Log.info("MESSAGE | CREATE "+CompanyUserModel.toString());
                    create((CompanyUserTmp) CompanyUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
                } else {
                    Log.info("MESSAGE | MODIFY "+CompanyUserModel.toString());
                    edit((CompanyUserTmp) CompanyUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
                }

            }
            isSuccess = true;
        } catch (Exception ex) {
            //context.setRollbackOnly();
            Log.error("ERROR   | Unable to save temp data." + ex.getMessage(), ex);
            throw new SBLException("Unable to save temp data.");
        }
        Log.info("LEFT    | CompanyUserTmpFacade.saveEmp()");
        return isSuccess;
    }

    @Override
    public boolean rejectEmp(List<CompanyUserModel> CompanyUserModelList) throws Exception {
        Log.info("ENTERED | CompanyUserTmpFacade.rejectEmp()");
        boolean isSuccess = false;
        try {
            for (CompanyUserModel CompanyUserModel : CompanyUserModelList) {
                edit((CompanyUserTmp) CompanyUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
            }
            isSuccess = true;
        } catch (Exception ex) {
            //context.setRollbackOnly();
            Log.error("ERROR   | Unable to reject temp data." + ex.getMessage(), ex);
            throw new SBLException("Unable to reject temp data.");
        }
        Log.info("LEFT    | CompanyUserTmpFacade.rejectEmp()");
        return isSuccess;
    }
    
    @Override
    public boolean deleteEmp(List<CompanyUserModel> CompanyUserModelList) throws Exception {
        Log.info("ENTERED | CompanyUserTmpFacade.deleteEmp()");
        boolean isSuccess = false;
        try {
            for (CompanyUserModel CompanyUserModel : CompanyUserModelList) {
                remove((CompanyUserTmp) CompanyUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
            }
            isSuccess = true;
        } catch (Exception ex) {
            //context.setRollbackOnly();
            Log.error("ERROR   | Unable to delete temp data." + ex.getMessage(), ex);
            throw new SBLException("Unable to delete temp data.");
        }
        Log.info("LEFT    | CompanyUserTmpFacade.deleteEmp()");
        return isSuccess;
    }

    @Override
    public boolean verifyEmp(List<CompanyUserModel> CompanyUserModelList) throws Exception {
        Log.info("ENTERED | CompanyUserTmpFacade.verifyEmp()");
        boolean isSuccess = false;
        try {
            for (CompanyUserModel CompanyUserModel : CompanyUserModelList) {
                edit((CompanyUserTmp) CompanyUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
            }
            isSuccess = true;
        } catch (Exception ex) {
            //context.setRollbackOnly();
            Log.error("ERROR   | Unable to verify temp data." + ex.getMessage(), ex);
            throw new SBLException("Unable to verify temp data.");
        }
        Log.info("LEFT    | CompanyUserTmpFacade.verifyEmp()");
        return isSuccess;
    }
}
