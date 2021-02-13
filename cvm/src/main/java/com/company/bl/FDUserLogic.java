/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.bl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.common.ApplicationConstants;
import com.company.dao.FDUserMasterFacadeLocal;
import com.company.dao.FDUserTmpFacadeLocal;
import com.company.dao.SeqNumberGeneratorBeanLocal;
import com.company.dto.FdUserMaster;
import com.company.dto.FdUserTmp;
import com.company.models.FdUserModel;
import com.company.models.UserData;
//import org.sampath.app.services.manager.SecurePassManagerLocal;
import com.company.common.APPUtills;
import com.company.common.SBLException;
import org.springframework.beans.factory.annotation.Autowired;


public class FDUserLogic implements FDUserLogicLocal {

    private static Logger Log = LogManager.getLogger(FDUserLogic.class);

    @Autowired
    private FDUserTmpFacadeLocal FDUserTmpFacade;
    @Autowired
    private FDUserMasterFacadeLocal FDUserMasterFacade;
    @Autowired
    private SeqNumberGeneratorBeanLocal seqNumberGeneratorBean;
    //@Autowired
    //private SecurePassManagerLocal SecurePassManager;

    @Override
    public Map<Integer, FdUserModel> getFdUsers(String tableType, List<Integer> ids) throws Exception {
        Log.info("ENTERED | FDUserLogic.getFdUsers()");
        Map<Integer, FdUserModel> dfumsMap = new HashMap();
        try {
            if (tableType.equalsIgnoreCase(ApplicationConstants.MASTER_DATA)) {
                Log.info("MESSAGE | Get Existing..");
                dfumsMap = FDUserMasterFacade.getAllFdUsersByUserIds(ids);
            } else if (tableType.equalsIgnoreCase(ApplicationConstants.TEMP_DATA)) {
                Log.info("MESSAGE | Get Pending..");
                dfumsMap = FDUserTmpFacade.getTempFdUsers(ids, ApplicationConstants.TEMP_DATA);
            }

        } catch (SBLException ex) {
            Log.error("ERROR   | Unable to fetch data." + ex.getMessage(), ex);
            throw new SBLException(ex.getMessage());
        } catch (Exception ex) {
            Log.error("ERROR   | Unable to fetch data." + ex.getMessage(), ex);
            throw new SBLException("Unable to fetch data.Please try again.");
        }
        Log.info("LEFT    | FDUserLogic.getFdUsers()");
        return dfumsMap;
    }

    @Override
    public FdUserModel getFdUserByUserName(List<String> getFdUserByUserNames) throws Exception {
        Log.info("ENTERED | FDUserLogic.getFdUserByUserName()");
        Map<Integer, FdUserModel> dfumsMap = new HashMap();
        List<FdUserModel> dfums = new ArrayList<>();
        FdUserModel dfum = null;
        try {
            dfumsMap = FDUserMasterFacade.getFdUserByUserName(getFdUserByUserNames);
            dfums = new ArrayList<>(dfumsMap.values());
            if (dfums.size() > 0) {
                dfum = dfums.get(0);
            } else {
                Log.error("ERROR   | Can not found desk user for user ids : " + getFdUserByUserNames);
                //throw new SBLException("Can not found desk user.");
            }

        } catch (SBLException ex) {
            Log.error("ERROR   | Unable to fetch data." + ex.getMessage(), ex);
            throw new SBLException(ex.getMessage());
        } catch (Exception ex) {
            Log.error("ERROR   | Unable to fetch data." + ex.getMessage(), ex);
            throw new SBLException("Unable to fetch data.Please try again.");
        }
        Log.info("LEFT    | FDUserLogic.getFdUserByUserName()");
        return dfum;
    }

