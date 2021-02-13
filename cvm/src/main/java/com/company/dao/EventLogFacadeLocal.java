/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao;

import java.util.List;
import com.company.dto.EventLogTbl;

/**
 *
 * @author sits_lahirupr
 */

public interface EventLogFacadeLocal {

    void create(EventLogTbl eventLog);

    void edit(EventLogTbl eventLog);

    void remove(EventLogTbl eventLog);

    EventLogTbl find(Object id);

    List<EventLogTbl> findAll();

    List<EventLogTbl> findRange(int[] range);

    int count();

}
