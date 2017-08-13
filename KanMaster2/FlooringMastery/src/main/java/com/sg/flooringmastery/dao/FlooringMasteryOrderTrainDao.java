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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryOrderTrainDao extends FlooringMasteryOrderDaoFileImpl {

    public static final String DELIMITER = ",";
    HashMap<LocalDate, HashMap<Integer, Order>> orderMapMemory = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    Integer number = 0;

//******************************************************************************
    @Override
    public Order createOrder(LocalDate date, Order order)
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException {

        if (orderMapMemory.get(date) == null) {
            try {
                loadOrder(date);
            } catch (FlooringMasteryPersistenceException e) {
                HashMap<Integer, Order> newMap = new HashMap<>();
                newMap.put(order.getNumber(), order);
                orderMapMemory.put(date, newMap);
            }
        }
        order = orderMapMemory.get(date).put(order.getNumber(), order);
        saveOrder(date);
        return order;
    }
//******************************************************************************

    @Override
    public Order removeOrder(LocalDate date, Integer number)
            throws FlooringMasteryPersistenceException {
        loadOrder(date);
        Order order = orderMapMemory.get(date).remove(number);
        if(orderMapMemory.get(date).isEmpty()) {
            orderMapMemory.remove(date);
            removeOrderFile(date);
        } else {
            saveOrder(date);
        }
        return order;
    }
//******************************************************************************
    @Override
    public void editOrder(LocalDate date, Order order)
            throws FlooringMasteryPersistenceException {
    	 Order editedOrder=null;
     	if(orderMapMemory.get(date)==null) {
     		
                 HashMap<Integer, Order> newMap = new HashMap<>();
                 newMap.put(order.getNumber(), order);
                 orderMapMemory.put(date, newMap);
             }
     		editedOrder=orderMapMemory.get(date).put(order.getNumber(), order);

         try{
                
         	saveOrder(date);
         }
        
         catch(FlooringMasteryPersistenceException e){
             if(e.getMessage().equals("COULD NOT FIND THE ORDER.")) {
            	 
            	 saveOrder(date);
             }
             else throw e;
            }
        // return editedOrder;
            }

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate date)
            throws FlooringMasteryPersistenceException {
        loadOrder(date);
        if (orderMapMemory.get(date) != null) {
            return new ArrayList<Order>(orderMapMemory.get(date).values());
        }
        return null;
    }
//******************************************************************************

    @Override
    public Order getOrder(int number, LocalDate date)
            throws FlooringMasteryPersistenceException,
            NoOrderFoundException {
        try {
            loadOrder(date);
        } catch (FlooringMasteryPersistenceException e) {
            throw new FlooringMasteryPersistenceException("Could not get Order");
        }

        if (!orderMapMemory.get(date).containsKey(number)) {
            throw new NoOrderFoundException(" Order Not Found");
        } else {
            return orderMapMemory.get(date).get(number);
        }
    }
//******************************************************************************

    @Override
    public Order retrieveOrdersByDateAndId(LocalDate date, Integer number)
            throws FlooringMasteryPersistenceException {
        loadOrder(date);
        if (orderMapMemory.get(date) != null) {
            return orderMapMemory.get(date).get(number);
        }
        return null;
    }
//******************************************************************************
    @Override
    public void loadOrder(LocalDate date)
            throws FlooringMasteryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Orders_" + date.format(formatter) + ".txt")));

        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("-_- COULD NOT LOAD ORDER DATA FROM MEMORY."
                    + "The Order Date You Entered May Not Exist!", e);
        }

        String currentLine;
        String[] currentTokens;

        HashMap<Integer, Order> newMap = new HashMap<>();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Order newOrder = new Order(Integer.parseInt(currentTokens[0]));

            newOrder.setCustomerName(currentTokens[1]);

            StateTax newStateTax = new StateTax(currentTokens[2]);
            newStateTax.setTaxRate(new BigDecimal(currentTokens[3]));
            newOrder.setTaxRate(newStateTax);

            Product newProduct = new Product(currentTokens[4]);
            newProduct.setLaborCostPerSqFt(new BigDecimal(currentTokens[5]));
            newProduct.setCostPerSqFt(new BigDecimal(currentTokens[6]));
            newOrder.setProductType(newProduct);

            newOrder.setArea(new BigDecimal(currentTokens[7]));
            newOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            newOrder.setLaborCost(new BigDecimal(currentTokens[9]));

            newOrder.setTax(new BigDecimal(currentTokens[10]));
            newOrder.setTotal(new BigDecimal(currentTokens[11]));
            newOrder.setDate(date);

            orderMapMemory.put(date, newMap);
            newMap.put(newOrder.getNumber(), newOrder);

        }
        scanner.close();
    }
//******************************************************************************

    private void removeOrderFile(LocalDate date) {
        File orderFile = new File("Orders_" + date.format(formatter) + ".txt");
        orderFile.delete();
    }

    private int generateOrderNumber(LocalDate date) {
        int orderNumber = 1;
        if(orderMapMemory.containsKey(date)) {
            orderNumber = orderMapMemory.get(date).size() + 1;
        }
        return orderNumber;
    }
}

