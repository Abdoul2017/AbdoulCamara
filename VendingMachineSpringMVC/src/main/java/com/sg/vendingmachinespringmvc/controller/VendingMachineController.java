/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author n0200797
 */
@Controller
public class VendingMachineController {

    VendingMachineServiceLayer service;
//    BigDecimal total = new BigDecimal("0.00");
//    int itemId;
//    String message = "";
//    int quarter;
//    int dime;
//    int nickel;
//    int penny;

    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }
//***********************LOAD INVENTORY*****************************************

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayAllItems(Model model)
            throws VendingMachinePersistenceException {

        List<Item> itemList = new ArrayList<>();

        itemList = service.getInventory();

        model.addAttribute("items", itemList);

        return "index";
    }
//************************MAKE ITEM SELECTION***********************************

    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request, Model model)
            throws VendingMachinePersistenceException {

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        model.addAttribute("id", itemId);

        List<Item> itemList = new ArrayList<>();
        itemList = service.getInventory();
        model.addAttribute("items", itemList);

        return "index";
    }
//*******************************INSERT MONEY***********************************

    @RequestMapping(value = "/insertMoney", method = RequestMethod.POST)
    public String insertMoney(HttpServletRequest request, Model model)
            throws VendingMachinePersistenceException {

        BigDecimal total = new BigDecimal("0.00");

        int quarter = 0;
        int dime = 0;
        int nickel = 0;
        int penny = 0;

        if (request.getParameter("addDollar") != null) {
            BigDecimal addDollar = new BigDecimal(request.getParameter("addDollar"));
            total = total.add(addDollar).setScale(2, RoundingMode.HALF_UP);

        }
        if (request.getParameter("addQuarter") != null) {
            BigDecimal addQuarter = new BigDecimal(request.getParameter("addQuarter"));
            total = total.add(addQuarter).setScale(2, RoundingMode.HALF_UP);
        }
        if (request.getParameter("addDime") != null) {
            BigDecimal addDime = new BigDecimal(request.getParameter("addDime"));
            total = total.add(addDime).setScale(2, RoundingMode.HALF_UP);
        }
        if (request.getParameter("addNickel") != null) {
            BigDecimal addNickel = new BigDecimal(request.getParameter("addNickel"));
            total = total.add(addNickel).setScale(2, RoundingMode.HALF_UP);
        }

        model.addAttribute("quarter", quarter);
        model.addAttribute("dime", dime);
        model.addAttribute("nickel", nickel);
        model.addAttribute("penny", penny);
        model.addAttribute("total", total);

        List<Item> itemList = new ArrayList<>();
        itemList = service.getInventory();
        model.addAttribute("items", itemList);
        

        return "index";
    }
//**************************MAKE PURCHASE PLACEHOLDER***************************

    @RequestMapping(value = "/purchaseItem", method = RequestMethod.GET)
    public String purchaseItem(HttpServletRequest request, Model model)
            throws InsufficientFundsException, NoItemInventoryException {

        return "index";
    }
//**************************GET CHANGE PLACEHOLDER******************************    
}
