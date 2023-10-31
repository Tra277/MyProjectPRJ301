<%-- 
    Document   : header
    Created on : Oct 21, 2023, 3:53:17 PM
    Author     : alexf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div class="logo">
        <a href="home">
            <img src="assets/img/logo.png" alt="logo">
        </a>

    </div>
    <nav class="nav-bar">
        <ul>
            <li><a href="cart?service=showCart"><i class="fa-solid fa-cart-shopping fa-lg" style="color: #ffffff;"></i></a></li>
            <c:if test="${sessionScope.user!=null}">
                <c:if test="${sessionScope.user.isAdmin == 1}">
                    <li><a href="game?service=displayGame">Dành cho admin</a></li>
                    </c:if>
                <li><a href="#"><i class="fa-solid fa-circle-user fa-2xl" style="color: #ffffff;"></i></a></li>
                <li><a href="#">Xin chào ${sessionScope.user.username}</a></li>
                </c:if>
                <c:if test="${sessionScope.user==null}">

                <li><a href="login.jsp">Đăng nhập</a></li>
                <li><a href="register.jsp">${(sessionScope.user.username==null)?"Đăng ký":""}</a></li>
                </c:if>
                <c:if test="${sessionScope.user!=null}">
                <li><a href="account?service=logout"><i class="fa-solid fa-right-from-bracket fa-lg" style="color: #ffffff;"></i></a></li>
                    </c:if>


        </ul>
    </nav>
</div>
