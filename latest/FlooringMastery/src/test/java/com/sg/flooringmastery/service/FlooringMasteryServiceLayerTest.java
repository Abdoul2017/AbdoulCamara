/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryOrderDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrderDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryStateTaxDao;
import com.sg.flooringmastery.dao.FlooringMasteryStateTaxDaoFileImpl;
import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryServiceLayerTest {

    private FlooringMasteryServiceLayer service;

    public FlooringMasteryServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringMasteryServiceLayer.class);
    }
//******************************************************************************    

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoStubImpl();
        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoFileImpl();
        FlooringMasteryStateTaxDao stateTaxDao = new FlooringMasteryStateTaxDaoFileImpl();
        
        service = new FlooringMasteryServiceLayerFileImpl(orderDao, productDao, stateTaxDao);
    }
//******************************************************************************
    @After
    public void tearDown() {
    }

    /**
     * Test of commitWork method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCommitWork() throws Exception {
    }

    /**
     * Test of getOrders method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrders() throws Exception {
    }

    /**
     * Test of createOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCreateOrder() throws Exception {
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
    }

    /**
     * Test of confirmOrderRemoval method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testConfirmOrderRemoval() throws Exception {
    }

    /**
     * Test of commitEditChange method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCommitEditChange() throws Exception {
    }

    /**
     * Test of getOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrder() throws Exception {
    }



}
