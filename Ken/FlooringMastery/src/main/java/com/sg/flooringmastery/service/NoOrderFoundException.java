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
public class NoOrderFoundException extends Exception {
    private String orderNotFound;

    public NoOrderFoundException(String orderNotFound) {
        this.orderNotFound =  orderNotFound;
        
    }
        public String getMessage(){
        return ("COULD NOT FIND THE ORDER NUMBER SELECTED!");
    }
        
    
}
