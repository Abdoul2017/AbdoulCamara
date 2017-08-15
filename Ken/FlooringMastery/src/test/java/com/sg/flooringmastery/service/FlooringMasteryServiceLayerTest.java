/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryServiceLayerTest {

    private FlooringMasteryServiceLayer service;
    private FlooringMasteryOrderDao orderDao;
    private FlooringMasteryProductDao productDao;
    private FlooringMasteryStateTaxDao stateTaxDao;
    private LocalDate date;

    public FlooringMasteryServiceLayerTest() {
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
        orderDao = new FlooringMasteryOrderDaoStubImpl();
        productDao = new FlooringMasteryProductDaoStubImpl();
        stateTaxDao = new FlooringMasteryStateTaxDaoStubImpl();

        service = new FlooringMasteryServiceLayerFileImpl(orderDao, productDao, stateTaxDao);

        date = LocalDate.parse("01012017", DateTimeFormatter.ofPattern("MMddyyyy"));
    }

    @After
    public void tearDown() {
    }
//******************************************************************************

    /**
     * Test of commitWork method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCommitWork() throws Exception {
        System.out.println("COMMIT WORK TEST");
        Order order = new Order();
        order.setCustomerName("Abdoul");
        Order commitedOrder = service.commitWork(date, order);
        assertEquals(order.getCustomerName(), commitedOrder.getCustomerName());
    }
//******************************************************************************

    @Test
    public void testGetStateTaxes() throws Exception {
        System.out.println("GET STATE TAXES TEST");
        List<StateTax> stateTaxes = service.getStateTaxes();
        assertEquals(1, stateTaxes.size());
    }
//******************************************************************************

    @Test
    public void testGetProducts() throws Exception {
        System.out.println("GET PRODUCTS TEST");
        List<Product> products = service.getProducts();
        assertEquals(1, products.size());
    }
//******************************************************************************

    /**
     * Test of getOrders method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrders() throws Exception {
        System.out.println("GET ALL ORDERS TEST");
        List<Order> orders = service.getOrders(date);
        assertEquals(1, orders.size());
    }
//******************************************************************************    

    @Test
    public void testGetOrdersOnDifferentDate() throws Exception {
        System.out.println("GET ALL ORDERS TEST ON DIFFERENT DATE");
        try {
            date = LocalDate.parse("02012017", DateTimeFormatter.ofPattern("MMddyyyy"));
            List<Order> orders = service.getOrders(date);
            fail("Expected NoOrderFoundException was not thrown. ");
        } catch (NoOrderFoundException e) {
            return;
        }
    }
//******************************************************************************

    /**
     * Test of getOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("GET ORDER TEST");
        Order order = service.getOrder(1, date);
        assertNotNull(order);
    }
//******************************************************************************

    @Test
    public void testGetOrderWithDifferentNumber() throws Exception {
        System.out.println("GET ORDER TEST WITH DIFFERENT NUMBER");
        Order order = service.getOrder(3, date);
        assertNull(order);
    }
//******************************************************************************

    @Test
    public void testGetOrderWithDifferentDate() throws Exception {
        System.out.println("GET ORDER TEST WITH DIFFERENT DATE");
        date = LocalDate.parse("02012017", DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order = service.getOrder(3, date);
        assertNull(order);
    }
//******************************************************************************

    /**
     * Test of createOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCreateOrder() throws Exception {
        System.out.println("CREATE ORDER TEST");
        Order newOrder = new Order();
        StateTax newTax = new StateTax("OH");
        Product newProduct = new Product("Tile");

        newOrder.setCustomerName("Abdoul");
        newOrder.setTaxRate(newTax);
        newOrder.setProductType(newProduct);
        newOrder.setArea(BigDecimal.valueOf(5.0));

        Order createdOrder = service.createOrder(date, newOrder);

        assertEquals(newOrder.getCustomerName(), createdOrder.getCustomerName());
        assertEquals(newOrder.getProductType().getProductType(), createdOrder.getProductType().getProductType());
        assertEquals(newOrder.getTaxRate().getStateName(), createdOrder.getTaxRate().getStateName());
        assertEquals(newOrder.getArea(), createdOrder.getArea());
    }
//******************************************************************************

    @Test
    public void testCreateOrderWithBadStateName() throws Exception {
        System.out.println("CREATE ORDER TEST WITH BAD STATE");
        Order newOrder = new Order();
        StateTax newTax = new StateTax("IN");
        Product newProduct = new Product("Tile");

        newOrder.setCustomerName("Abdoul");
        newOrder.setTaxRate(newTax);
        newOrder.setProductType(newProduct);
        newOrder.setArea(BigDecimal.valueOf(5.0));

        try {
            service.createOrder(date, newOrder);
            fail("InvalidInputValidation was not thrown.");
        } catch (InvalidInputException e) {
            return;
        }
    }
//******************************************************************************

    @Test
    public void testCreateOrderWithBadProductType() throws Exception {
        System.out.println("CREATE ORDER TEST WITH BAD STATE");
        Order newOrder = new Order();
        StateTax newTax = new StateTax("OH");
        Product newProduct = new Product("Plastic");

        newOrder.setCustomerName("Abdoul");
        newOrder.setTaxRate(newTax);
        newOrder.setProductType(newProduct);
        newOrder.setArea(BigDecimal.valueOf(5.0));

        try {
            service.createOrder(date, newOrder);
            fail("InvalidInputValidation was not thrown.");
        } catch (InvalidInputException e) {
            return;
        }
    }

//******************************************************************************
    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {
        System.out.println("EDIT ORDER TEST");
        Order toEdit = service.getOrder(1, date);
        toEdit.setCustomerName("Abdoul");
        service.editOrder(date, toEdit);
        Order editedOrder = service.getOrder(1, date);
        assertEquals(toEdit.getCustomerName(), editedOrder.getCustomerName());
    }
//******************************************************************************

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("REMOVE ORDER TEST");
        try {
            Order order = service.getOrder(1, date);
            service.removeOrder(date, order);
            List<Order> orders = service.getOrders(date);
            fail("NoOrderFoundException was not thrown.");
        } catch (NoOrderFoundException e) {
            return;
        }
    }
//******************************************************************************

//******************************************************************************
}
