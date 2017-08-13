/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryStateTaxDaoStubImpl implements FlooringMasteryStateTaxDao {

    StateTax onlyStateTax;
    List<StateTax> taxList = new ArrayList<>();

    public FlooringMasteryStateTaxDaoStubImpl() {
        onlyStateTax = new StateTax("OH");
        onlyStateTax.setStateName("OH");
        onlyStateTax.setTaxRate(BigDecimal.valueOf(6.25));

    }

    @Override
    public StateTax getTax(String stateName)
            throws FlooringMasteryPersistenceException {
        if (stateName == onlyStateTax.getStateName()) {
            return onlyStateTax;
        } else {
            return null;
        }
    }

    @Override
    public List<StateTax> getAllTaxes()
            throws FlooringMasteryPersistenceException {
        return taxList;
    }

}
