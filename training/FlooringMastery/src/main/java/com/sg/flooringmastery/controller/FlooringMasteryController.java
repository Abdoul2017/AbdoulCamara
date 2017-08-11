/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.InvalidMoneyException;
import com.sg.flooringmastery.service.InvalidSelectionException;
import com.sg.flooringmastery.service.NoOrderFoundException;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;

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

    public void run() throws NoOrderFoundException, InvalidMoneyException {
        int menuSelection;
        boolean keepGoing = true;
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
            InvalidSelectionException {
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
            FlooringMasteryPersistenceException, InvalidMoneyException {
        LocalDate date = null;
        Order order = null;
        boolean hasErrors = false;
        do {
            try {
                view.displayAddOrdersBanner();
                date = view.displayDateBanner();
                order = view.displayGetOrderInfo();
                service.createOrder(date, order);
            } catch (FlooringMasteryPersistenceException e) {
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
            InvalidMoneyException {
        LocalDate date = null;
        Order orderToUpdate = null;
        int ordernumber = 0;
        boolean hasErrors = false;
        LocalDate editedDate = null; 
        do {
            try {
                view.displayEditOrdersBanner();
                date = view.displayDateBanner();
                view.displayOrderList(service.getOrders(date));
                ordernumber = view.displayReadOrderNumber();//Need to validate this
                orderToUpdate = service.getOrder(ordernumber, date);
                view.editedOrderAttribute(date, orderToUpdate);
            } catch (FlooringMasteryPersistenceException e) {
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
        if (view.confirmCommitEditChange()) {
            service.editOrder(date, orderToUpdate);
            view.displayOrderUpdatedSucessBanner();
        } else {
            view.displayEditNotSaved();
        }
    }
//******************************************************************************  

    private void removeOrders() throws NoOrderFoundException,
            FlooringMasteryPersistenceException,
            InvalidMoneyException {
        LocalDate date = null;
        Order orderToRemove = null;
        int ordernumber = 0;
        boolean hasErrors = false;
        do {
            try {
                view.displayRemoverderBanner();
                date = view.displayDateBanner();
                view.displayOrderList(service.getOrders(date));
                ordernumber = view.displayReadOrderNumber();
                orderToRemove = service.getOrder(ordernumber, date);

            } catch (FlooringMasteryPersistenceException e) {
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
}
