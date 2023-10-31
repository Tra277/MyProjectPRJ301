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
        <title>Chỉnh sửa game</title>
        <link rel="stylesheet" href="assets/css/login.css"/>
    </head>
    <body>

        <div class="loginForm">
            <h1>Sửa game</h1>
            <form action="game">                
                <input type="hidden" name="service" value="updateGame" />
                <c:set var="gameUpdated" value="${requestScope.gameById}"/>
                <div class="user-enter mt-16">
                    <input readonly type="text" name="gid" value="${gameUpdated.gameID}"/>
                </div>
                <div class="user-enter mt-16">
                    <input required placeholder="Name..." type="text" name="name" value="${gameUpdated.name}"/>
                </div>
                <div class="pass-enter mt-16">
                    <input required placeholder="Miêu tả..." type="text" name="desc" value="${gameUpdated.description}" >
                </div>
                <div class="pass-enter mt-16">
                    <input required placeholder="Giá..." type="text" name="price" value="${gameUpdated.price}">
                </div>
                <div class="pass-enter mt-16">
                    <select name="genreId">
                        <c:forEach var="lg" items="${requestScope.listGenre}">
                        <option ${(lg.genreID == gameUpdated.genreID)?"selected":""} value="${lg.genreID}">${lg.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="pass-enter mt-16">
                    Ảnh:
                    <input placeholder="Ảnh..." type="text" name="imagePath" value="${gameUpdated.image}">
                </div>
                <div class="pass-enter mt-16">
                    Ngày ra mắt:
                    <input placeholder="Ngày ra mắt" type="date" name="releaseDate" value="${gameUpdated.releaseDate}">
                </div>
                <button name="submit" class="submit-btn mt-8" type="submit"><i class="fa-solid fa-right-to-bracket fa-sm" style="color: #ffffff;"></i>Sửa</button> 
                <hr/>
                
            </form>
            
        </div>
       



        <script src="https://kit.fontawesome.com/8922b65fb8.js" crossorigin="anonymous"></script>

    </body>
</html>