    @Override
    public boolean setPasswordFdUser(String userName, String userPassword) throws SBLException {
        Log.info("ENTERED | FDUserLogic.setPasswordirectryUser()");
        boolean isSuccess = false;
       /* try {
            FdUserModel fdUserModel = getFdUserByUserName(Arrays.asList(userName));
            Map<Integer, FdUserModel> userMap = FDUserTmpFacade.getTempFdUsers(Arrays.asList(fdUserModel.getFdUserMasterId().intValue()), ApplicationConstants.MASTER_DATA);

            if (userMap.size() > 0) {
                Log.error("ERROR   | Password is already sent for authorization.");
                throw new SBLException("Password is already sent for authorization.");
            }

            try {
                SecurePassManager.webServiceSecurePassInitialise();
            } catch (Exception e) {
                Log.error("ERROR   | SecurePass service down.Try again later." + e);
                throw new SBLException("SecurePass service down.Try again later.");
            }

            Log.info("MESSAGE | Going to validated password foramt.");
            if (!SecurePassManager.isValidPasswordFormat(userPassword)) {
                throw new SBLException("Invalid login password format");
            }
            Log.info("MESSAGE | Valid password foramt.");
            Log.info("MESSAGE | Goning to set password.");
            if (!SecurePassManager.setUserPassword(1, ApplicationConstants.APP_CODE + userName, userPassword)) {
                Log.info("MESSAGE | Unable to set password.");
                throw new SBLException("Unable to set password.");
            }
            Log.info("MESSAGE | Successfully set the password.");

            fdUserModel.setFdUserTmpId(seqNumberGeneratorBean.getNextFDUserTempId());
            fdUserModel.setActionType(ApplicationConstants.ACTION_TYPE_MODIFY);
            fdUserModel.setSecurepassUserStatus(ApplicationConstants.SECUREPASS_USER_SET_PASSWORD);

            Log.info("MESSAGE | fdUserModel.toString() " + fdUserModel.toString());
            FDUserTmpFacade.create((FdUserTmp) fdUserModel.modelToObject(ApplicationConstants.TEMP_DATA));

            //SETTING USER PASSWORD SUCCESSFULLY
            isSuccess = true;
            Log.info("MESSAGE |  User Password Set Successfull");
        } catch (SBLException e) {
            Log.error("ERROR   | " + e.getMessage());
            throw new SBLException(e.getMessage());
        } catch (Exception e) {
            Log.error("ERROR   | " + e.getMessage());
            throw new SBLException("User Password Set fail.");
        }
        Log.info("LEFT    | FDUserLogic.setPasswordirectryUser()");*/
        return isSuccess;
    }

