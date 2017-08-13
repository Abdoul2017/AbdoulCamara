/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryProductDaoStubImpl implements FlooringMasteryProductDao{

    Product onlyProduct;
    List<Product> productList = new ArrayList<>();

    public FlooringMasteryProductDaoStubImpl() {
        onlyProduct = new Product("Tile");
        onlyProduct.setProductType("Tile");
        onlyProduct.setCostPerSqFt(BigDecimal.valueOf(15.5));
        onlyProduct.setLaborCostPerSqFt(BigDecimal.valueOf(25));

    }

    @Override
    public Product getProduct(String productType)
            throws FlooringMasteryPersistenceException {
        if (productType == onlyProduct.getProductType()) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts()
            throws FlooringMasteryPersistenceException {
        return productList;
    }
    
}
