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
public class InvalidClientMoneyException extends Exception {
    private String money;
    
    public InvalidClientMoneyException(String money){
        this.money = money;
    }
    
    public String getMessage(){
        return ("YOU ENTERED AN INVALID AMOUNT." + money + " IS NOT VALID!!!!! \n" 
                +"PLEASE BE AWARE THAT PENNIES ARE NOT ACCEPTED.");
    }
}
