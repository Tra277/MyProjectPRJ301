<%-- 
    Document   : displayGame
    Created on : Oct 27, 2023, 5:07:11 PM
    Author     : alexf
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage</title>
        <link rel="stylesheet" href="assets/css/style.css"/>
        <link rel="stylesheet" href="assets/css/bootstrap-grid.min.css"/>
        <link rel="stylesheet" href="assets/css/manageGame.css"/>
    </head>
    <body>
        <jsp:include page="Component/header.jsp"></jsp:include>

            <div class="container">
                <h3 style="color: red;">${requestScope.msg}</h3>
            <div class="row">
                <div class="col-md-12">
                    <div class="manage-game mt-16">
                        <div class="game-manager-header">
                            <div>
                                <h1>Manage Game</h1>
                            </div>

                            <div class="general-action">
                                <a href="game?service=insertGame"><i class="fa-solid fa-circle-plus fa-sm" style="color: #fcfcfc;"></i>Add game</a>
                            </div>
                        </div>
                        <div class="game-manager-table">
                            <table>
                                <tr>

                                    <th>ID</th>
                                    <th>Tên game</th>
                                    <th>Ảnh</th>
                                    <th>Giá</th>
                                    <th>Thao tác</th>
                                </tr>
                                <c:forEach var="gameItem" items="${requestScope.data}">
                                    <tr>

                                        <td>${gameItem.gameID}</td>
                                        <td>${gameItem.name}</td>
                                        <td><img src="${gameItem.image}" alt="alt"/></td>
                                        <td>$${gameItem.price}</td>
                                        <td>
                                            <a href="game?service=updateGame&gid=${gameItem.gameID}"><i class="fa-solid fa-pen-to-square fa-sm" style="color: #ffffff;"></i></a>
                                            <a href="game?service=deleteGame&gid=${gameItem.gameID}"><i class="fa-solid fa-trash fa-sm" style="color: #ffffff;"></i></a>
                                            <a href="#"><i class="fa-solid fa-key fa-sm" style="color: #ffffff;"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>   

                            </table>
                        </div>
                        <div class="go-to-home mt-16">
                            <a href="home"><i class="fa-solid fa-house fa-sm" style="color: #ffffff;"> </i>Trang chủ</a>
                        </div>

                    </div>
                </div>
            </div>
        </div>


        <script src="https://kit.fontawesome.com/8922b65fb8.js" crossorigin="anonymous"></script>
    </body>
</html>
