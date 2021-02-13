/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sits_lahirupr
 */
public class ApplicationConstants {
    //public static String PWD_APPLICATION  = "DVM";//SPE

    // Session Scpe Parameters
    public final static int SCOPE_GLOBAL = 0;
    public final static int SCOPE_BANK_USER = 1;
    public final static int SCOPE_FD_USER = 2;
    public final static int SCOPE_COMMON_USER = 3;
    public final static int SCOPE_COMMON_VIEW = 4;

    public static String APP_ENV = "";
    public static String APP_CODE = "";
    public static String UPM_APP_CODE = "";
    public static String UPM_WS_URL = "";
    public static String SECUREPASS_WS_URL = "";
    public static String UPM_REST_WS_URL = ""; // To get AD user Details
    public static String INITIAL_CONTEXT_FACTORY = ""; // For Validate AD user
    public static String SECURITY_AUTHENTICATION = ""; // For Validate AD user
    public static String PROVIDER_URL = ""; // For Validate AD user
    public static String USERNAME_TAIL = ""; // For Validate AD user

    //public static Boolean USE_PROXY = Boolean.FALSE;
   // public static String PROXY_HOST = "";
   // public static String PROXY_PORT = "";

    // Event Log details
    public static String EVENT_LOG_LOGIN = "LOGIN";
    public static String BANK_EMPLOYEE = "BANK_EMPLOYEE";
    public static String FRONT_DESK_USER = "FRONT_DESK_USER";

    public static String EVENTSUCCESSFUL = "S";
    public static String EVENTFAIL = "F";

    // GLOBAL ERROR MESSAGES
    public final static String ERR_MSG_SESSION_TERMINTATED = "User session terminated. Please log in again.";
    public final static String ERR_MSG_VALUES_ARE_SAME = "Please do some change.";

    // System user roles
    public static String USER_ROLE_SUPER_ENTERER = "1";            // Authorizer : To frontdesk user management 
    public static String USER_ROLE_SUPER_AUTHORIZER = "2";         // Enterer : To frontdesk user management 
    public static String USER_ROLE_COMMON_ENTERER = "3";           // Authorizer : To any user management 
    public static String USER_ROLE_COMMON_AUTHORIZER = "4";    // Enterer : To any user management 
    public static String USER_ROLE_ENTERER = "5";           // Authorizer : To department user management 
    public static String USER_ROLE_AUTHORIZER = "6";       // Enterer : To department user management 
    public static String USER_ROLE_INVALID = "0";            // Invalid user role        

    //Tab header ids
    public final static String APP_DASH_BOARD = "APP_DASH_BOARD";
    public final static String ALL_BANK_USER = "ALL_BANK_USER";
    public final static String BANK_DEP_USER = "BANK_DEP_USER";
    public final static String FD_USER = "FD_EMP";
    public final static String LOU_OUT = "LOU_OUT";

    public final static String MASTER_DATA = "MASTER_DATA";
    public final static String TEMP_DATA = "TEMP_DATA";

    public final static String STATUS_ACTIVE = "A";
    public final static String STATUS_INACTIVE = "I";

    public final static String ACTION_TYPE_NEW = "N";
    public final static String ACTION_TYPE_MODIFY = "M";

    //public final static boolean SAVE_FROM_MASTER_TBL = true;
    public final static String RECORD_STATUS_PENDING = "P";
    public final static String RECORD_STATUS_REJECT = "R";
    public final static String RECORD_STATUS_VERIFY = "V";

    public final static String SECUREPASS_USER_REGISTER = "REGISTER";
    public final static String SECUREPASS_USER_AUTHORIZE = "AUTHORIZE";
    public final static String SECUREPASS_USER_SET_PASSWORD = "SET_PASS";
    public final static String SECUREPASS_USER_ACTIVE = "ACTIVE";
    public final static String SECUREPASS_USER_RESET_PASSWORD = "RESET_PASS";
    public final static String SECUREPASS_USER_LOCK = "LOCK";

    public static List<String> USER_GRADES = new ArrayList<>();
    public final static List<String> USER_GENDER = Arrays.asList(new String[]{"M", "F"});

    public static String statusDesc(String status) {
        status = status != null ? status : "UNKNOWN";
        return status.equalsIgnoreCase(STATUS_ACTIVE) ? "ACTIVE" : status.equalsIgnoreCase(STATUS_INACTIVE) ? "INACTIVE" : "UNKNOWN";
    }

    public static String securepassStatusDesc(String status) {
        String desc = "UNKNOWN";
        status = status != null ? status : "UNKNOWN";

        if (status.equalsIgnoreCase(SECUREPASS_USER_REGISTER)) {
            desc = "REGISTER";
        } else if (status.equalsIgnoreCase(SECUREPASS_USER_AUTHORIZE)) {
            desc = "AUTHORIZE";
        } else if (status.equalsIgnoreCase(SECUREPASS_USER_SET_PASSWORD)) {
            desc = "SET PASSWORD";
        } else if (status.equalsIgnoreCase(SECUREPASS_USER_ACTIVE)) {
            desc = "ACTIVE";
        } else if (status.equalsIgnoreCase(SECUREPASS_USER_RESET_PASSWORD)) {
            desc = "RESET PASSWORD";
        } else if (status.equalsIgnoreCase(SECUREPASS_USER_LOCK)) {
            desc = "LOCK";
        }
        return desc;
    }

    public static String actionTypeDesc(String status) {
        status = status != null ? status : "UNKNOWN";
        return status.equalsIgnoreCase(ACTION_TYPE_NEW) ? "NEW" : status.equalsIgnoreCase(ACTION_TYPE_MODIFY) ? "MODIFY" : "UNKNOWN";
    }

    public static String recordStatusDesc(String status) {
        String desc = "UNKNOWN";
        status = status != null ? status : "UNKNOWN";
        if (status.equalsIgnoreCase(RECORD_STATUS_PENDING)) {
            desc = "PENDING";
        } else if (status.equalsIgnoreCase(RECORD_STATUS_REJECT)) {
            desc = "REJECTED";
        } else if (status.equalsIgnoreCase(RECORD_STATUS_VERIFY)) {
            desc = "VERIFY";
        }
        return desc;
    }

    public static String genderTypeDesc(String gender) {
        String code = "UNKNOWN";
        gender = gender != null ? gender : "";
        if (gender.equalsIgnoreCase("M")) {
            code = "MALE";
        } else if (gender.equalsIgnoreCase("F")) {
            code = "FEMALE";
        }
        return code;
    }
}
