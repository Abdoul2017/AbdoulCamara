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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author n0200797
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner inputReader = new Scanner(System.in);

    //
    @Override
    public void print(String msg) {
        System.out.println(msg);

    }

    @Override
    public double readDouble(String prompt) throws InvalidInputException {
        boolean badInput = false;
        double userInput = 0;
        do {
            try {

                System.out.println(prompt);
                String userInputString = inputReader.nextLine();
                userInput = Double.parseDouble(userInputString);
            } catch (InputMismatchException e) {
                badInput = true;
            }
        } while (badInput);
        return userInput;

    }

    @Override
    public double readDouble(String prompt, double min, double max) throws InvalidInputException {
        double userInput = 0.0;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            userInput = Double.parseDouble(userInputString);
        } while (userInput < min || userInput > max);
        return userInput;
    }

    @Override
    public float readFloat(String prompt) throws InvalidInputException {
        boolean badInput;
        int userInput = 0;
        do {
            try {

                System.out.println(prompt);
                String userInputString = inputReader.nextLine();
                userInput = (int) Float.parseFloat(userInputString);
                badInput = false;
            } catch (InputMismatchException e) {
                System.out.println("INPUT ERROR. YOU ENTERED AN INVALID INPUT");
                badInput = false;
                inputReader.nextLine();
            }

        } while (badInput);
        return userInput;

    }

    @Override
    public float readFloat(String prompt, float min, float max) throws InvalidInputException {
        float userInput = 0.0f;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            userInput = Float.parseFloat(userInputString);

        } while (userInput < min || userInput > max);
        return userInput;
    }

    @Override
    public int readInt(String prompt) throws InvalidInputException {
        boolean badInput;
        int userInput = 0;
        do {
            try {
                System.out.println(prompt);
                String userInputString = inputReader.nextLine();
                userInput = Integer.parseInt(userInputString);
                badInput = false;
            } catch (InputMismatchException e) {
                System.out.println("INPUT ERROR. YOU ENTERED AN INVALID INPUT");
                badInput = true;
//                inputReader.nextLine();
            }
        } while (badInput);
        return userInput;
    }

    @Override
    public int readInt(String prompt, int min, int max) throws InvalidInputException {
        int userInput = 0;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            userInput = Integer.parseInt(userInputString);

        } while (userInput < min || userInput > max);
        return userInput;
    }

    @Override
    public long readLong(String prompt) throws InvalidInputException {
        boolean badInput;
        long userInput = 0;
        do {
            try {
                System.out.println(prompt);
                String userInputString = inputReader.nextLine();
                userInput = Long.parseLong(userInputString);
                badInput = false;
            } catch (InputMismatchException e) {
                System.out.println("INPUT ERROR. YOU ENTERED AN INVALID INPUT");
                badInput = true;
//                inputReader.nextLine();
            }
        } while (badInput);
        return userInput;
    }

    @Override
    public long readLong(String prompt, long min, long max) throws InvalidInputException {
        long userInput = 0;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            userInput = Long.parseLong(userInputString);
        } while (userInput < min || userInput > max);
        return userInput;
    }

    @Override
    public String readString(String prompt)
            throws InvalidInputException {
        boolean badInput;
        String userInput = "";
        do {
            try {
                System.out.println(prompt);
                userInput = inputReader.nextLine();
                badInput = false;
            } catch (InputMismatchException e) {
                System.out.println("INPUT ERROR. YOU ENTERED AN INVALID INPUT");
                badInput = true;
//                inputReader.nextLine();
            }
        } while (badInput);

        return userInput;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt)
            throws InvalidInputException {
        String userInput = readString(prompt);
        try {
            BigDecimal bigDecimal = new BigDecimal(userInput);
            return bigDecimal;
        } catch (NumberFormatException e) {
            System.out.println("INPUT ERROR. YOU ENTERED AN INVALID INPUT");
        }
        return null;
    }

    @Override
    public LocalDate readLocalDate(String prompt, String format) {
        LocalDate ld = LocalDate.now();
        boolean badInput = false;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            badInput = false;
            try {
                ld = LocalDate.parse((userInputString), DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                System.out.println("Date Format ERROR. Please Enter A Date in " + format + " Format");
                badInput = true;
            }
        } while (badInput);
        return ld;
    }

    @Override
    public boolean readBoolean(String prompt) {
        System.out.println(prompt);
        String userInputString = inputReader.nextLine();
        if (userInputString.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }
//******************************************************************************
    @Override
    public String readState(String prompt) throws InvalidInputException {
        boolean badInput = false;
        String userInput = "";
        do {
            try {
                System.out.println(prompt);
                userInput = inputReader.nextLine();
                if (userInput.equalsIgnoreCase("OH") || userInput.equalsIgnoreCase("PA")
                        || userInput.equalsIgnoreCase("MI") || userInput.equalsIgnoreCase("IN")) {
                    badInput = false;
                    
                }else{
                    badInput = true;
                    System.out.println("===========The State Name you Enter is Incorrect. Please Enter Either: PA, OH, MI, and IN===========");
                }

            } catch (InputMismatchException e) {
                System.out.println("INPUT ERROR. YOU ENTERED AN INVALID INPUT");
                badInput = true;
//                inputReader.nextLine();
            }
        } while (badInput);

        return userInput;
    }
//******************************************************************************    

    @Override
    public String readProduct(String prompt) 
            throws InvalidInputException {
                boolean badInput = false;
        String userInput = "";
        do {
            try {
                System.out.println(prompt);
                userInput = inputReader.nextLine();
                if (userInput.equalsIgnoreCase("Carpet") || userInput.equalsIgnoreCase("Laminate")
                        || userInput.equalsIgnoreCase("Tile") || userInput.equalsIgnoreCase("Wood")) {
                    badInput = false;
                    
                }else{
                    badInput = true;
                    System.out.println("===========The Product Name . Please Enter Either:Carpet, Laminate, Tile or Wood===========");
                }

            } catch (InputMismatchException e) {
                System.out.println("INPUT ERROR. YOU ENTERED AN INVALID INPUT");
                badInput = true;
//                inputReader.nextLine();
            }
        } while (badInput);

        return userInput;
    }

}
