/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
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
import java.util.Vector;
import model.DAOGame;
import model.DAOGenre;

/**
 *
 * @author alexf
 */
@WebServlet(name = "gameController", urlPatterns = {"/game"})
public class gameController extends HttpServlet {

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
            DAOGame daoGame = new DAOGame();
            DAOGenre daoGenre = new DAOGenre();
            String service = request.getParameter("service");
            HttpSession session = request.getSession();
            if (service == null) {
                service = "displayGame";
            }
            //kiem tra user co phai admin khong, neu khong lap tuc quay lai trang home
            Account a = (Account) session.getAttribute("user");
            if (a.getIsAdmin() == 0) {
                response.sendRedirect("home");
            } else {
                if (service.equals("displayGame")) {
                    String message = request.getParameter("message");
                    if (message == null) {
                        message = "";
                    }
                    Vector<Game> listGame = daoGame.getGame("select * from Games");
                    request.setAttribute("data", listGame);
                    request.setAttribute("msg", message);
                    request.getRequestDispatcher("manager.jsp").forward(request, response);
                }
                if (service.equals("deleteGame")) {
                    String msg = "";
                    try {
                        String id_raw = request.getParameter("gid");
                        int id = Integer.parseInt(id_raw);
                        int n = daoGame.deleteGame(id);

                        if (n > 0) {
                            msg = "delete successfully !";
                        } else {
                            msg = "Oops... something went wrong!!!";
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    response.sendRedirect("game?message=" + msg);
                }
                if (service.equals("insertGame")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        Vector<Genre> listGenre = daoGenre.getGenre("select * from Genres");
                        request.setAttribute("listGenre", listGenre);
                        request.getRequestDispatcher("insertGame.jsp").forward(request, response);
                    } else {
                        int n = 0;
                        String msg = "";
                        try {
                            //lay ID lon nhat trong GameID
                            Vector<Game> gameMaxID = daoGame.getGame("select top 1 * from Games order by GameID desc; ");

                            String name = request.getParameter("name");
                            String desc = request.getParameter("desc");
                            Double price = Double.parseDouble(request.getParameter("price"));
                            int genreId = Integer.parseInt(request.getParameter("genreId"));
                            String img = "images/" + request.getParameter("imagePath");
                            Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
                            Game game = new Game(gameMaxID.get(0).getGameID() + 1, name, desc, price, genreId, img, releaseDate);
                            n = daoGame.insertGame(game);
                            if (n > 0) {
                                msg = "insert successfully";
                            } else {
                                msg = "Something is wrong!!";
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(e);
                        }
                        response.sendRedirect("game?message=" + msg);
                    }
                }
                if (service.equals("updateGame")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        String gid = request.getParameter("gid");
                        Vector<Genre> listGenre = daoGenre.getGenre("select * from Genres");
                        Vector<Game> gameById = daoGame.getGame("select * from Games where GameID = " + gid);
                        request.setAttribute("gameById", gameById.get(0));
                        request.setAttribute("listGenre", listGenre);
                        request.getRequestDispatcher("updateGame.jsp").forward(request, response);
                    } else {
                        int n = 0;
                        String msg = "";
                        try {
                            //lay ID lon nhat trong GameID
                            int gid = Integer.parseInt(request.getParameter("gid"));

                            String name = request.getParameter("name");
                            String desc = request.getParameter("desc");
                            Double price = Double.parseDouble(request.getParameter("price"));
                            int genreId = Integer.parseInt(request.getParameter("genreId"));
                            String img =  request.getParameter("imagePath");
                            Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
                            Game game = new Game(gid, name, desc, price, genreId, img, releaseDate);
                            n = daoGame.updateGame(game);
                            if (n > 0) {
                                msg = "update successfully";
                            } else {
                                msg = "Something is wrong!!";
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(e);
                        }
                        response.sendRedirect("game?message=" + msg);
                    }
                }
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
