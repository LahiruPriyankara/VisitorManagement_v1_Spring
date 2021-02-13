/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.bl;

import java.util.List;
import java.util.Map;

import com.company.models.CompanyUserModel;
import com.company.models.UserData;

public interface CompanyUserLogicLocal {
    public Map<String, CompanyUserModel> getCompanyUsers(String tableType, List<String> ids, String depCode) throws Exception;
    
    public boolean saveBankUser(List<CompanyUserModel> bankUserModelList,List<String> IdsAryList, UserData userData) throws Exception;

    public boolean rejectBankUser(List<CompanyUserModel> bankUserModelList, List<String> empIds,UserData userData) throws Exception;

    public boolean deleteBankUser( List<CompanyUserModel> bankUserModels) throws Exception;

    public boolean verifyBankUser(List<CompanyUserModel> bankUserModelList , List<String> empIds) throws Exception;
}
