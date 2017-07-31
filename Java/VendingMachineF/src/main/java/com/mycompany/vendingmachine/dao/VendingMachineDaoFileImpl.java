/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author n0200797
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
//******************************************************************************

    private Map<Integer, Item> itemMap = new HashMap<>();
    private boolean isItemsLoaded = false;

//*******************************ABSTRACT METHODS*******************************
    @Override
    public List<Item> getAllItems() throws 
            VendingMachinePersistenceException {
        loadItem();
        return new ArrayList<Item>(itemMap.values());
    }

    @Override
    public Item getAnItem(int itemId) throws 
            VendingMachinePersistenceException {
        loadItem();
        return itemMap.get(itemId);

    }

    @Override
    public Item updateItemQuantity(Item item) throws 
            VendingMachinePersistenceException {      
       item.removeAnItem();    
       Item newItem = itemMap.put(item.getItemId(), item);
       return newItem;
    }
//***************************MYLOAD*********************************************

    public void loadItem() throws VendingMachinePersistenceException {
        if(!isItemsLoaded) {
            Scanner scanner;

            try {

                scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));

            } catch (FileNotFoundException e) {
                throw new VendingMachinePersistenceException("-_- Could not load item data into memory.", e);
            }
            String currentLine;
            String[] currentTokens;

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();

                currentTokens = currentLine.split(DELIMITER);

                Item snack = new Item(Integer.parseInt(currentTokens[0]));
                snack.setName(currentTokens[1]);
                snack.setPrice(new BigDecimal(currentTokens[2]));
                snack.setQuantity(Integer.parseInt(currentTokens[3]));

                itemMap.put(snack.getItemId(), snack);                

            }
            scanner.close();
            isItemsLoaded = true;
        }
    }
//***************************MY WRITER*****************************************
    
    public void saveItemsToFile() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save item to inventory file.", e);
        }
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            out.println(currentItem.getItemId() + DELIMITER
                    + currentItem.getName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getQuantity());
            out.flush();
        }
        out.close();
    }

}
