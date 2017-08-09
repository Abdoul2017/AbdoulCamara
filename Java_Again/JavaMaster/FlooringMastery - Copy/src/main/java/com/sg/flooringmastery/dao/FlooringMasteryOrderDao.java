/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.NoOrderFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0200797
 */
public interface FlooringMasteryOrderDao {

    void createOrder(LocalDate date, Order order)
            throws FlooringMasteryPersistenceException;

    void removeOrder(LocalDate date, int number)
            throws FlooringMasteryPersistenceException;

    Order editOrder(LocalDate date, Order order)
            throws FlooringMasteryPersistenceException;

    List<Order> retrieveOrdersByDate(LocalDate date)
            throws FlooringMasteryPersistenceException;

    Order retrieveOrdersByDateAndId(LocalDate date, int number)
            throws FlooringMasteryPersistenceException;

    void load(LocalDate date)
            throws FlooringMasteryPersistenceException;

    void saveOrder(LocalDate date)
            throws FlooringMasteryPersistenceException;

//    List<Order> getAllOrders()
//            throws FlooringMasteryPersistenceException;
}