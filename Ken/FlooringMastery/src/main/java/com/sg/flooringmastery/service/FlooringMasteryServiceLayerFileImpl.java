/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryOrderDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductDao;
import com.sg.flooringmastery.dao.FlooringMasteryStateTaxDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryServiceLayerFileImpl implements FlooringMasteryServiceLayer {

    FlooringMasteryOrderDao orderDao;
    FlooringMasteryProductDao productDao;
    FlooringMasteryStateTaxDao stateTaxDao;

    public FlooringMasteryServiceLayerFileImpl(FlooringMasteryOrderDao orderDao,
            FlooringMasteryProductDao productDao, FlooringMasteryStateTaxDao stateTaxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateTaxDao = stateTaxDao;

    }
//******************************************************************************

    @Override
    public List<Order> getOrders(LocalDate date)
            throws NoOrderFoundException, FlooringMasteryPersistenceException {
        List<Order> allOrderByDate = orderDao.retrieveOrdersByDate(date);
        if(allOrderByDate == null || allOrderByDate.isEmpty()) {
            throw new NoOrderFoundException("Could Not Find Orders!");
        }
        return allOrderByDate;
    }
//******************************************************************************

    @Override
    public Order createOrder(LocalDate date, Order order)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException {
        
        if (stateTaxDao.getTax(order.getTaxRate().getStateName()) == null) {
            throw new InvalidInputException("State Name Not Found");
        }

        if (productDao.getProduct(order.getProductType().getProductType()) == null) {
            throw new  InvalidInputException("Product Name NotFound");
        }

        order.setNumber(generateOrderNumbers());        
        order.setCustomerName(order.getCustomerName());        
        order.setTaxRate(stateTaxDao.getTax(order.getTaxRate().getStateName()));
        order.setProductType(productDao.getProduct(order.getProductType().getProductType()));

        return order;
    }
//******************************************************************************   

    @Override
    public Order getOrder(int ordernumber, LocalDate date)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException, InvalidInputException {
        return orderDao.getOrder(ordernumber, date);
    }

//****************************************************************************** 
    @Override
    public void editOrder(LocalDate date, Order order)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException {
        validateOrderDetails(order);
        orderDao.editOrder(date, order);
    }
//******************************************************************************
    @Override
    public void removeOrder(LocalDate date, Order order)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException {
        orderDao.removeOrder(date, order.getNumber());
    }
//******************************************************************************    

    @Override
    public Order commitWork(LocalDate date, Order order)
            throws FlooringMasteryPersistenceException,
            NoOrderFoundException, InvalidInputException {
        return orderDao.createOrder(date, order);
    }
//****************************************************************************** 
    @Override
    public List<StateTax> getStateTaxes() 
            throws FlooringMasteryPersistenceException {
        return stateTaxDao.getAllTaxes();
    }
//****************************************************************************** 
    @Override
    public List<Product> getProducts() 
            throws FlooringMasteryPersistenceException {
        return productDao.getAllProducts();
    }
//****************************************************************************** 
    private void validateOrderDetails(Order order)
            throws InvalidInputException, FlooringMasteryPersistenceException {

        if (stateTaxDao.getTax(order.getTaxRate().getStateName()) == null) {
            throw new InvalidInputException("State Name Not Found");
        }

        if (productDao.getProduct(order.getProductType().getProductType()) == null) {
            throw new  InvalidInputException("Product Name NotFound");
        }
    }
//******************************************************************************       

    private int generateOrderNumbers() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("orderNumbers.txt")));

        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("-_- Could not Read fie into memory.", e);
        }
        String lastNumber = null;
        while (scanner.hasNextLine()) {
            lastNumber = scanner.nextLine();
        }
        scanner.close();
        int nextNumber = Integer.parseInt(lastNumber);
        nextNumber += 1;
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("orderNumbers.txt"));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(" Could not write to File");
        }
        out.println(nextNumber);
        out.flush();
        out.close();
        return nextNumber;
    }
//******************************************************************************
    

}
