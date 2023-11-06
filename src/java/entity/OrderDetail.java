/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author alexf
 */
public class OrderDetail {
    private int cartID;
    private int userID;
    private int orderID;
    private int gameID;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int userID, int orderID, int gameID, int quantity) {
        this.userID = userID;
        this.orderID = orderID;
        this.gameID = gameID;
        this.quantity = quantity;
    }

    public OrderDetail(int cartID, int userID, int orderID, int gameID, int quantity) {
        this.cartID = cartID;
        this.userID = userID;
        this.orderID = orderID;
        this.gameID = gameID;
        this.quantity = quantity;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
