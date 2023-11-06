/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author alexf
 */
public class Order {
    private int orderID;
    private String username;
    private String address;
    private String phone;
    private Double total;
    private Date date;
    
    public Order() {
    }

    public Order(int orderID, String username, String address, String phone, Double total, Date date) {
        this.orderID = orderID;
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.date = date;
    }

    public Order(String username, String address, String phone, Double total, Date date) {
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.date = date;
    }
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