    @Override
    public boolean saveFdUser(FdUserModel fdUserModel, UserData userData, boolean isSecurepassProcess) throws Exception {
        Log.info("ENTERED | FDUserLogic.saveFdUser()");
        boolean isSuccess = true;
       /* try {
            fdUserModel.setModifiedBy(Integer.parseInt(userData.getUSER_ID()));
            fdUserModel.setModifiedDate(APPUtills.getCurrentDate());
// ---------------------------------------------------------------------------------------
            Log.info("MESSAGE |  isSecurepassProcess : " + isSecurepassProcess);
            if (isSecurepassProcess) {
                Log.info("MESSAGE |  Going to update temp for secure password.");
                Map<Integer, FdUserModel> userMap = new HashMap();
                userMap = FDUserTmpFacade.getTempFdUsers(Arrays.asList(fdUserModel.getFdUserMasterId().intValue()), ApplicationConstants.MASTER_DATA);

                if (userMap.size() > 0) {
                    Log.error("ERROR   | Bellow record is already pending.");
                    throw new SBLException("Bellow record is already pending.");
                }
                fdUserModel.setRecStatus(ApplicationConstants.RECORD_STATUS_PENDING);
                Log.info("MESSAGE | fdUserModel.toString() " + fdUserModel.toString());
                FDUserTmpFacade.edit((FdUserTmp) fdUserModel.modelToObject(ApplicationConstants.TEMP_DATA));

                return isSuccess;
            }

// ---------------------------------------------------------------------------------------
            if (fdUserModel.getRecStatus().equalsIgnoreCase(ApplicationConstants.RECORD_STATUS_REJECT)) {
                Log.info("MESSAGE |  Going to reject temp record.");
                fdUserModel.setRecStatus(ApplicationConstants.RECORD_STATUS_PENDING);
                Log.info("MESSAGE | fdUserModel.toString() " + fdUserModel.toString());
                FDUserTmpFacade.edit((FdUserTmp) fdUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
            } else {
                fdUserModel.setFdUserTmpId(seqNumberGeneratorBean.getNextFDUserTempId());
                fdUserModel.setRecStatus(ApplicationConstants.RECORD_STATUS_PENDING);
                if (fdUserModel.getActionType().equalsIgnoreCase(ApplicationConstants.ACTION_TYPE_NEW)) {
                    Log.info("MESSAGE |  Going to save new record.");
                    fdUserModel.setCreatedBy(Integer.parseInt(userData.getUSER_ID()));
                    fdUserModel.setCreatedDate(APPUtills.getCurrentDate());
                } else {
                    Log.info("MESSAGE |  Going to save master table record.");
                    Map<Integer, FdUserModel> userMap = new HashMap();
                    userMap = FDUserTmpFacade.getTempFdUsers(Arrays.asList(fdUserModel.getFdUserMasterId().intValue()), ApplicationConstants.MASTER_DATA);

                    if (userMap.size() > 0) {
                        Log.error("ERROR   | Bellow record is already pending.");
                        throw new SBLException("Bellow record is already pending.");
                    }
                }
                Log.info("MESSAGE | fdUserModel.toString() " + fdUserModel.toString());
                FDUserTmpFacade.create((FdUserTmp) fdUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
            }

        } catch (SBLException ex) {
            Log.error("ERROR   | Unable to save front desak user in temp table." + ex.getMessage(), ex);
            throw new SBLException(ex.getMessage());
        } catch (Exception ex) {
            Log.error("ERROR   | Unable to save front desak user in temp table." + ex.getMessage(), ex);
            throw new SBLException("Unable to save front desak user in temp table..");
        }
        Log.info("LEFT    | FDUserLogic.saveFdUser()");*/
        return isSuccess;
    }

