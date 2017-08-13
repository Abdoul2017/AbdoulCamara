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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryOrderDaoStubImpl 
        implements FlooringMasteryOrderDao{
    Order onlyOrder;
    List<Order> orderList = new ArrayList<>();
    
    public FlooringMasteryOrderDaoStubImpl(){
        
//******************************************************************************
    }
    
    @Override
    public Order createOrder(LocalDate date, Order order) 
            throws NoOrderFoundException, 
            FlooringMasteryPersistenceException, InvalidInputException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Order removeOrder(LocalDate date, Integer number) 
            throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void editOrder(LocalDate date, Order order) 
            throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate date) 
            throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Order retrieveOrdersByDateAndId(LocalDate date, Integer number) 
            throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void loadOrder(LocalDate date) 
            throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void saveOrder(LocalDate date) 
            throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Order getOrder(int number, LocalDate date) 
            throws FlooringMasteryPersistenceException, NoOrderFoundException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
//******************************************************************************    
}
