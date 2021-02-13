/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.bl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.dao.UserReferenceFacadeLocal;
import com.company.dao.SeqNumberGeneratorBeanLocal;
import com.company.dto.UserReference;
import com.company.models.UserData;
import com.company.common.APPUtills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sits_lahirupr
 */
@Repository
public class UserReferenceLogic implements UserReferenceLogicLocal {

    private static Logger APPLog = LogManager.getLogger(UserReferenceLogic.class);

    @Autowired
    private SeqNumberGeneratorBeanLocal seqNumberGeneratorBean;
    @Autowired
    private UserReferenceFacadeLocal APPUserReferenceFacade;

    @Override
    public boolean upateReferenceByUserId(UserData userData) throws Exception {
        APPLog.info("ENTERED | UserReferenceLogic.upateReferenceByUserId()");
        boolean isSuccess = false;
        try {
            UserReference dur = APPUserReferenceFacade.getReferenceByUserId(Integer.parseInt(userData.getUSER_ID()));
            if (dur == null) {
                dur = new UserReference();
                
                dur.setReferenceId(seqNumberGeneratorBean.getNextUserReferenceId());
                dur.setUserId(Integer.parseInt(userData.getUSER_ID()));
                dur.setFirstLoginDate(APPUtills.getCurrentDate());
                dur.setLoggingCount(1);
                dur.setLastLoginDate(APPUtills.getCurrentDate());
                APPLog.info("MESSAGE | NEW RECORD..");
                APPUserReferenceFacade.create(dur);
            } else {
                dur.setLoggingCount(dur.getLoggingCount() + 1);
                dur.setLastLoginDate(APPUtills.getCurrentDate());
                APPLog.info("MESSAGE | UPDATE RECORD..");
                APPUserReferenceFacade.edit(dur);
            }

        } catch (Exception ex) {
            APPLog.error("ERROR   | Unable to update reference table." + ex.getMessage(), ex);
            //throw new SBLException("Unable to reject.Please try again.");
        }
        APPLog.info("LEFT    | UserReferenceLogic.upateReferenceByUserId()");
        return isSuccess;
    }
}