    @Override
    public boolean rejectFdUser(FdUserModel fdUserModel, UserData userData) throws Exception {
        Log.info("ENTERED | FDUserLogic.rejectFdUser()");
        boolean isSuccess = true;
        try {
            fdUserModel.setModifiedBy(Integer.parseInt(userData.getUSER_ID()));
            fdUserModel.setModifiedDate(APPUtills.getCurrentDate());
            fdUserModel.setRecStatus(ApplicationConstants.RECORD_STATUS_REJECT);
            Log.info("MESSAGE | fdUserModel.toString() " + fdUserModel.toString());
            FDUserTmpFacade.edit((FdUserTmp) fdUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
        } catch (Exception ex) {
            Log.error("ERROR   | Unable to reject." + ex.getMessage(), ex);
            isSuccess = false;
            //throw new SBLException("Unable to reject.Please try again.");
        }
        Log.info("LEFT    | FDUserLogic.rejectFdUser()");
        return isSuccess;
    }

    @Override
    public boolean deleteFdUser(FdUserModel fdUserModel) throws Exception {
        Log.info("ENTERED | FDUserLogic.deleteFdUser()");
        boolean isSuccess = true;
        try {
            Log.info("MESSAGE | fdUserModel.toString() " + fdUserModel.toString());
            FDUserTmpFacade.remove((FdUserTmp) fdUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
        } catch (Exception ex) {
            Log.error("ERROR   | Unable to reject." + ex.getMessage(), ex);
            isSuccess = false;
            //throw new SBLException("Unable to dalete.Please try again.");
        }
        Log.info("LEFT    | FDUserLogic.deleteFdUser()");
        return isSuccess;
    }

    @Override
    public boolean verifyUser(FdUserModel fdUserModel, UserData userData, boolean isSecurepassProcess) throws Exception {
        Log.info("ENTERED | FDUserLogic.verifyUser()");
        boolean isSuccess = false, isRegister = false, isAuthorize = false, iActivateUser = false;
       /* try {
            Log.info("MESSAGE | isSecurepassProcess : " + isSecurepassProcess);
            Map<Integer, FdUserModel> userMap;
            userMap = FDUserTmpFacade.getTempFdUsers(Arrays.asList(fdUserModel.getFdUserTmpId().intValue()), ApplicationConstants.TEMP_DATA);

            if (userMap.isEmpty()) {
                Log.error("ERROR   | Bellow record is already verified.");
                throw new SBLException("Bellow record is already verified.");
            }

            fdUserModel.setVerifiedBy(Integer.parseInt(userData.getUSER_ID()));
            fdUserModel.setVerifiedDate(APPUtills.getCurrentDate());
            fdUserModel.setRecStatus(ApplicationConstants.RECORD_STATUS_VERIFY);

            Log.info("MESSAGE | fdUserModel.toString() " + fdUserModel.toString());
            if (fdUserModel.getActionType().equalsIgnoreCase(ApplicationConstants.ACTION_TYPE_NEW)) {

                Map<Integer, FdUserModel> dfumsMap = FDUserMasterFacade.getFdUserByUserName(Arrays.asList(fdUserModel.getFdUserName()));
                if (!dfumsMap.isEmpty()) {
                    throw new SBLException("User name is already exist.");
                }
                try {
                    SecurePassManager.webServiceSecurePassInitialise();
                } catch (Exception e) {
                    Log.error("ERROR   | SecurePass service down.Try again later." + e);
                    throw new SBLException("SecurePass service down.Try again later.");
                }

                Log.info("MESSAGE | Going to register user in bank by using securePass web service.");
                if (!SecurePassManager.userRegister(ApplicationConstants.APP_CODE + fdUserModel.getFdUserName())) {
                    throw new SBLException("User registration failed.");
                }
                Log.info("MESSAGE | Going to authorize user in bank by using securePass web service.");
                if (!SecurePassManager.authorize(ApplicationConstants.APP_CODE + fdUserModel.getFdUserName())) {
                    throw new SBLException("User registration failed.");
                }

                Log.info("MESSAGE | successfully register and authorize.");
                fdUserModel.setFdUserMasterId(seqNumberGeneratorBean.getNextFDUserMasterId());
                //Create In Master Table
                FDUserMasterFacade.create((FdUserMaster) fdUserModel.modelToObject(ApplicationConstants.MASTER_DATA));
            } else {
                if (isSecurepassProcess) {
                    try {
                        SecurePassManager.webServiceSecurePassInitialise();
                    } catch (Exception e) {
                        Log.error("ERROR   | SecurePass service down.Try again later." + e);
                        throw new SBLException("SecurePass service down.Try again later.");
                    }
                    Log.info("MESSAGE | Going to active user in bank by using securePass web service.");
                    if (!SecurePassManager.activateUser(ApplicationConstants.APP_CODE + fdUserModel.getFdUserName())) {
                        throw new SBLException("User activation failed.");
                    }
                    fdUserModel.setSecurepassUserStatus(ApplicationConstants.SECUREPASS_USER_ACTIVE);
                }
                //Update Master Table
                FDUserMasterFacade.edit((FdUserMaster) fdUserModel.modelToObject(ApplicationConstants.MASTER_DATA));
            }

            //Update Temp Table
            FDUserTmpFacade.edit((FdUserTmp) fdUserModel.modelToObject(ApplicationConstants.TEMP_DATA));
            isSuccess = true;
        } catch (Exception ex) {
            Log.error("ERROR   | Unable to verify." + ex.getMessage(), ex);
            //isSuccess = false;
            throw new SBLException(ex.getMessage());
        }
        Log.info("LEFT    | FDUserLogic.verifyUser()");*/
        return isSuccess;
    }

}
