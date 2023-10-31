<%-- 
    Document   : login
    Created on : Oct 23, 2023, 11:44:54 AM
    Author     : alexf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký</title>
        <link rel="stylesheet" href="assets/css/login.css"/>
    </head>
    <body>

        <div class="loginForm">
            <h1>Đăng ký</h1>
            <p style="color: red;">${requestScope.error}</p>
            <form action="account">
                <input type="hidden" name="service" value="register"/>
                <div class="user-enter mt-16">
                    <input placeholder="Tên đăng nhập..." type="text" name="name"/>
                </div>
                <div class="pass-enter mt-16">
                    <input placeholder="Mật khẩu..." type="password" name="pass">
                </div>
                <div class="pass-enter mt-16">
                    <input placeholder="Nhập lại mật khẩu..." type="password" name="repass">
                </div>
                <button class="submit-btn mt-16" type="submit"><i class="fa-solid fa-user-plus fa-sm" style="color: #ffffff;"></i>Đăng ký</button> 
                
            </form>
        </div>



        <script src="https://kit.fontawesome.com/8922b65fb8.js" crossorigin="anonymous"></script>

    </body>
</html>
