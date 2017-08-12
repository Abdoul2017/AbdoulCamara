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
public class InvalidSelectionException extends Exception {

    public String getMessage() {
        return ("YOU ENTERED AN INVALID SELECTION!!!!\n"
                + "PLEASE SELECT FROM THE LIST OF AVAILABLE OPTIONS.");
    }

}
