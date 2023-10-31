/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Genre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author alexf
 */
public class DAOGenre extends DBConnect{
    public Vector<Genre> getGenre(String sql){
        Vector<Genre> listGenre = new Vector<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Genre g = new Genre(id, name);
                listGenre.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listGenre;
    } 
}
