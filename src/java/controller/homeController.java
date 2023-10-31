/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Game;
import entity.Genre;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import model.DAOGame;
import model.DAOGenre;

/**
 *
 * @author alexf
 */
@WebServlet(name = "homeController", urlPatterns = {"/home"})
public class homeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            DAOGame daoGame = new DAOGame();
            DAOGenre daoGenre = new DAOGenre();
            HttpSession session = request.getSession();
            if (service == null) {
                service = "loadWebsite";
            }
            if (service.equals("loadWebsite")) {
                /* latest game */
                String msg = request.getParameter("message");
                if (msg == null) {
                    msg = "";
                }
                if (!msg.equals("latestGameSelected")&&!msg.equals("genreSelected")&&!msg.equals("gameSearched")) {
                    Vector<Game> latestGame = daoGame.getGame("select top 1 * from Games \n"
                            + "order by ReleaseDate desc");
                    session.setAttribute("latestGame", latestGame);
                }

                /* latest game list*/
                Vector<Game> listLatest = daoGame.getGame("select top 5 * from Games \n"
                        + "order by ReleaseDate desc");
                request.setAttribute("listLatest", listLatest);
                /* Display game*/
                if(!msg.equals("latestGameSelected")&&!msg.equals("genreSelected")&&!msg.equals("gameSearched")){
                    Vector<Game> listGame = daoGame.getGame("select * from Games");
                    session.setAttribute("listGame", listGame);
                    session.setAttribute("listGameTitle", "Tất cả");
                    session.setAttribute("gid",0);
                }
                

                /*select genre*/
                Vector<Genre> listGenre = daoGenre.getGenre("select * from Genres");
                request.setAttribute("listGenre", listGenre);
                
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
            if (service.equals("selectLatest")) {

                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Vector<Game> latestGame = daoGame.getGame("select * from Games \n"
                            + "where GameID = " + id);
                    session.setAttribute("latestGame", latestGame);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                response.sendRedirect("home?message=latestGameSelected");
            }
            if(service.equals("selectGenre")){
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Vector<Game> listSelected = daoGame.getGame("select * from Games \n"
                            + "where GenreID = " + id);
                    session.setAttribute("listGame", listSelected);
                    // get Kind of game
                    Vector<Genre> titleGame = daoGenre.getGenre("select * from Genres where GenreID ="+id);
                    session.setAttribute("listGameTitle",titleGame.get(0).getName());
                    session.setAttribute("gid",id);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                response.sendRedirect("home?message=genreSelected");
            }
            if(service.equals("searchGame")){
                String search_rq = request.getParameter("search");
                String fromDate_rq = request.getParameter("fromDate");
                String toDate_rq = request.getParameter("toDate");
                String fromPrice_rq = request.getParameter("fromPrice");
                String toPrice_rq = request.getParameter("toPrice");
                try {
                    String search = (search_rq==null || search_rq.equals(""))?null:search_rq;
                    Date fromDate = (fromDate_rq==null||fromDate_rq.equals(""))?null:Date.valueOf(fromDate_rq);
                    Date toDate = (toDate_rq==null||toDate_rq.equals(""))?null:Date.valueOf(toDate_rq);
                    Double fromPrice = (fromPrice_rq==null||fromPrice_rq.equals(""))?null:Double.parseDouble(fromPrice_rq);
                    Double toPrice = (toPrice_rq==null||toPrice_rq.equals(""))?null:Double.parseDouble(toPrice_rq);
                    Vector<Game> list = daoGame.searchGame(search, fromDate, toDate, fromPrice, toPrice);
                    session.setAttribute("listGame", list);
                    String resultQuantity = (list.size()>0)?"Tìm thấy "+list.size()+" Kết quả":"Không tìm thấy kết quả phù hợp..";
                    session.setAttribute("listGameTitle", resultQuantity);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
                response.sendRedirect("home?message=gameSearched");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
