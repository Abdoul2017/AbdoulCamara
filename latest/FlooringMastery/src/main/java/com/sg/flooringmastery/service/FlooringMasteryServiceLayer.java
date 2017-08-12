/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0200797
 */
public interface FlooringMasteryServiceLayer {

    public Order commitWork(LocalDate date, Order order)
            throws FlooringMasteryPersistenceException,
            NoOrderFoundException, InvalidInputException;
//******************************************************************************

    public List<Order> getOrders(LocalDate date)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException;
//******************************************************************************

    public Order createOrder(LocalDate date, Order order)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException;
//******************************************************************************

    public Order editOrder(LocalDate date, Order order)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException;
//******************************************************************************

    public void removeOrder(LocalDate date, Order order)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException;

//******************************************************************************
    public Order confirmOrderRemoval(LocalDate date, Order order)
            throws FlooringMasteryPersistenceException,
            NoOrderFoundException, InvalidInputException;
//******************************************************************************

    public Order commitEditChange(LocalDate date, Order order)
            throws FlooringMasteryPersistenceException,
            NoOrderFoundException, InvalidInputException;
//******************************************************************************        

    public Order getOrder(int ordernumber, LocalDate date)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException;
//******************************************************************************    
}
