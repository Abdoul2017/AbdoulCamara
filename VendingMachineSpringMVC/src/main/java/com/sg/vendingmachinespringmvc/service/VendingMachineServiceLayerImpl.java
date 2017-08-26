/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author n0200797
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    BigDecimal clientMoney;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
        this.clientMoney = BigDecimal.ZERO;
    }

    @Override
    public List<Item> getInventory() throws
            VendingMachinePersistenceException {
        List<Item> allItems = dao.getAllItems();
        List<Item> availableItems = new ArrayList<Item>();
        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i).getQuantity() > 0) {
                availableItems.add(allItems.get(i));
            }
        }
        return availableItems;
    }
//******************************************************************************

    @Override
    public void validateMoney(BigDecimal clientMoney) throws InvalidClientMoneyException {

        if (clientMoney.scale() > 2) {
            throw new InvalidClientMoneyException(clientMoney.toString());
        } else if (clientMoney.compareTo(BigDecimal.ZERO) != 1) {
            throw new InvalidClientMoneyException(clientMoney.toString());
        } else if (clientMoney.scale() == 2 && !clientMoney.remainder(new BigDecimal(".05")).setScale(2).equals(new BigDecimal("0.00"))) {
            throw new InvalidClientMoneyException(clientMoney.toString());
        }
    }
//******************************************************************************

    @Override
    public Change purchaseItem(int itemId)
            throws VendingMachinePersistenceException, NoItemInventoryException,
            InsufficientFundsException, InvalidItemSelectionException {
        Item userSelection = dao.getAnItem(itemId);
        if (userSelection == null) {
            throw new InvalidItemSelectionException();
        }
        if (userSelection.getQuantity() == 0) {
            throw new NoItemInventoryException();
        }
        if (userSelection.getPrice().compareTo(clientMoney) > 0) {
            throw new InsufficientFundsException();
        }

        clientMoney = clientMoney.subtract(userSelection.getPrice());
        dao.updateItemQuantity(userSelection);

        return getChange();
    }
//***************************ADD MONEY *****************************************

    @Override
    public void addToClientMoney(BigDecimal money) throws
            InvalidClientMoneyException {
        validateMoney(money);
        clientMoney = clientMoney.add(money);
    }

    @Override
    public BigDecimal getClientMoney() {
        return clientMoney;
    }

    @Override
    public Change getChange() {
        Double doubleChange = clientMoney.doubleValue() * 100;
        int totalChangeInPennies = doubleChange.intValue();
        Change change = new Change(totalChangeInPennies);
        change.calcChange();
        resetClientMoney();
        return change;
    }

    private void resetClientMoney() {
        clientMoney = BigDecimal.ZERO;
    }

    @Override
    public boolean userHasMoney() {
        return getClientMoney().compareTo(BigDecimal.ZERO) == 0;

    }

    @Override
    public Item addQuantity(int itemId)
            throws VendingMachinePersistenceException {
        return dao.addQuantity(itemId);

    }

    @Override
    public void addMoney(String total) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
