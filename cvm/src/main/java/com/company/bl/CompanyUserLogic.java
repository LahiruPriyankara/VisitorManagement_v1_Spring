/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.bl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.common.ApplicationConstants;
import com.company.dao.CompanyUserMasterFacadeLocal;
import com.company.dao.CompanyUserTmpFacadeLocal;
import com.company.dao.SeqNumberGeneratorBeanLocal;
import com.company.models.CompanyUserModel;
import com.company.models.UserData;
import com.company.common.APPUtills;
import com.company.common.SBLException;
import org.springframework.beans.factory.annotation.Autowired;


public class CompanyUserLogic implements CompanyUserLogicLocal {

    private static Logger dedLog = LogManager.getLogger(CompanyUserLogic.class);
    @Autowired
    private CompanyUserTmpFacadeLocal CompanyUserTmpFacade;
    @Autowired
    private SeqNumberGeneratorBeanLocal seqNumberGeneratorBean;
    @Autowired
    private CompanyUserMasterFacadeLocal CompanyUserMasterFacade;

    @Override
    public Map<String, CompanyUserModel> getCompanyUsers(String tableType, List<String> ids, String depCode) throws Exception {
        dedLog.info("ENTERED | FDUserLogic.getCompanyUsers()");
        Map<String, CompanyUserModel> dfumsMap = new HashMap();
        try {
            if (tableType.equalsIgnoreCase(ApplicationConstants.MASTER_DATA)) {
                dedLog.info("MESSAGE | Get from master..");
                dfumsMap = CompanyUserMasterFacade.getMasterCompanyUsers(ids, depCode);
            } else if (tableType.equalsIgnoreCase(ApplicationConstants.TEMP_DATA)) {
                dedLog.info("MESSAGE | Get from temp..");
                dfumsMap = CompanyUserTmpFacade.getTempCompanyUsers(ids, depCode);
            }

        } catch (SBLException ex) {
            dedLog.error("ERROR   | Unable to fetch data." + ex.getMessage(), ex);
            throw new SBLException(ex.getMessage());
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to fetch data." + ex.getMessage(), ex);
            throw new SBLException("Unable to fetch data.Please try again.");
        }
        dedLog.info("LEFT | FDUserLogic.getCompanyUsers()");
        return dfumsMap;
    }    

    @Override
    public boolean saveBankUser(List<CompanyUserModel> bankUserModelList,List<String> ids, UserData userData) throws Exception {
        dedLog.info("ENTERED | FDUserLogic.saveBankUser()");
        boolean isSuccess = false;
        String errorCode = "";
        try {
            Map<String, CompanyUserModel> userMap = CompanyUserTmpFacade.getTempCompanyUsers(ids, userData.getDIV_CODE());//Pending OR Reject

           /* for (CompanyUserModel bankUserModel : bankUserModelList) {
                if (userMap.get(bankUserModel.getBankUserEmpId()) != null && userMap.get(bankUserModel.getBankUserEmpId()).getRecStatus().equalsIgnoreCase(ApplicationConstants.RECORD_STATUS_PENDING) ) {
                    errorCode = errorCode.equalsIgnoreCase("") ? bankUserModel.getBankUserEmpId() : errorCode + "," + bankUserModel.getBankUserEmpId();
                }
            }*/
            if (APPUtills.isThisStringValid(errorCode)) {
                dedLog.error("ERROR   | Bellow records are already pending for this department." + errorCode);
                throw new SBLException("Bellow records are already pending for this department.<br>(" + errorCode+")");
            }
                        
            isSuccess = CompanyUserTmpFacade.saveEmp(bankUserModelList,userData);

        } catch (SBLException ex) {
            dedLog.error("ERROR   | Unable to save bank user in temp table." + ex.getMessage(), ex);
            throw new SBLException(ex.getMessage());
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to save bank user in temp table." + ex.getMessage(), ex);
            throw new SBLException("Unable to save bank user in temp table.");
        }
        dedLog.info("LEFT | FDUserLogic.saveBankUser()");
        return isSuccess;
    }

    @Override
    public boolean rejectBankUser(List<CompanyUserModel> bankUserModelList, List<String> empIds,UserData userData) throws Exception {
        dedLog.info("ENTERED | FDUserLogic.verifyBankUser()");
        boolean isSuccess = false;
        String errorCode = "";
        try {
          Map<String, CompanyUserModel> userMap = CompanyUserTmpFacade.getTempCompanyUsers(empIds, userData.getDIV_CODE());//Pending OR Reject

            for (String empId : empIds) {
                if (userMap.get(empId) != null && userMap.get(empId).getRecStatus().equalsIgnoreCase(ApplicationConstants.RECORD_STATUS_REJECT)) {
                    errorCode = errorCode.equalsIgnoreCase("") ? empId : errorCode + "," + empId;
                }
            }

            if (APPUtills.isThisStringValid(errorCode)) {
                dedLog.error("ERROR   | Bellow record is already rejected in this department." + errorCode);
                throw new SBLException("Bellow record is already rejected in this department.<br>(" + errorCode+")");
            }
            //Update Temp
            isSuccess = CompanyUserTmpFacade.rejectEmp(bankUserModelList);            

        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to verify." + ex.getMessage(), ex);
            throw new SBLException(ex.getMessage());
        }
        dedLog.info("LEFT | FDUserLogic.verifyBankUser()");
        return isSuccess;
    }

    @Override
    public boolean verifyBankUser(List<CompanyUserModel> bankUserModelList, List<String> empIds) throws Exception {
        dedLog.info("ENTERED | FDUserLogic.verifyBankUser()");
        boolean isSuccess = false;
        String errorCode = "";
        try {
            Map<String, CompanyUserModel> userMap = CompanyUserTmpFacade.getTempCompanyUsers(empIds, null);//Pending OR Reject

            for (String empId : empIds) {
                if (userMap.get(empId) == null) {
                    errorCode = errorCode.equalsIgnoreCase("") ? empId : errorCode + "," + empId;
                }
            }

            if (APPUtills.isThisStringValid(errorCode)) {
                dedLog.error("ERROR   | Bellow record is already verified." + errorCode);
                throw new SBLException("Bellow record is already verified.<br>(" + errorCode+")");
            }
            
          /*  for (CompanyUserModel bankUserModel : bankUserModelList) {
                if (bankUserModel.getBankUserMasterId() == null) {
                    bankUserModel.setBankUserMasterId(seqNumberGeneratorBean.getNextBankUserMasterId());
                } 
            }
            */
            //Update Temp
            isSuccess = CompanyUserTmpFacade.verifyEmp(bankUserModelList);
            //Update Master
            isSuccess = CompanyUserMasterFacade.verifyEmp(bankUserModelList);

        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to verify." + ex.getMessage(), ex);
            throw new SBLException(ex.getMessage());
        }
        dedLog.info("LEFT | FDUserLogic.verifyBankUser()");
        return isSuccess;
    }

    @Override
    public boolean deleteBankUser( List<CompanyUserModel> bankUserModels) throws Exception {
        dedLog.info("ENTERED | FDUserLogic.deleteBankUser()");
        boolean isSuccess = true;
        try {
            CompanyUserTmpFacade.deleteEmp(bankUserModels);
        } catch (Exception ex) {
            dedLog.error("ERROR   | Unable to delete." + ex.getMessage(), ex);
            isSuccess = false;
        }
        dedLog.info("LEFT | FDUserLogic.deleteBankUser()");
        return isSuccess;
    }
}
