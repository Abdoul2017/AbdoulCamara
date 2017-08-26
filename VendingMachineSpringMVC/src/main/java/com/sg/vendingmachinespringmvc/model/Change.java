/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

/**
 *
 * @author n0200797
 */
public class Change {
    private int totalChangeInPennies;
    private int pennies;
    private int quarters;
    private int dimes;
    private int nickels;

//*******************PLACEHOLDER FOR MY CONSTRUCTOR*****************************
    public Change(int totalChangeInPennies) {
        this.totalChangeInPennies = totalChangeInPennies;
    }
    public void calcChange() {
        int remainder;
        pennies = totalChangeInPennies;

        quarters = (pennies - (pennies % 25)) / 25;
        remainder = pennies % 25;
        pennies = remainder;
        
        dimes = (pennies - (pennies % 10)) / 10;
        remainder = pennies % 10;
        pennies = remainder;

        nickels = (pennies - (pennies % 5)) / 5;
        remainder = pennies % 5;
        pennies = remainder;

    }

//**********************MY GETTERS - NO SETTERS NEEDED**************************

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getPennies() {
        return pennies;
    }

    public int getNickels() {
        return nickels;
    }
//******************************************************************************

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.totalChangeInPennies;
        hash = 83 * hash + this.pennies;
        hash = 83 * hash + this.quarters;
        hash = 83 * hash + this.dimes;
        hash = 83 * hash + this.nickels;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (this.totalChangeInPennies != other.totalChangeInPennies) {
            return false;
        }
        if (this.pennies != other.pennies) {
            return false;
        }
        if (this.quarters != other.quarters) {
            return false;
        }
        if (this.dimes != other.dimes) {
            return false;
        }
        if (this.nickels != other.nickels) {
            return false;
        }
        return true;
    }
    
}
