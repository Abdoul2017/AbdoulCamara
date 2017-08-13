/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.InvalidInputException;
import com.sg.flooringmastery.service.NoOrderFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0200797
 */
public interface FlooringMasteryOrderDao {

    Order createOrder(LocalDate date, Order order)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException;

    Order removeOrder(LocalDate date, Integer number)
            throws FlooringMasteryPersistenceException;

    void editOrder(LocalDate date, Order order)
            throws FlooringMasteryPersistenceException;

    List<Order> retrieveOrdersByDate(LocalDate date)
            throws FlooringMasteryPersistenceException;

    Order retrieveOrdersByDateAndId(LocalDate date, Integer number)
            throws FlooringMasteryPersistenceException;

    void loadOrder(LocalDate date)
            throws FlooringMasteryPersistenceException;

    void saveOrder(LocalDate date)
            throws FlooringMasteryPersistenceException;

    Order getOrder(int number, LocalDate date)
            throws FlooringMasteryPersistenceException, 
            NoOrderFoundException;
}

