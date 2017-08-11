/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.InvalidMoneyException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryView {

    private UserIO io;
    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
//******************************************************************************

    public int displayMenuSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
        io.print(" *  <<Flooring Program>>\n");
        io.print(" * 1. Display Orders\n");
        io.print(" * 2. Add an Order\n");
        io.print(" * 3. Edit an Order\n");
        io.print(" * 4. Remove an Order\n");
        io.print(" * 5. Save Current Work\n");
        io.print(" * 6. Quit\n");
        io.print(" * ");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt("PLEASE SELECT FROM THE ABOVE CHOICES: ", 1, 6);
    }
//******************************************************************************

    public void displayOrdersBanner() {
        io.print("\n*** DISPLAY ORDERS ***");
    }

    public void displayAddOrdersBanner() {
        io.print("\n*** ADD ORDERS ***");
    }

    public void displayAddOrdersSucessBanner() {
        io.print("\n*** ORDER CREATED SUCCESSFULLY ***");
    }

    public LocalDate displayRequestOrderDate() {
        return io.readLocalDate("What date would you like to display orders for (MMDDYYYY)?\n", "MMddyyyy");

    }

    public void displayOrderList(List<Order> orderList) {
        io.print("-----------------------------------------------------------------------");
        io.print("          LIST OF ORDERS FOR THE DATE ENTERED: \n");

        for (Order currentOrder : orderList) {
            io.print(currentOrder.getNumber() + ","
                    + currentOrder.getCustomerName() + ","
                    + currentOrder.getTaxRate().getStateName() + ","
                    + currentOrder.getTaxRate().getTaxRate() + ","
                    + currentOrder.getProductType().getProductType() + ","
                    + currentOrder.getProductType().getLaborCostPerSqFt() + ","
                    + currentOrder.getProductType().getCostPerSqFt() + ","
                    + currentOrder.getArea() + ","
                    + currentOrder.getMaterialCost() + ","
                    + currentOrder.getLaborCost() + ","
                    + currentOrder.getTax() + ","
                    + currentOrder.getTotal());
        }
        io.print("-----------------------------------------------------------------------");
    }
//******************************************************************************    

    public Order displayGetOrderInfo() throws InvalidMoneyException {
        io.print("        ENTER ORDER INFO   ");

        String customerName = io.readString("Please Enter Customer Name");
        String stateName = io.readString("Please Enter OH, PA, MI, or IN");
        String productType = io.readString("Please Enter Carpet, Laminate, Tile or Wood");
        BigDecimal area = io.readBigDecimal("Please Enter the Area ");

        Order currentOrder = new Order();

        currentOrder.setCustomerName(customerName);
        StateTax newStateTax = new StateTax(stateName);
        currentOrder.setTaxRate(newStateTax);

        Product newProduct = new Product(productType);
        currentOrder.setProductType(newProduct);
        currentOrder.setArea(area);

        return currentOrder;
    }

    public LocalDate displayDateBanner() {
        return io.readLocalDate("Please Enter Local Date ", "MMddyyyy");

    }

    public void displayOrderNotSaved() {
        io.print("Order Not Saved!");

    }
//******************************************************************************      

    public boolean commitOrder() {
        io.print("\n");
        return io.readBoolean("Do You Want To Commit Your Changes (Y/N)?");
    }
//******************************************************************************    

    public void displayExitBanner() {
        io.print("Thanks for being a awesome customer! PLEASE COME BACK.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("\n");
        io.print("====================== ERROR ==============================");
        io.print(errorMsg);
    }
//******************************************************************************

    public void displayEditOrdersBanner() {
        io.print("\n*** EDIT ORDERS ***");
    }

    public int displayEditingOptionBanner() {
        io.print("===What Component of the Order Would You like to Edit?===");
        io.print("1. Edit Order Date ");
        io.print("2. Edit Customer Name ");
        io.print("3. Edit State Name ");
        io.print("4. Edit Product Name");
        io.print("5. Edit the Area Value ");
        return io.readInt("PLEASE SELECT FROM THE ABOVE CHOICES: ", 1, 5);
    }

    public void editedOrderAttribute(LocalDate date, Order order)
            throws FlooringMasteryPersistenceException,
            InvalidMoneyException {
        boolean writeAgain = true;
        int userChoice = 0;

        while (writeAgain) {
            userChoice = displayEditingOptionBanner();
            switch (userChoice) {
                case 1:
                    LocalDate newDate = io.readLocalDate("Please Enter The New Date:", "MMddyyyy");
//                  date
                    break;
                case 2:
                    String newCustomerName = io.readString("Please Enter The New Customer Name ");
                    order.setCustomerName(newCustomerName);
                    break;
                case 3:
                    String newStateName = io.readString("Please Enter The New State Name :");
                    order.getTaxRate().setStateName(newStateName);
                case 4:
                    String newProductType = io.readString("Please Enter The New Product Name: ");
                    order.getProductType().setProductType(newProductType);
                case 5:
                    BigDecimal newArea = io.readBigDecimal("Please Enter The New Area Value");
                    order.setArea(newArea);
                case 6:
                    writeAgain = false;
                    break;
                default:
                    io.print("THIS IS NOT A VALID OPTION!!!!");
            }
        }
    }
//******************************************************************************

}
