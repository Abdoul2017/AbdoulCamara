/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author n0200797
 */
public class InvalidInputException extends Exception{
    private String money;
        public InvalidInputException(String money){
        this.money = money;
    }
    
    public String getMessage(){
        return ("YOU ENTERED AN INVALID DATA." + money + " IS NOT VALID!!!!! \n");
    }
    
}
