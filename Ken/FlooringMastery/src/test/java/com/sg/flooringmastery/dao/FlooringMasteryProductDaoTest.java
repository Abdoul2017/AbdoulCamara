/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
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
public class FlooringMasteryProductDaoTest {
    
    private FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoFileImpl();
    Product onlyProduct;
//******************************************************************************    
    public FlooringMasteryProductDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FlooringMasteryPersistenceException {
        List<Product> productList = productDao.getAllProducts();
        onlyProduct = productList.get(0);
    }
//******************************************************************************       
    @After
    public void tearDown() {
    }
//******************************************************************************   
    /**
     * Test of getProduct method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testGetProduct() throws Exception {
        String productType = onlyProduct.getProductType();
        Product product = productDao.getProduct(productType);
        
        assertEquals(onlyProduct.getProductType(), product.getProductType());
        assertEquals(onlyProduct.getCostPerSqFt(), product.getCostPerSqFt());
        assertEquals(onlyProduct.getLaborCostPerSqFt(), product.getLaborCostPerSqFt());
 
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryProductDao.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        assertFalse(productDao.getAllProducts().isEmpty());
        
    }
    
}
