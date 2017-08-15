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
public class InvalidInputValidation extends Exception{
        private String userInput;
        public InvalidInputValidation (String userInput){
        this.userInput = userInput;
    }
    
    public String getMessage(){
        return ("YOU ENTERED AN INVALID INPUT." + userInput + " IS NOT VALID!!!!! \n");
    }
    
}
