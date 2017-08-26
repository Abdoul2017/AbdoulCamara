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
public class InvalidItemSelectionException extends Exception {

    public String getMessage(){
        return ("YOU ENTERED AN INVALID ITEM SELECTION!!!!\n"
                +"PLEASE SELECT FROM THE LIST OF AVAILABLE ITEMS.");
    }
}
