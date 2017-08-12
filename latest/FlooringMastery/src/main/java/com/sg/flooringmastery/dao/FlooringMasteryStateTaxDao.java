/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTax;
import java.util.List;

/**
 *
 * @author n0200797
 */
public interface FlooringMasteryStateTaxDao {

    public StateTax getTax(String stateName)
            throws FlooringMasteryPersistenceException;
    List<StateTax> getAllTaxes()
            throws FlooringMasteryPersistenceException;
}
