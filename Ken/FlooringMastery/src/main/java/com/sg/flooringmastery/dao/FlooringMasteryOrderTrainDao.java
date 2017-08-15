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

    @Override
    public void saveOrder(LocalDate date)
            throws FlooringMasteryPersistenceException {

    }

    @Override
    protected void removeOrderFile(LocalDate date) {
    }

    private int generateOrderNumber(LocalDate date) {
        int orderNumber = 1;
        if (orderMapMemory.containsKey(date)) {
            orderNumber = orderMapMemory.get(date).size() + 1;
        }
        return orderNumber;
    }
}
