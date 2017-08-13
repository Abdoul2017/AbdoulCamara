/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTax;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryStateTaxDaoTest {
    private FlooringMasteryStateTaxDao stateTaxDao = new FlooringMasteryStateTaxDaoFileImpl();
    StateTax onlyStateTax;
//******************************************************************************    

    public FlooringMasteryStateTaxDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        List<StateTax> taxList = stateTaxDao.getAllTaxes();
        onlyStateTax = taxList.get(0);

    }

    @After
    public void tearDown() {
    }
//****************************************************************************** 

    /**
     * Test of getTax method, of class FlooringMasteryStateTaxDao.
     */
    @Test
    public void testGetTax() throws Exception {
        String stateName = onlyStateTax.getStateName();
        StateTax tax = stateTaxDao.getTax(stateName);
        
        assertEquals(onlyStateTax.getStateName(), tax.getStateName());
        assertEquals(onlyStateTax.getTaxRate(), tax.getTaxRate());
    }

    /**
     * Test of getAllTaxes method, of class FlooringMasteryStateTaxDao.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        assertFalse(stateTaxDao.getAllTaxes().isEmpty());
    }
//****************************************************************************** 
}
