/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author n0200797
 */
public class Item {

    private int itemId;
    
    @NotEmpty(message="You must provide a value for Item Name.")
    @Length(max=50, message="Item Name must be no more than 50 characters in length.")
    private String name;
    
    @DecimalMin(value = "0.01", message="Price must be at least 0.01")
    private BigDecimal price;

    @Min(value = 0, message="Quantity must not be less than 0")
    private int quantity;
    

//**********************MY CONSTRUCTOR******************************************    
    public Item(int itemId) {
        this.itemId = itemId;
    }
//**********************MY GETTERS & SETTERS************************************

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
       
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//***********Remove setters*****************************************************
    public void removeAnItem() {
        this.quantity = quantity - 1;
    }

    public void addQuantity() {
        this.quantity = quantity + 1;
    }

    public void setOtherQuantity(int i) {
        this.quantity = i;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
//******************************************************************************

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.price);
        hash = 97 * hash + this.quantity;
        hash = 97 * hash + this.itemId;
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
        final Item other = (Item) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.itemId != other.itemId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

}
