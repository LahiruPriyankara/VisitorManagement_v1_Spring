/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import java.util.List;
import java.util.Map;

import com.company.dto.CompanyUserMaster;
import com.company.models.CompanyUserModel;


/**
 *
 * @author sits_lahirupr
 */

public interface CompanyUserMasterFacadeLocal {

    void create(CompanyUserMaster obj);

    void edit(CompanyUserMaster obj);

    void remove(CompanyUserMaster obj);

    CompanyUserMaster find(Object id);

    List<CompanyUserMaster> findAll();

    List<CompanyUserMaster> findRange(int[] range);

    int count();

    public Map<String, CompanyUserModel> getMasterCompanyUsers(List<String> ids, String depCode) throws Exception;

    public boolean verifyEmp(List<CompanyUserModel> CompanyUserModelList) throws Exception;
}
