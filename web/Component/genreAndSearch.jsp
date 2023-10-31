<%-- 
    Document   : genreAndSearch
    Created on : Oct 21, 2023, 4:15:40 PM
    Author     : alexf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12 col-sm-12 col-md-3">
    <div class="Genres">
        <h1>Thể loại</h1>
        <hr />
        <ul class="mt-8">
            <c:forEach items="${requestScope.listGenre}" var="g">
                <li class="${sessionScope.gid==g.genreID?"active":""}"><a  href="home?service=selectGenre&id=${g.genreID}">${g.name}</a></li>
            </c:forEach>
        </ul>
        <br/>
        <hr/>
    </div>
    <div class="search-bar mt-32">
        <div class="search-bar-heading">
            <h1>Bộ lọc game</h1>
            <hr/>
            <br/>
        </div>
        <div class="search-bar-form mt-8">
            <form action="home">
                <input type="hidden" name="service" value="searchGame"/>
                <label for="search">Tìm kiếm:</label><br>
                <input placeholder="Tìm kiếm..." type="text" id="search" name="search"><br>
                <label for="from-date">Thời gian từ:</label><br>
                <input type="date" id="from-date" name="fromDate"><br>
                <label for="to-date">đến:</label><br>
                <input type="date" id="to-date" name="toDate"><br>
                <label for="from-price">Giá từ:</label><br>
                <input placeholder="Giá từ..." type="number" id="from-price"
                       name="fromPrice"><br>
                <label for="to-price">đến:</label><br>
                <input placeholder="Đến..." type="number" id="to-price" name="toPrice"><br>
                <br>
                <input type="submit" value="Tìm kiếm" name="submit">
            </form>
        </div>

    </div>
</div>
