/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Game;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author alexf
 */
public class DAOGame extends DBConnect {

    public int insertGame(Game g) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Games]\n"
                + "           ([GameID]\n"
                + "           ,[Name]\n"
                + "           ,[Description]\n"
                + "           ,[Price]\n"
                + "           ,[GenreID]\n"
                + "           ,[Image]\n"
                + "           ,[ReleaseDate])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, g.getGameID());
            ps.setString(2, g.getName());
            ps.setString(3, g.getDescription());
            ps.setDouble(4, g.getPrice());
            ps.setInt(5, g.getGenreID());
            ps.setString(6, g.getImage());
            ps.setDate(7, g.getReleaseDate());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return n;
    }

    public int deleteGame(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Games]\n"
                + "      WHERE GameID = " + id;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int updateGame(Game g) {
        int n = 0;
        String sql = "UPDATE [dbo].[Games]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[GenreID] = ?\n"
                + "      ,[Image] = ?\n"
                + "      ,[ReleaseDate] = ?\n"
                + " WHERE [GameID] = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);           
            ps.setString(1, g.getName());
            ps.setString(2, g.getDescription());
            ps.setDouble(3, g.getPrice());
            ps.setInt(4, g.getGenreID());
            ps.setString(5, g.getImage());
            ps.setDate(6, g.getReleaseDate());
            ps.setInt(7, g.getGameID());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }
    public Vector<Game> searchGame(String name,Date fromDate, Date toDate,Double fromPrice,Double toPrice){
        Vector<Game> listGame = new Vector<>();
        String sql = "select * from Games where 1=1";
        if(name!=null){
            sql+="and Name like '%"+name+"%'";
        }
        if(fromDate!=null){
            sql+="and ReleaseDate >= '"+fromDate+"'";
        }
        if(toDate!=null){
            sql+="and ReleaseDate <= '"+toDate+"'";
        }
        if(fromPrice!=null){
            sql+="and Price>="+fromPrice;
        }
        if(toPrice!=null){
            sql+="and Price<="+toPrice;
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String gname = rs.getString(2);
                String description = rs.getString(3);
                Double price = rs.getDouble(4);
                int genreID = rs.getInt(5);
                String img = rs.getString(6);
                Date releaseDate = rs.getDate(7);
                Game g= new Game(id, gname, description, price, genreID, img, releaseDate);
                listGame.add(g);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listGame;
    }
    public Vector<Game> getGame(String sql){
        Vector<Game> listGame = new Vector<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                Double price = rs.getDouble(4);
                int genreID = rs.getInt(5);
                String img = rs.getString(6);
                Date releaseDate = rs.getDate(7);
                Game g= new Game(id, name, description, price, genreID, img, releaseDate);
                listGame.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listGame;
    }
}
