/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author alexf
 */
public class GameCart {

    private Game game;
    private int quantity;

    public GameCart() {
    }

    public GameCart(Game game, int quantity) {
        this.game = game;
        this.quantity = quantity;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void quantityIncrement(){
        this.setQuantity(this.getQuantity()+1);
    }
}
