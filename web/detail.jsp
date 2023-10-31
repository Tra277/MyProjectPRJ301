
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
        <link rel="stylesheet" href="assets/css/detailStyle.css"/>
    </head>

    <body>
        <jsp:include page="Component/header.jsp"></jsp:include>

            <div class="main-content mt-32">
                <div class="grid">
                    <div class="container">
                        <div class="row">
                        <jsp:include page="Component/genreAndSearch.jsp"></jsp:include>
                            <div class="col-xs-12 col-sm-12 col-md-9">       
                            <c:forEach items="${requestScope.gameDetail}" var="gd">
                                <div class="detail-game-title">
                                    <h1>${gd.name}</h1>
                                </div>
                                <div class="display-game-detail mt-16">
                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="detail-game-img">
                                                <img src="${gd.image}" alt="alt"/>
                                            </div>
                                            <div class="game-detail-description mt-8">
                                                <p>${gd.description}</p> 
                                                <br/><p>Ngày phát hành:${gd.releaseDate}</p>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="detail-logo">
                                                <img src="assets/img/logo.png" alt="alt"/>
                                            </div>
                                            <div class="detail-price">
                                                <p>$${gd.price}</p>
                                            </div>
                                            <a href="" class="buyNow">BUY NOW</a>
                                            <a href="cart?service=addToCart&id=${gd.gameID}" class="addToCart">Add to Cart</a>
                                        </div>
                                            
                                    </div>
                                </div>
                            </c:forEach>

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
