/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alexf
 */
public class DAOOrder extends DBConnect {

    public int insertOrder(Order o) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([Username]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[total]\n"
                + "           ,[date])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        int OrderID = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, o.getUsername());
            ps.setString(2, o.getAddress());
            ps.setString(3, o.getPhone());
            ps.setDouble(4, o.getTotal());
            ps.setDate(5, o.getDate());
            int n = ps.executeUpdate();
            try(ResultSet generatedKeys = ps.getGeneratedKeys() ){
                if(generatedKeys.next()){
                    OrderID = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return OrderID;
    }
}
