<%-- 
    Document   : latestGame
    Created on : Oct 21, 2023, 4:10:12 PM
    Author     : alexf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="intro mt-32">
    <div class="grid">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-9">
                    <div class="latest-game">
                        <c:forEach items="${sessionScope.latestGame}" var="latest">
                            <a href="detail?service=showDetail&gid=${latest.gameID}">
                                <img src="${latest.image}" alt="">
                                <div class="game-name">
                                    <p>Khả dụng</p>
                                    <h2>${latest.name}</h2>
                                    <h4>Chỉ từ $${latest.price}</h4><br/>
                                    <a href="cart?service=addToCart&id=${latest.gameID}">
                                        <div class="latest-add-to-cart"><i class="fa-solid fa-circle-plus fa-sm" style="color: #f5f5f5;"></i>Add to Cart</div>
                                    </a>
                                    
                                </div>

                            </a>
                        </c:forEach>
                    </div>

                </div>
                <div class="col-xs-12 col-sm-12 col-md-3">
                    <div class="latest-game-list">
                        <ul>
                            <!-- lặp lại cái li -->
                            <c:forEach items="${requestScope.listLatest}" var="l">
                                <li>
                                    <a href="home?service=selectLatest&id=${l.gameID}">
                                        <div class="latest-item">
                                            <img src="${l.image}" alt="">
                                            <div>${l.name}</div>
                                        </div>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
