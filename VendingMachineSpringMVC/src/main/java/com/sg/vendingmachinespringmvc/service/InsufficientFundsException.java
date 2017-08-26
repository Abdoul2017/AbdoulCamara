/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

/**
 *
 * @author n0200797
 */
public class InsufficientFundsException extends Exception{

    public String getMessage(){
        return ("INSUFFICIENT FUNDS FOR THE SELECTED ITEM!!!"
                + "PLEASE CONSIDER SELECTING ANOTHER ITEM OR INSERT MORE MONEY.");
    }
}
