/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author n0200797
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getInventory()
            throws VendingMachinePersistenceException;
    
   
    public void validateMoney(BigDecimal clientMoney)
            throws InvalidClientMoneyException;
 
    public Change purchaseItem(int itemId)
            throws VendingMachinePersistenceException, 
            NoItemInventoryException, InsufficientFundsException, 
            InvalidItemSelectionException;

    public void addToClientMoney(BigDecimal money)
            throws InvalidClientMoneyException;

    public BigDecimal getClientMoney();

    public Change getChange();
    
    public boolean userHasMoney();
    
    public Item addQuantity(int itemId)
            throws VendingMachinePersistenceException;
   
    public void addMoney(String money);
    
    
    
}
