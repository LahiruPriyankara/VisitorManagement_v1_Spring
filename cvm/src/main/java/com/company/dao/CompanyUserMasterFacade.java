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
import com.company.dto.CompanyUserMaster;
import com.company.models.CompanyUserModel;
import com.company.common.APPUtills;
import com.company.common.SBLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sits_lahirupr
 */
@Repository
public class CompanyUserMasterFacade extends AbstractFacade<CompanyUserMaster> implements CompanyUserMasterFacadeLocal {

    private static Logger dedLog = LogManager.getLogger(CompanyUserTmpFacade.class);

    @PersistenceContext(unitName = "DEDirectory-ejbPU")
    private EntityManager em;

    //@Resource
    //EJBContext context;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyUserMasterFacade() {
        super(CompanyUserMaster.class);
    }

    @Autowired
    private SeqNumberGeneratorBeanLocal seqNumberGeneratorBean;

    @Override
    public Map<String, CompanyUserModel> getMasterCompanyUsers(List<String> ids, String depCode) throws Exception {
        dedLog.info("ENTERED | CompanyUserMasterFacade.getMasterCompanyUsers()");
        Map<String, CompanyUserModel> dfumsMap = new HashMap();
        List<CompanyUserMaster> dfumsList = new ArrayList<>();
        Query jql = null;
        try {
            dedLog.info("MESSAGE | ids : "+ids+" ,depCode : "+depCode);
            ids = (ids != null) ? ids : new ArrayList<>();
            
            if (ids.isEmpty() && !APPUtills.isThisStringValid(depCode)) { // empty - depCode empty
                dedLog.info("MESSAGE | Going to fetch all master data");
                dfumsList = findAll();
            } else if (ids.isEmpty() && APPUtills.isThisStringValid(depCode)) {// empty -  not empty
                //dfumsList = findAll();
                dedLog.info("MESSAGE | Going to fetch all master data for sol id "+depCode);
                String hql = "SELECT tbl FROM CompanyUserMaster tbl WHERE tbl.CompanyUserDivId=:CompanyUserDivId ORDER BY tbl.CompanyUserEmpId DESC";
                jql = em.createQuery(hql);
                jql.setParameter("CompanyUserDivId", depCode);

                dedLog.info("JQL     | " + jql.toString());
                dfumsList = jql.getResultList();
            } else if (!ids.isEmpty() && !APPUtills.isThisStringValid(depCode)) {// not empty -  empty
                //dfumsList = findAll();
                dedLog.info("MESSAGE | Going to fetch all master data for emp ids "+Arrays.asList(ids));
                String hql = "SELECT tbl FROM CompanyUserMaster tbl WHERE tbl.CompanyUserEmpId IN :CompanyUserEmpId ORDER BY tbl.CompanyUserEmpId DESC";
                jql = em.createQuery(hql);
                jql.setParameter("CompanyUserEmpId", ids);

                dedLog.info("JQL     | " + jql.toString());
                dfumsList = jql.getResultList();
            } else {// not empty - not empty
                dedLog.info("MESSAGE | Going to fetch all master data for given employee emp ids "+Arrays.asList(ids)+" sol id "+depCode);
                String hql = "SELECT tbl FROM CompanyUserMaster tbl WHERE tbl.CompanyUserEmpId IN :CompanyUserEmpId AND tbl.CompanyUserDivId=:CompanyUserDivId ORDER BY tbl.CompanyUserEmpId DESC";
                jql = em.createQuery(hql);
                jql.setParameter("CompanyUserEmpId", ids);
                jql.setParameter("CompanyUserDivId", depCode);

                dedLog.info("JQL     | " + jql.toString());
                dfumsList = jql.getResultList();
            }

            dedLog.info("MESSAGE | dfumsList.size() : " + dfumsList.size());

            // Converting to PayModeHelper list
            dfumsMap = objectToModel(dfumsList);
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to fetch list of object from master table." + ex.getMessage(), ex);
            throw new SBLException("Unable to fetch data.");
        }
        dedLog.info("LEFT    | CompanyUserMasterFacade.getMasterCompanyUsers()");
        return dfumsMap;
    }

    public Map<String, CompanyUserModel> objectToModel(List<CompanyUserMaster> dfumsTempList) throws Exception {
        dedLog.info("ENTERED | CompanyUserMasterFacade.objectToModel()");
        Map<String, CompanyUserModel> dfumsMap = new HashMap();
        CompanyUserModel model;
        try {
            dfumsTempList = dfumsTempList != null ? dfumsTempList : new ArrayList<>();
            for (CompanyUserMaster dfumsMaster : dfumsTempList) {
                model = CompanyUserModel.objectToModel(dfumsMaster, ApplicationConstants.MASTER_DATA);
                model.setActionType(ApplicationConstants.ACTION_TYPE_MODIFY);
                dfumsMap.put(dfumsMaster.getCompanyUserEmpId(), model);
            }
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to convert master to model." + ex.getMessage(), ex);
            throw new SBLException("Object Conversion error.Please try again.");
        }
        dedLog.info("LEFT    | CompanyUserMasterFacade.objectToModel()");
        return dfumsMap;
    }

    @Override
    public boolean verifyEmp(List<CompanyUserModel> CompanyUserModelList) throws Exception {
        dedLog.info("ENTERED | CompanyUserMasterFacade.verifyEmp()");
        boolean isSuccess = false;
        try {
            dedLog.info("MESSAGE | CompanyUserModelList.size() : "+CompanyUserModelList.size());
            for (CompanyUserModel CompanyUserModel : CompanyUserModelList) {

                if (CompanyUserModel.getActionType().equalsIgnoreCase(ApplicationConstants.ACTION_TYPE_MODIFY)) {
                    dedLog.info("MESSAGE | EDIT => Saving modify record : " + CompanyUserModel.toString());
                    edit((CompanyUserMaster) CompanyUserModel.modelToObject(ApplicationConstants.MASTER_DATA));
                } else {
                    dedLog.info("MESSAGE | CREATE => Saving new record : " + CompanyUserModel.toString());
                    //CompanyUserModel.setCompanyUserMasterId(seqNumberGeneratorBean.getNextCompanyUserMasterId());
                    create((CompanyUserMaster) CompanyUserModel.modelToObject(ApplicationConstants.MASTER_DATA));
                }

            }
            isSuccess = true;
        } catch (Exception ex) {
            //context.setRollbackOnly();
            dedLog.error("ERROR   | Unable to verify temp data." + ex.getMessage(), ex);
            throw new SBLException("Unable to verify temp data.");
        }
        dedLog.info("LEFT    | CompanyUserMasterFacade.verifyEmp()");
        return isSuccess;
    }

}
