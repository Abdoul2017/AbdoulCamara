/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author n0200797
 */
public interface FlooringMasteryProductDao {

    Product getProduct(String productType)
            throws FlooringMasteryPersistenceException;

    List<Product> getAllProducts()
            throws FlooringMasteryPersistenceException;
}
