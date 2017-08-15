/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.InvalidInputException;
import com.sg.flooringmastery.service.NoOrderFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        StateTax onlyStateTax = new StateTax("OH");
        onlyStateTax.setStateName("OH");
        onlyStateTax.setTaxRate(BigDecimal.valueOf(6.25));
        Product onlyProduct = new Product("Tile");
        onlyProduct.setProductType("Tile");
        onlyProduct.setCostPerSqFt(BigDecimal.valueOf(15.5));
        onlyProduct.setLaborCostPerSqFt(BigDecimal.valueOf(25));

        onlyOrder = new Order();
        onlyOrder.setDate(LocalDate.parse("01012017", DateTimeFormatter.ofPattern("MMddyyyy")));
        onlyOrder.setNumber(1);
        onlyOrder.setArea(new BigDecimal("20"));
        onlyOrder.setCustomerName("Test");
        onlyOrder.setTaxRate(onlyStateTax);
        onlyOrder.setProductType(onlyProduct);

        orderList.add(onlyOrder);
    }
    
    @Override
    public Order createOrder(LocalDate date, Order order) 
            throws NoOrderFoundException, 
            FlooringMasteryPersistenceException, InvalidInputException {
        orderList.add(order);
        return order;
    }

    @Override
    public Order removeOrder(LocalDate date, Integer number) 
            throws FlooringMasteryPersistenceException {
        if(date.compareTo(onlyOrder.getDate()) == 0 && onlyOrder.getNumber() == number) {
            return orderList.remove(0);
        } else {
            return null;
        }
    }

    @Override
    public void editOrder(LocalDate date, Order order) 
            throws FlooringMasteryPersistenceException {
        if(date.compareTo(onlyOrder.getDate()) == 0 && order.getNumber() == onlyOrder.getNumber()) {
            onlyOrder = order;
        }
    }

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate date) 
            throws FlooringMasteryPersistenceException {
        if(date.compareTo(onlyOrder.getDate()) == 0) {
            return orderList;
        } else {
            return null;
        }
    }

    @Override
    public Order getOrder(int number, LocalDate date) 
            throws FlooringMasteryPersistenceException, NoOrderFoundException {
        if(date.compareTo(onlyOrder.getDate()) == 0 && onlyOrder.getNumber() == number) {
            return onlyOrder;
        } else {
            return null;
        }
    }
//******************************************************************************    
}
