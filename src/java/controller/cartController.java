/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Game;
import entity.GameCart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import model.DAOGame;

/**
 *
 * @author alexf
 */
@WebServlet(name = "cartController", urlPatterns = {"/cart"})
public class cartController extends HttpServlet {

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
            HttpSession session = request.getSession();
            String service = request.getParameter("service");
            if (service == null) {
                service = "showCart";
            }
            if (service.equals("addToCart")) {
                int id = Integer.parseInt(request.getParameter("id"));
                System.out.println(id);
                DAOGame daoGame = new DAOGame();
                GameCart gameCart;
                Game game = daoGame.getGame("select * from Games where GameID = " + id).get(0);
                HashMap<Integer, GameCart> listCart = (HashMap<Integer, GameCart>) session.getAttribute("gameCart");
                //session chua duoc luu
                if (listCart == null) {
                    listCart = new HashMap<Integer, GameCart>();
                    gameCart = new GameCart(game, 1);
                    listCart.put(id, gameCart);
                } else {
                    if (listCart.containsKey(id)) {
                        gameCart = listCart.get(id);
                        gameCart.quantityIncrement();
                    } else {
                        gameCart = new GameCart(game, 1);
                        listCart.put(id, gameCart);
                    }
                }
                session.setAttribute("gameCart", listCart);
                request.getRequestDispatcher("showCart.jsp").forward(request, response);
            }
            if (service.equals("showCart")) {
                HashMap<Integer, GameCart> listCart = (HashMap<Integer, GameCart>) session.getAttribute("gameCart");
                if (listCart == null) {
                    listCart = new HashMap<Integer, GameCart>();
                }
                session.setAttribute("gameCart", listCart);
                request.getRequestDispatcher("showCart.jsp").forward(request, response);
            }
            if (service.equals("updateCart")) {
                try {
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    DAOGame daoGame = new DAOGame();
                    GameCart gameCart;
                    Game game = daoGame.getGame("select * from Games where GameID = " + id).get(0);
                    HashMap<Integer, GameCart> listCart = (HashMap<Integer, GameCart>) session.getAttribute("gameCart");
                    //session chua duoc luu

                    if (listCart.containsKey(id)) {
                        if (quantity <= 0) {
                            listCart.remove(id);
                        } else {
                            gameCart = listCart.get(id);
                            gameCart.setQuantity(quantity);
                        }

                    }

                    session.setAttribute("gameCart", listCart);
                    request.getRequestDispatcher("showCart.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (service.equals("deleteCart")) {
                String gid_raw = request.getParameter("gid");
                int gid = Integer.parseInt(gid_raw);
                HashMap<Integer, GameCart> listCart = (HashMap<Integer, GameCart>) session.getAttribute("gameCart");
                listCart.remove(gid);
                session.setAttribute("gameCart", listCart);
                request.getRequestDispatcher("showCart.jsp").forward(request, response);
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
