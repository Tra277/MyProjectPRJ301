/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOAccount;

/**
 *
 * @author alexf
 */
@WebServlet(name = "accountController", urlPatterns = {"/account"})
public class accountController extends HttpServlet {

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
            DAOAccount daoAccount = new DAOAccount();
            HttpSession session = request.getSession();
            String service = request.getParameter("service");
            if (service.equals("login")) {
                String name = request.getParameter("name");
                String pass = request.getParameter("pass");
                //Lam phan remember me
                String rem = request.getParameter("rememberMe");
                if(name==null){
                    name="";
                }
                if(pass==null){
                    pass="";
                }
                Cookie cUser = new Cookie("cUser", name);
                Cookie cPass = new Cookie("cPass", pass);
                Cookie cRem = new Cookie("cRem", rem);
                if(rem!=null){
                    //thoi gian 7 ngay
                    cUser.setMaxAge(60*60*24*7);
                    cPass.setMaxAge(60*60*24*7);
                    cRem.setMaxAge(60*60*24*7);
                }else{
                    cUser.setMaxAge(0);
                    cPass.setMaxAge(0);
                    cRem.setMaxAge(0);
                }
                response.addCookie(cUser);
                response.addCookie(cPass);
                response.addCookie(cRem);
                Account acc = daoAccount.checkLogin(name, pass);
                String msg = "";
                if (acc == null) {
                    msg = "tên đăng nhập hoặc mật khẩu không đúng!";
                    request.setAttribute("error", msg);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    session.setAttribute("user", acc);
                    response.sendRedirect("home");
                }
            }
            if (service.equals("register")) {
                String name = request.getParameter("name");
                String pass = request.getParameter("pass");
                String repass = request.getParameter("repass");
                Account acc = daoAccount.checkAccountExist(name);
                String msg;
                int n = 0;
                if (acc == null) {
                    if (pass.equals(repass)) {
                        n = daoAccount.insertAccount(new Account(name, pass, n));
                        msg = "đăng kí thành công! mời đăng nhập lại.";
                        request.setAttribute("error", msg);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        msg = "mật khẩu nhập lại không chính xác!";
                        request.setAttribute("error", msg);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }

                } else {
                    msg = "tên đăng nhập đã tồn tại!";
                    request.setAttribute("error", msg);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
            if (service.equals("logout")) {
                session.removeAttribute("user");
                response.sendRedirect("home");
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
