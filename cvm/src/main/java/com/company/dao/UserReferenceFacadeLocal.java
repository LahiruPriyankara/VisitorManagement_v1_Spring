/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import java.util.List;

import com.company.dto.UserReference;

/**
 *
 * @author sits_lahirupr
 */

public interface UserReferenceFacadeLocal {

    void create(UserReference eventLog);

    void edit(UserReference eventLog);

    void remove(UserReference eventLog);

    UserReference find(Object id);

    List<UserReference> findAll();

    List<UserReference> findRange(int[] range);

    int count();

    public UserReference getReferenceByUserId(int uId) throws Exception;
}
