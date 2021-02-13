/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.models;

/**
 *
 * @author sits_lahirupr
 */
public class UserData implements Cloneable {

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    String USER_NAME = "";
    String USER_PASWD = "";

    //     --------     should be manually assignede ------------    
    String USER_TYPE = "";//AD user or FRONT DESK USER

    //     --------     from resrvice response ------------
    String USER_ID = "";//4553
    String SUP_ID = "";//4553
    String FIRST_NAME = "";//SAMAN
    String LAST_NAME = "";//MANCHANAYAKE
    String DESIGNATION_CODE = "";//SEII
    String DESIGNATION_DESC = "";//Senoir Executive II
    String SECURITY_CLASS = "";//10
    String WORK_CLASS_BOT = "";//10
    String AD_USER_ID = "";//DMSUSer2_920
    //String SIGNATURE_ID = "";//A166
    String OFFICER_STATUS = "";//A
    //String DA_LEVEL_BOT = "";//null
    //String DA_AMOUNT_BOT = "";//0
    //String MOBILE_PHONE = "";//null
    //String EMAIL = "";//null
    String SOL_ID = "";//900
    String DIV_CODE = "";//875 -Deprartment
    String DIV_NAME = "";
    //String BOT_STATUS = "";//V
    //String APPLICATION_CODE = "";//HRS
    String APPLICATION_SECURITY_CLASS = "";//10
    String WORK_CLASS_OAT = "";//10
    //String EXPIRY_DATE = "";//31/12/2099 00:00:00
    //String DA_LEVEL_OAT = "";//XX
    //String DA_AMOUNT_OAT = "";//0
    //String SIGN_ON_STATUS = "";//N
    //String ACTIVE_STATUS = "";//A
    //String FUNCTION_CODE1 = "";//null
    //String FUNCTION_CODE2 = "";//null
    //String FUNCTION_CODE3 = "";//null
    //String OAT_STATUS = "";//V
    //String SOL_NAME = "";//Head Office
    //String AREA_CODE = "";//A01
    //String ZONE = "";//null
    //String SOL_DIV_ID = "";//875
    //String FINACLE_WORK_CLASS = "";//200

    String base64Image = "";

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_PASWD() {
        return USER_PASWD;
    }

    public void setUSER_PASWD(String USER_PASWD) {
        this.USER_PASWD = USER_PASWD;
    }

    public String getUSER_TYPE() {
        return USER_TYPE;
    }

    public void setUSER_TYPE(String USER_TYPE) {
        this.USER_TYPE = USER_TYPE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getSUP_ID() {
        return SUP_ID;
    }

    public void setSUP_ID(String SUP_ID) {
        this.SUP_ID = SUP_ID;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getDESIGNATION_CODE() {
        return DESIGNATION_CODE;
    }

    public void setDESIGNATION_CODE(String DESIGNATION_CODE) {
        this.DESIGNATION_CODE = DESIGNATION_CODE;
    }

    public String getDESIGNATION_DESC() {
        return DESIGNATION_DESC;
    }

    public void setDESIGNATION_DESC(String DESIGNATION_DESC) {
        this.DESIGNATION_DESC = DESIGNATION_DESC;
    }

    public String getSECURITY_CLASS() {
        return SECURITY_CLASS;
    }

    public void setSECURITY_CLASS(String SECURITY_CLASS) {
        this.SECURITY_CLASS = SECURITY_CLASS;
    }

    public String getWORK_CLASS_BOT() {
        return WORK_CLASS_BOT;
    }

    public void setWORK_CLASS_BOT(String WORK_CLASS_BOT) {
        this.WORK_CLASS_BOT = WORK_CLASS_BOT;
    }

    public String getAD_USER_ID() {
        return AD_USER_ID;
    }

    public void setAD_USER_ID(String AD_USER_ID) {
        this.AD_USER_ID = AD_USER_ID;
    }

    public String getOFFICER_STATUS() {
        return OFFICER_STATUS;
    }

    public void setOFFICER_STATUS(String OFFICER_STATUS) {
        this.OFFICER_STATUS = OFFICER_STATUS;
    }

    public String getSOL_ID() {
        return SOL_ID;
    }

    public void setSOL_ID(String SOL_ID) {
        this.SOL_ID = SOL_ID;
    }

    public String getDIV_CODE() {
        return DIV_CODE;
    }

    public void setDIV_CODE(String DIV_CODE) {
        this.DIV_CODE = DIV_CODE;
    }

    public String getDIV_NAME() {
        return DIV_NAME;
    }

    public void setDIV_NAME(String DIV_NAME) {
        this.DIV_NAME = DIV_NAME;
    }

    public String getAPPLICATION_SECURITY_CLASS() {
        return APPLICATION_SECURITY_CLASS;
    }

    public void setAPPLICATION_SECURITY_CLASS(String APPLICATION_SECURITY_CLASS) {
        this.APPLICATION_SECURITY_CLASS = APPLICATION_SECURITY_CLASS;
    }

    public String getWORK_CLASS_OAT() {
        return WORK_CLASS_OAT;
    }

    public void setWORK_CLASS_OAT(String WORK_CLASS_OAT) {
        this.WORK_CLASS_OAT = WORK_CLASS_OAT;
    }

    @Override
    public String toString() {
        return "UserData{" + "USER_NAME=" + USER_NAME + ", USER_PASWD=" + USER_PASWD + ", USER_TYPE=" + USER_TYPE + ", USER_ID=" + USER_ID + ", SUP_ID=" + SUP_ID + ", FIRST_NAME=" + FIRST_NAME + ", LAST_NAME=" + LAST_NAME + ", DESIGNATION_CODE=" + DESIGNATION_CODE + ", DESIGNATION_DESC=" + DESIGNATION_DESC + ", SECURITY_CLASS=" + SECURITY_CLASS + ", WORK_CLASS_BOT=" + WORK_CLASS_BOT + ", AD_USER_ID=" + AD_USER_ID + ", OFFICER_STATUS=" + OFFICER_STATUS + ", SOL_ID=" + SOL_ID + ", DIV_CODE=" + DIV_CODE + ", DIV_NAME=" + DIV_NAME + ", APPLICATION_SECURITY_CLASS=" + APPLICATION_SECURITY_CLASS + ", WORK_CLASS_OAT=" + WORK_CLASS_OAT + '}';
    }

}
