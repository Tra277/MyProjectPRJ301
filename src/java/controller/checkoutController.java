/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import entity.GameCart;
import entity.Order;
import entity.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import model.DAOOrder;
import model.DAOOrderDetail;

/**
 *
 * @author alexf
 */
@WebServlet(name = "checkoutController", urlPatterns = {"/checkout"})
public class checkoutController extends HttpServlet {

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
            HttpSession session = request.getSession();
            DAOOrder dOrder = new DAOOrder();
            DAOOrderDetail dOrderDetail = new DAOOrderDetail();
            Account a = (Account) session.getAttribute("user");
            HashMap<Integer, GameCart> listCart = (HashMap<Integer, GameCart>) session.getAttribute("gameCart");
            Date date = Date.valueOf(LocalDate.now().toString());
            if (listCart.size() == 0) {
                String checkoutErr = "Chưa có sản phẩm, không thể đặt hàng!";
                request.setAttribute("msg", checkoutErr);
                request.getRequestDispatcher("showCart.jsp").forward(request, response);
            } else if(a != null && a.getIsAdmin() == 1){
                String checkoutErr = "Admin không thể đặt hàng";
                request.setAttribute("msg", checkoutErr);
                request.getRequestDispatcher("showCart.jsp").forward(request, response);
            }else{
                String name = request.getParameter("name");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                double total = 0;
                for (Integer gc : listCart.keySet()) {
                    total += listCart.get(gc).getGame().getPrice() * listCart.get(gc).getQuantity();
                }
                Order o = new Order(name, address, phone, total, date);
                int orderID = dOrder.insertOrder(o);
                //Order detail 
                int userID = (a==null)?0:a.getUserID();
                for (Integer gc : listCart.keySet()) {
                    
                    int GameID = listCart.get(gc).getGame().getGameID();
                    int quantity = listCart.get(gc).getQuantity();
                    OrderDetail od = new OrderDetail(userID, orderID, GameID, quantity);
                    dOrderDetail.insertOrderDetail(od);
                }
                //reset gio hang
                 listCart.clear();
                 request.setAttribute("msg","Bạn đã đặt hàng thành công!");
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
