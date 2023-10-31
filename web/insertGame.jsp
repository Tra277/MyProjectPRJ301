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
        <title>Thêm game</title>
        <link rel="stylesheet" href="assets/css/login.css"/>
    </head>
    <body>

        <div class="loginForm">
            <h1>Thêm game</h1>
            <form action="game">
                <input type="hidden" name="service" value="insertGame" />
                <div class="user-enter mt-16">
                    <input required placeholder="Name..." type="text" name="name" />
                </div>
                <div class="pass-enter mt-16">
                    <input required placeholder="Miêu tả..." type="text" name="desc" >
                </div>
                <div class="pass-enter mt-16">
                    <input required placeholder="Giá..." type="text" name="price" >
                </div>
                <div class="pass-enter mt-16">
                    <select name="genreId">
                        <c:forEach var="lg" items="${requestScope.listGenre}">
                        <option value="${lg.genreID}">${lg.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="pass-enter mt-16">
                    Ảnh:
                    <input placeholder="Ảnh..." type="file" name="imagePath">
                </div>
                <div class="pass-enter mt-16">
                    Ngày ra mắt:
                    <input placeholder="Ngày ra mắt" type="date" name="releaseDate">
                </div>
                <button name="submit" class="submit-btn mt-8" type="submit"><i class="fa-solid fa-right-to-bracket fa-sm" style="color: #ffffff;"></i>Thêm</button> 
                <hr/>
                
            </form>
            
        </div>
       



        <script src="https://kit.fontawesome.com/8922b65fb8.js" crossorigin="anonymous"></script>

    </body>
</html>
