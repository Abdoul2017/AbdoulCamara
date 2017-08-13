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
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
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

    @After
    public void tearDown() {
    }
//******************************************************************************

    /**
     * Test of commitWork method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCommitWork() throws Exception {
    }
//******************************************************************************

    /**
     * Test of getOrders method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrders() throws Exception {
        System.out.println("GET ALL ORDERS TEST");
        assertEquals(1, service.getOrders(LocalDate.of(12, Month.AUGUST, 2017)).size());
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
        Product newProduct = new Product("Wood");

        //newOrder.setDate(LocalDate.of(14, Month.AUGUST, 2017));
        newOrder.setCustomerName("Abdoul");
        newTax.setStateName("OH");
        //newTax.setTaxRate(BigDecimal.valueOf(6.0));
        newProduct.setProductType("Wood");
        //newProduct.setCostPerSqFt(BigDecimal.valueOf(3.0));
        //newProduct.setLaborCostPerSqFt(BigDecimal.valueOf(2.5));        
        newOrder.setArea(BigDecimal.valueOf(5.0));

        service.createOrder(LocalDate.of(14, Month.MARCH, 2017), newOrder);

    }
//******************************************************************************

    @Test
    public void testCreateOrderWithBadStateName() throws Exception {
        //THIS TEST CAN BE MODELED FOR AREA. DO IF TIME PERMIT
        System.out.println("CREATE ORDER TEST WITH BAD STATE");
        Order newOrder = new Order();
        StateTax newTax = new StateTax("OH");
        Product newProduct = new Product("Wood");

        //newOrder.setDate(LocalDate.of(14, Month.AUGUST, 2017));
        newOrder.setCustomerName("Lucas");
        newTax.setStateName(" ");
        //newTax.setTaxRate(BigDecimal.valueOf(6.0));
        newProduct.setProductType("Wood");
        //newProduct.setCostPerSqFt(BigDecimal.valueOf(3.0));
        //newProduct.setLaborCostPerSqFt(BigDecimal.valueOf(2.5));        
        newOrder.setArea(BigDecimal.valueOf(5.0));
        try {
            service.createOrder(LocalDate.of(15, Month.APRIL, 2017), newOrder);
            fail("InvalidInputValidation was not thrown.");
        } catch (FlooringMasteryPersistenceException e) {
            return;
        }
    }
//******************************************************************************
   @Test
    public void testCreateOrderWithBadProductType() throws Exception {
        //THIS TEST CAN BE MODELED FOR PRODUCT NAME AND AREA. DO IF TIME PERMIT
        System.out.println("CREATE ORDER TEST WITH BAD STATE");
        Order newOrder = new Order();
        StateTax newTax = new StateTax("OH");
        Product newProduct = new Product("Wood");

        //newOrder.setDate(LocalDate.of(14, Month.AUGUST, 2017));
        newOrder.setCustomerName("Lucas");
        newTax.setStateName("OH");
        //newTax.setTaxRate(BigDecimal.valueOf(6.0));
        newProduct.setProductType(" ");
        //newProduct.setCostPerSqFt(BigDecimal.valueOf(3.0));
        //newProduct.setLaborCostPerSqFt(BigDecimal.valueOf(2.5));        
        newOrder.setArea(BigDecimal.valueOf(5.0));
        try {
            service.createOrder(LocalDate.of(15, Month.APRIL, 2017), newOrder);
            fail("InvalidInputValidation was not thrown.");
        } catch (FlooringMasteryPersistenceException e) {
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
    }
//******************************************************************************

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        System.out.println("REMOVE ORDER TEST");
//        Order order = new Order();
//        order = service.removeOrder(LocalDate.of(13, Month.AUGUST, 2017), order);
//        assertNotNull(order));
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
//******************************************************************************

    /**
     * Test of getOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = service.getOrder(4, LocalDate.of(12, Month.AUGUST, 2017));
        assertNull(order);
        order = service.getOrder(11, LocalDate.of(12, Month.AUGUST, 2017));
        assertNull(order);

    }

//******************************************************************************
}
