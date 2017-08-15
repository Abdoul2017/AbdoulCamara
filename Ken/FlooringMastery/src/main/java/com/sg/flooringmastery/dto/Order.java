/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author n0200797
 */
public class Order {

    private Integer number;
    private String customerName;
    String OrderNumber;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StateTax getTaxRate() {
        return taxRate;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public void setTaxRate(StateTax taxRate) {
        this.taxRate = taxRate;
    }

    public Product getProductType() {
        return productType;
    }

    public void setProductType(Product productType) {
        this.productType = productType;
    }

    private StateTax taxRate;
    private Product productType;

    private BigDecimal area;

//******************************************************************************

    public Order(Integer number) {
        this.number = number;
    }
//******************************************************************************    

    public Order() {
    }
//********************SETTERS***************************************************    

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }
//**************************GETTERS*********************************************

    public Integer getNumber() {
        return number;
    }

    public BigDecimal getArea() {
        return area;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
//******************************************************************************    
    public BigDecimal getMaterialCost() {
        return productType.getCostPerSqFt().multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborCost() {
        return  productType.getLaborCostPerSqFt().multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTax() {
        BigDecimal costBeforeTax = getMaterialCost().add(getLaborCost());
        return (taxRate.getTaxRate().divide(new BigDecimal("100")).multiply(costBeforeTax).setScale(2, RoundingMode.HALF_UP));
    }

    public BigDecimal getTotal() {
        return getMaterialCost().add(getLaborCost()).add(getTax()).setScale(0, RoundingMode.HALF_UP);
    }
//******************************************************************************

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.number);
        hash = 23 * hash + Objects.hashCode(this.customerName);
        hash = 23 * hash + Objects.hashCode(this.OrderNumber);
        hash = 23 * hash + Objects.hashCode(this.taxRate);
        hash = 23 * hash + Objects.hashCode(this.productType);
        hash = 23 * hash + Objects.hashCode(this.area);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.OrderNumber, other.OrderNumber)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        return true;
    }

}
