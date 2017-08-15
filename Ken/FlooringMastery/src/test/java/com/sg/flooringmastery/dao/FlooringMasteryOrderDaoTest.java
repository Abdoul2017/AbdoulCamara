/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.InvalidInputException;
import com.sg.flooringmastery.service.NoOrderFoundException;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    
    private FlooringMasteryOrderDao orderDao;
    Order onlyOrder;
    LocalDate date;
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
        date = LocalDate.parse("01012017", DateTimeFormatter.ofPattern("MMddyyyy"));
        StateTax onlyStateTax = new StateTax("OH");
        onlyStateTax.setStateName("OH");
        onlyStateTax.setTaxRate(BigDecimal.valueOf(6.25));
        Product onlyProduct = new Product("Tile");
        onlyProduct.setProductType("Tile");
        onlyProduct.setCostPerSqFt(BigDecimal.valueOf(15.5));
        onlyProduct.setLaborCostPerSqFt(BigDecimal.valueOf(25));

        onlyOrder = new Order();
        onlyOrder.setDate(date);
        onlyOrder.setNumber(1);
        onlyOrder.setArea(new BigDecimal("20"));
        onlyOrder.setCustomerName("Test");
        onlyOrder.setTaxRate(onlyStateTax);
        onlyOrder.setProductType(onlyProduct);

        orderDao = new FlooringMasteryOrderDaoFileImpl();
    }
    
    @After
    public void tearDown() {
        File orderFile = new File("Orders_01012017.txt");
        orderFile.delete();
    }

    /**
     * Test of createOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testCreateAndRemoveOrder() throws Exception {
        System.out.println("GET CREATE AND REMOVE ORDER TEST");
        Order createdOrder = orderDao.createOrder(date, onlyOrder);
        Order removedOrder = orderDao.removeOrder(date, onlyOrder.getNumber());

        assertEquals(onlyOrder.getCustomerName(), createdOrder.getCustomerName());
        assertEquals(onlyOrder.getCustomerName(), removedOrder.getCustomerName());
    }

    /**
     * Test of createOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testCreateAndRemoveOrderWithDifferentNumber() throws Exception {
        System.out.println("GET CREATE AND REMOVE ORDER WITH DIFFERENT NUMBER TEST");
        Order createdOrder = orderDao.createOrder(date, onlyOrder);
        Order removedOrder = orderDao.removeOrder(date, 3);

        assertEquals(onlyOrder.getCustomerName(), createdOrder.getCustomerName());
        assertNull(removedOrder);
    }

    /**
     * Test of editOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testCreateAndEditOrder() throws Exception {
        System.out.println("GET CREATE AND EDIT ORDER TEST");
        Order createdOrder = orderDao.createOrder(date, onlyOrder);
        createdOrder.setCustomerName("Abdoul");
        orderDao.editOrder(date, createdOrder);
    }

    /**
     * Test of retrieveOrdersByDate method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testCreateAndRetrieveOrdersByDate() throws Exception {
        System.out.println("GET CREATE AND RETRIEVE ORDER BY DATE TEST");
        Order createdOrder = orderDao.createOrder(date, onlyOrder);
        List<Order> orders = orderDao.retrieveOrdersByDate(date);
        assertEquals(1, orders.size());
    }

    @Test
    public void testRetrieveOrdersByDateInvalid() throws Exception {
        System.out.println("GET RETRIEVE ORDER BY DATE INVALID TEST");
        try {
            date = LocalDate.parse("02012017", DateTimeFormatter.ofPattern("MMddyyyy"));
            orderDao.retrieveOrdersByDate(date);
            fail("Expected FlooringMasteryPersistenceException was not thrown. ");
        } catch(FlooringMasteryPersistenceException  e){
            return;
        }
    }

    /**
     * Test of retrieveOrdersByDateAndId method, of class FlooringMasteryOrderDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("GET GET ORDER TEST");
        Order createdOrder = orderDao.createOrder(date, onlyOrder);
        Order order = orderDao.getOrder(createdOrder.getNumber(), date);
        assertEquals(createdOrder.getCustomerName(), order.getCustomerName());
    }
    @Test
    public void testGetOrderWithInvalidDate() throws Exception {
        System.out.println("GET GET ORDER WITH INVALID DATE TEST");
        try {
            date = LocalDate.parse("02012017", DateTimeFormatter.ofPattern("MMddyyyy"));
            Order order = orderDao.getOrder(onlyOrder.getNumber(), date);
            fail("Expected FlooringMasteryPersistenceException was not thrown. ");
        } catch (FlooringMasteryPersistenceException e) {
            return;
        }
    }
    @Test
    public void testGetOrderWithInvalidId() throws Exception {
        System.out.println("GET GET ORDER WITH INVALID ID TEST");
        try {
            Order createdOrder = orderDao.createOrder(date, onlyOrder);
            Order order = orderDao.getOrder(3, date);
            fail("Expected NoOrderFoundException was not thrown. ");
        } catch(NoOrderFoundException e) {
            return;
        }
    }
    
}
