/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.util.List;

/**
 *
 * @author n0200797
 */
public interface VendingMachineDao {

    List<Item> getAllItems()
            throws VendingMachinePersistenceException;

    Item getAnItem(int itemId)
            throws VendingMachinePersistenceException;

    Item updateItemQuantity(Item item)
            throws VendingMachinePersistenceException;

    Item addQuantity(int item)
            throws VendingMachinePersistenceException;

}
