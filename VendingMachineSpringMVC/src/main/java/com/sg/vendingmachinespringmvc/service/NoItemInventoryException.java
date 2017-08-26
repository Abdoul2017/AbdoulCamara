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
public class NoItemInventoryException extends Exception{
    public String getMessage(){
        return ("WE RAN OUT OF THE ITEM YOU SELECTED!!!!"
                + "PLEASE CONSIDER SELECTING ANOTHER ITEM.");
    }
}
