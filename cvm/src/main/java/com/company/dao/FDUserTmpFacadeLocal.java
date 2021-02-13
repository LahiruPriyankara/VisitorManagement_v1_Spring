/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import java.util.List;
import java.util.Map;
import com.company.dto.FdUserTmp;
import com.company.models.FdUserModel;

/**
 *
 * @author sits_lahirupr
 */
public interface FDUserTmpFacadeLocal {

    void create(FdUserTmp eventLog);

    void edit(FdUserTmp eventLog);

    void remove(FdUserTmp eventLog);

    FdUserTmp find(Object id);

    List<FdUserTmp> findAll();

    List<FdUserTmp> findRange(int[] range);

    int count();

    public Map<Integer, FdUserModel> getTempFdUsers(List<Integer> ids,String tableType) throws Exception;

}
