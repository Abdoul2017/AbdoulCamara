/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.InvalidInputException;
import com.sg.flooringmastery.service.InvalidSelectionException;
import com.sg.flooringmastery.service.NoOrderFoundException;
import com.sg.flooringmastery.ui.FlooringMasteryView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryController {

    private FlooringMasteryView view;
    private FlooringMasteryServiceLayer service;

    public FlooringMasteryController(FlooringMasteryServiceLayer service,
            FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }
//******************************************************************************

    public void run() throws NoOrderFoundException, InvalidInputException {        
        boolean keepGoing = true;
        int menuSelection;
        try {
            while (keepGoing) {
                try {
                    menuSelection = getMenuSelection();
                    switch (menuSelection) {
                        case 1:
                            displayOrders();
                            break;
                        case 2:
                            addOrders();
                            break;
                        case 3:
                            editOrders();
                            break;

                        case 4:
                            removeOrders();
                            break;
                        case 5:

                            break;
                        case 6:
                            keepGoing = false;
                            break;
                        default:
                    }
                } catch (InvalidSelectionException e) {
                    view.displayErrorMessage(e.getMessage());
                }
            }
            exitMessage();
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
//******************************************************************************

    private int getMenuSelection()
            throws FlooringMasteryPersistenceException,
            InvalidSelectionException,
            InvalidInputException {
        return view.displayMenuSelection();
    }
//******************************************************************************  

    private void exitMessage() {
        view.displayExitBanner();
    }
//******************************************************************************

    private void displayOrders()
            throws
            FlooringMasteryPersistenceException {
        try {
            view.displayOrdersBanner();
            LocalDate date = view.displayRequestOrderDate();
            view.displayOrderList(service.getOrders(date));
        } catch (NoOrderFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
//******************************************************************************

    private void addOrders()
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException, InvalidInputException {
        LocalDate date = null;
        Order order = null;
        boolean hasErrors = false;
        do {
            try {
                view.displayAddOrdersBanner();
                date = view.displayDateBanner();
                order = view.displayGetOrderInfo(service.getStateTaxes(), service.getProducts());
                service.createOrder(date, order);
            } catch (FlooringMasteryPersistenceException | NoOrderFoundException e) {
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
        if (view.commitOrder()) {
            service.commitWork(date, order);
            view.displayAddOrdersSucessBanner();
        } else {
            view.displayOrderNotSaved();
        }
    }
//******************************************************************************

    private void editOrders()
            throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException {
        LocalDate date = null;
        Order orderToUpdate = null;
        Order updatedOrder = null;
        int ordernumber = 0;
        boolean hasErrors = true;
        List<Order>  orders = null;

        do {
            try {
                view.displayEditOrdersBanner();
                date = view.displayDateBanner();
                orders = service.getOrders(date);
                if(orders == null || orders.isEmpty()) {
                    throw new NoOrderFoundException("");
                }
                view.displayOrderList(service.getOrders(date));
                hasErrors = false;
            } catch (FlooringMasteryPersistenceException | NoOrderFoundException e) {
                view.displayErrorMessage(e.getMessage());
            }
        } while(hasErrors);

        hasErrors = true;
        do {
            try {
                ordernumber = view.displayReadOrderNumber();
                orderToUpdate = service.getOrder(ordernumber, date);
                updatedOrder = editOrderAttributes(date, orderToUpdate);
                hasErrors = false;
            } catch (FlooringMasteryPersistenceException | NoOrderFoundException e) {
                view.displayErrorMessage(e.getMessage());
            }

        } while (hasErrors);

        if(updatedOrder.getDate() != date || !updatedOrder.getCustomerName().equals(orderToUpdate.getCustomerName())
                || !updatedOrder.getTaxRate().getStateName().equals(orderToUpdate.getTaxRate().getStateName())
                || !updatedOrder.getProductType().getProductType().equals(orderToUpdate.getProductType().getProductType())
                || updatedOrder.getArea().compareTo(orderToUpdate.getArea()) != 0) {
            if(view.confirmCommitEditChange()) {
                service.editOrder(date, updatedOrder);
                view.displayOrderUpdatedSucessBanner();
            } else {
                view.displayOrderNotSaved();
            }
        } else {
            view.displayOrderNotSaved();
        }
    }
//******************************************************************************
    private Order editOrderAttributes(LocalDate date, Order order) {
        int userChoice = 0;
        boolean writeAgain = true;
        Order updatedOrder = copyOrder(order);

        while (writeAgain) {
            try {
                userChoice = view.displayEditingOptionBanner();
                switch (userChoice) {
                    case 1:
                        LocalDate newDate = view.displayDateBanner(date);
                        if (!newDate.equals("")) {
                            updatedOrder.setDate(newDate);
                        }
                        break;
                    case 2:
                        String newCustomerName = view.displayCustomerNameBanner();
                        if (newCustomerName.equalsIgnoreCase("")) {
                            view.displayErrorMessage("************New Customer Name Cannot Be Blank!************");
                        } else {
                            updatedOrder.setCustomerName(newCustomerName);
                        }
                        break;
                    case 3:
                        String newStateName = view.displayStateTaxBanner(order.getTaxRate());
                        if (newStateName.equalsIgnoreCase("")) {
                            view.displayErrorMessage("************State Name Cannot Be Blank! Please Choose From the Following:  OH, PA, MI, or IN************");
                        } else {
                            StateTax stateTax = new StateTax(newStateName);
                            updatedOrder.setTaxRate(stateTax);
                        }
                        break;

                    case 4:
                        String newProductType = view.displayProductNameBanner(order.getProductType());
                        if (newProductType.equals("")) {
                            view.displayErrorMessage("************Product Name Cannot Be Blank! Please Choose from: Carpet, Laminate, Tile or Wood************");
                        } else {
                            Product product = new Product(newProductType);
                            updatedOrder.setProductType(product);
                        }
                        break;
                    case 5:
                        BigDecimal newArea = view.displayAreaBanner(order.getArea());
                        if (!newArea.equals("")) {
                            updatedOrder.setArea(newArea);
                        }
                    case 6:
                        writeAgain = false;
                        break;
                    default:
                        view.displayErrorMessage("THIS IS NOT A VALID OPTION!!!!");
                }
            } catch(InvalidInputException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
        return updatedOrder;
    }
//****************************************************************************** 

    private void removeOrders() throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidInputException {
        LocalDate date = null;
        Order orderToRemove = null;
        int ordernumber = 0;
        boolean hasErrors = false;
        do {
            try {
                view.displayRemoverderBanner();
                date = view.displayDateBanner();
                view.displayOrderList(service.getOrders(date));
                ordernumber = view.displayReadOrderNumberToRemove();
                orderToRemove = service.getOrder(ordernumber, date);

            } catch (FlooringMasteryPersistenceException | NoOrderFoundException e) {
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
        if (view.confirmOrderRemoval()) {
            service.removeOrder(date, orderToRemove);
            view.displayOrderRemovedSucessBanner();
        } else {
            view.displayOrderNotRemoved();
        }
    }
//******************************************************************************
    private Order copyOrder(Order order) {
        Order copiedOrder = new Order();
        copiedOrder.setNumber(order.getNumber());
        copiedOrder.setDate(order.getDate());
        copiedOrder.setCustomerName(order.getCustomerName());
        copiedOrder.setTaxRate(order.getTaxRate());
        copiedOrder.setProductType(order.getProductType());
        copiedOrder.setArea(order.getArea());
        return copiedOrder;
    }
//******************************************************************************    
}
