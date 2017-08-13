/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.service.FlooringMasteryServiceLayerFileImpl;
import com.sg.flooringmastery.service.InvalidInputException;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author n0200797
 */
public interface UserIO {

    void print(String msg);

    double readDouble(String prompt)throws InvalidInputException;

    double readDouble(String prompt, double min, double max)throws InvalidInputException;

    float readFloat(String prompt)throws InvalidInputException;

    float readFloat(String prompt, float min, float max)throws InvalidInputException;

    int readInt(String prompt)throws InvalidInputException;

    int readInt(String prompt, int min, int max)throws InvalidInputException;

    long readLong(String prompt)throws InvalidInputException;

    long readLong(String prompt, long min, long max)
            throws InvalidInputException;

    String readString(String prompt)
            throws InvalidInputException;
            

    BigDecimal readBigDecimal(String prompt)
            throws InvalidInputException;
    
    LocalDate readLocalDate(String prompt, String format);
    
    boolean readBoolean(String prompt);
    
    String readState(String prompt)throws InvalidInputException;
    
    String readProduct(String prompt)throws InvalidInputException;

}
