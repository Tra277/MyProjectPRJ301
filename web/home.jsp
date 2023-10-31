<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Game4U VN</title>
        <link rel="stylesheet" href="assets/css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>

    <body>
        <jsp:include page="Component/header.jsp"></jsp:include>
        <jsp:include page="Component/latestGame.jsp"></jsp:include>

            <div class="main-content mt-32">
                <div class="grid">
                    <div class="container">
                        <div class="row">
                        <jsp:include page="Component/genreAndSearch.jsp"></jsp:include>
                            <div class="col-xs-12 col-sm-12 col-md-9">
                                <div class="list-game-title">
                                    <h1>${sessionScope.listGameTitle}</h1>
                            </div>
                            <div class="display-game">
                                <div class="row">
                                    <c:forEach items="${sessionScope.listGame}" var="i">
                                        <!-- lặp lại cái này  -->
                                        <div class="col-md-4">
                                            <a href="detail?service=showDetail&gid=${i.gameID}">
                                                <div class="display-game-item mt-8">
                                                    <div class="game-item-img">
                                                        <img src="${i.image}" alt="">
                                                    </div>
                                                    <div class="game-item-content">
                                                        <h2>${i.name}</h2>
                                                        <p class="description">${i.description}</p>
                                                        <h3>$${i.price}</h3>
                                                        <div class="add-to-cart">

                                                        </div>
                                                    </div>

                                                </div>
                                            </a>

                                        </div>
                                        <!--  -->
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <br/>
        <jsp:include page="Component/footer.jsp"></jsp:include>
        <!-- add font -->
        <script src="https://kit.fontawesome.com/8922b65fb8.js" crossorigin="anonymous"></script>
    </body>

</html>
