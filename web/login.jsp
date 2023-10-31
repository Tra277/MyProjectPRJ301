<%-- 
    Document   : login
    Created on : Oct 23, 2023, 11:44:54 AM
    Author     : alexf
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="assets/css/login.css"/>
    </head>
    <body>

        <div class="loginForm">
            <h1>Đăng nhập</h1>
            <p style="color: red;">${requestScope.error}</p>
            <form action="account" method="post">
                <c:set var="cookie" value="${pageContext.request.cookies}"/>
                <input type="hidden" name="service" value="login"/>
                <div class="user-enter mt-16">
                    <input required placeholder="Tên đăng nhập..." type="text" name="name" value="${cookie.cUser.value}"/>
                </div>
                <div class="pass-enter mt-16">
                    <input required placeholder="Mật khẩu..." type="password" name="pass" value="${cookie.cPass.value}">
                </div>
                <div class="remember-me mt-8">
                    <label><input type="checkbox" name="rememberMe" value="ON" ${(cookie.cRem==null)?"":"checked"}>Nhớ mật khẩu</label>
                </div>
                <button class="submit-btn mt-8" type="submit"><i class="fa-solid fa-right-to-bracket fa-sm" style="color: #ffffff;"></i>Đăng nhập</button> 
                <hr/>
                <a class="register" href="register.jsp"><i class="fa-solid fa-user-plus fa-sm" style="color: #ffffff;"></i>Đăng ký</a>
            </form>
            
        </div>
       



        <script src="https://kit.fontawesome.com/8922b65fb8.js" crossorigin="anonymous"></script>

    </body>
</html>
