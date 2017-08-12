/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.InvalidInputException;
import com.sg.flooringmastery.service.NoOrderFoundException;
import java.time.LocalDate;
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
public class FlooringMasteryOrderDaoTest {
    
    private FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoFileImpl();
    Order onlyOrder;
//******************************************************************************    
    public FlooringMasteryOrderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testCreateOrder() throws Exception {
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
    }

    /**
     * Test of editOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testEditOrder() throws Exception {
    }

    /**
     * Test of retrieveOrdersByDate method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testRetrieveOrdersByDate() throws Exception {
    }

    /**
     * Test of retrieveOrdersByDateAndId method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testRetrieveOrdersByDateAndId() throws Exception {
    }

    /**
     * Test of loadOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testLoadOrder() throws Exception {
    }

    /**
     * Test of saveOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testSaveOrder() throws Exception {
    }

    /**
     * Test of getOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testGetOrder() throws Exception {
    }
    
}
