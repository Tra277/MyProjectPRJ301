/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author alexf
 */
public class DAOOrderDetail extends DBConnect {

    public void insertOrderDetail(OrderDetail o) {

        String sql = "INSERT INTO [dbo].[OrderDetails]\n"
                + "           ([UserID]\n"
                + "           ,[OrderID]\n"
                + "           ,[GameID]\n"
                + "           ,[Quantity])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(o.getUserID()!=0){
                ps.setInt(1, o.getUserID());
            }else{
                ps.setNull(1, java.sql.Types.INTEGER);
            }
            
            ps.setInt(2, o.getOrderID());
            ps.setInt(3, o.getGameID());
            ps.setInt(4, o.getQuantity());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
