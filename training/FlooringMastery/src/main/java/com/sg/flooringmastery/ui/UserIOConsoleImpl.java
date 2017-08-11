/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.service.InvalidMoneyException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    public double readDouble(String prompt) {
        System.out.println(prompt);
        String userInputString = inputReader.nextLine();
        double userInput = Double.parseDouble(userInputString);
        return userInput;

    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double userInput = 0.0;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            userInput = Double.parseDouble(userInputString);
        } while (userInput < min || userInput > max);
        return userInput;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        String userInputString = inputReader.nextLine();
        float userInput = Float.parseFloat(userInputString);
        return userInput;

    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float userInput = 0.0f;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            userInput = Float.parseFloat(userInputString);

        } while (userInput < min || userInput > max);
        return userInput;
    }

    @Override
    public int readInt(String prompt) {
        int userInput = 0;
        System.out.println(prompt);
        String userInputString = inputReader.nextLine();
        userInput = Integer.parseInt(userInputString);
        return userInput;

    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int userInput = 0;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            userInput = Integer.parseInt(userInputString);

        } while (userInput < min || userInput > max);
        return userInput;
    }

    @Override
    public long readLong(String prompt) {
        long userInput = 0;
        System.out.println(prompt);
        String userInputString = inputReader.nextLine();
        userInput = Long.parseLong(userInputString);
        return userInput;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long userInput = 0;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            userInput = Long.parseLong(userInputString);
        } while (userInput < min || userInput > max);
        return userInput;
    }

    @Override
    public String readString(String prompt) {
        String userInput = "";
        System.out.println(prompt);
        userInput = inputReader.nextLine();
        return userInput;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt)
            throws InvalidMoneyException {
        String userInput = readString(prompt);
        try {
            BigDecimal bigDecimal = new BigDecimal(userInput);
            return bigDecimal;
        } catch (NumberFormatException e) {
            throw new InvalidMoneyException(userInput);
        }
    }

    @Override
    public LocalDate readLocalDate(String prompt, String format) {
        LocalDate ld = LocalDate.now();
        boolean hasErrors = false;
        do {
            System.out.println(prompt);
            String userInputString = inputReader.nextLine();
            hasErrors = false;
            try {
                ld = LocalDate.parse((userInputString), DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                System.out.println("Date Format ERROR. Please Enter A Date in " + format + " Format");
                hasErrors = true;
            }
        } while (hasErrors);
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
}
