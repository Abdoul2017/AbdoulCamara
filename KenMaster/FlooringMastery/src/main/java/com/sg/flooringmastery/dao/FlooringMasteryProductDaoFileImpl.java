/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryProductDaoFileImpl implements FlooringMasteryProductDao {

    public static final String PRODUCT_FILE = "products.txt";
    public static final String DELIMITER = ",";

    HashMap<String, Product> productMap = new HashMap<>();
    private boolean isProductsLoaded = false;

//******************************************************************************    
    @Override
    public Product getProduct(String productType)
            throws FlooringMasteryPersistenceException {
        loadProduct();
        return productMap.get(productType);
    }

    @Override
    public List<Product> getAllProducts()
            throws FlooringMasteryPersistenceException {
        loadProduct();
        return new ArrayList<Product>(productMap.values());
    }
//******************************************************************************

    private void loadProduct() throws FlooringMasteryPersistenceException {
        if (!isProductsLoaded) {
            Scanner scanner;

            try {
                scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));

            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceException("-_- Could not load Product data into memory.", e);
            }
            String currentLine;
            String[] currentTokens;
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);

                Product newProduct = new Product(currentTokens[0]);
                newProduct.setCostPerSqFt(new BigDecimal(currentTokens[1]));
                newProduct.setLaborCostPerSqFt(new BigDecimal(currentTokens[2]));
                
                productMap.put(newProduct.getProductType(), newProduct);
            }
            scanner.close();
            isProductsLoaded = true;
        }
    }
}
