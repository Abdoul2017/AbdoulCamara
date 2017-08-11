/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author n0200797
 */
public class FlooringMasteryStateTaxDaoFileImpl implements FlooringMasteryStateTaxDao {

    public static final String STATETAXES_FILE = "stateTax.txt";
    public static final String DELIMITER = ",";

    HashMap<String, StateTax> stateTaxMap = new HashMap<>();
    private boolean isTaxLoaded = false;

//******************************************************************************
    @Override
    public StateTax getTax(String stateName)
            throws FlooringMasteryPersistenceException {
        loadStateTax();
        return stateTaxMap.get(stateName);
    }

    @Override
    public List<StateTax> getAllTaxes()
            throws FlooringMasteryPersistenceException {
        return new ArrayList<StateTax>(stateTaxMap.values());
    }

//******************************************************************************
    private void loadStateTax() throws FlooringMasteryPersistenceException {
        if (!isTaxLoaded) {
            Scanner scanner;

            try {
                scanner = new Scanner(new BufferedReader(new FileReader(STATETAXES_FILE)));

            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceException("-_- Could not load State Tax data into memory.", e);
            }
            String currentLine;
            String[] currentTokens;
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);

                StateTax newTax = new StateTax(currentTokens[0]);

                newTax.setTaxRate(new BigDecimal(currentTokens[1]));

                stateTaxMap.put(newTax.getStateName(), newTax);
            }
            scanner.close();
            isTaxLoaded = true;
        }
    }

}
