<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap,entity.Game,entity.GameCart" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:choose>
            <c:when test="${(sessionScope.user.username)==null}">
                <title>Giỏ hàng</title>
            </c:when>
            <c:otherwise>
                <title>Giỏ hàng của ${sessionScope.user.username}</title>
            </c:otherwise>
        </c:choose>
        <link rel="stylesheet" href="assets/css/bootstrap-grid.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/cartStyle.css"/>
    </head>
    <body>
        <jsp:include page="Component/header.jsp"></jsp:include>
        <% 
            HashMap<Integer, GameCart> listCart = (HashMap<Integer, GameCart>) session.getAttribute("gameCart");
            double total = 0;
            for (Integer gc : listCart.keySet()) {
                total+=listCart.get(gc).getGame().getPrice()*listCart.get(gc).getQuantity();
            }
        %>
        <div class="container">
            <h3 style="color: red">${requestScope.msg}</h3>
            <div class="row">
                <div class="mt-32 col-md-8">
                    <div class="show-cart">
                        <div class="show-cart-heading">
                            <h1>Giỏ hàng <i class="fa-solid fa-cart-shopping fa-sm" style="color: #ffffff;"></i></h1>
                            <a href="cart?service=deleteAllCart"><i class="fa-solid fa-trash fa-sm" style="color: #ffffff;"></i>Delete all cart</a>
                        </div>
                        
                        <div class="mt-8 show-cart-table">
                            <div class="mt-8"></div>
                            <table>
                                <c:choose>
                                    <c:when test="${sessionScope.gameCart.size()==0}">
                                        <h3 style="color: red;">Không có sản phầm nào trong giỏ hàng</h3>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <th>Tên game</th>
                                            <th>Ảnh</th>
                                            <th>Giá</th>
                                            <th>Số lượng</th>
                                            <th>Xóa</th>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>

                                <% for (Integer gc : listCart.keySet()) {%>
                                <tr>

                                    <td><%= listCart.get(gc).getGame().getName() %></td>
                                    <td><img src="<%= listCart.get(gc).getGame().getImage() %>"></td>
                                    <td><%= listCart.get(gc).getGame().getPrice() %></td>
                                    <td>
                                        <form action="cart">
                                            <input type="number" name="quantity" value="<%= listCart.get(gc).getQuantity()%>"/>
                                            <input type="hidden" name="id" value="<%= listCart.get(gc).getGame().getGameID() %>"/>
                                            <input type="hidden" name="service" value="updateCart"/>
                                            <input type="submit" value="Cập nhật"/>
                                        </form>

                                    </td>
                                    <td>
                                        <a href="cart?service=deleteCart&gid=<%= listCart.get(gc).getGame().getGameID() %>"><i class="fa-solid fa-trash fa-sm" style="color: #ffffff;"></i></a>
                                    </td>
                                </tr> 
                                <%} %>



                            </table>
                        </div>
                    </div>
                </div>
                <div class=" mt-32 col-md-4">
                    <div class="order-detail mt-8">
                        <div class="order-detail-heading mt-8">
                            <h1>Đơn hàng</h1>
                        </div>
                        <div class="order-detail-table">
                            <table>
                                <tr>
                                    <th>Tên game</th>
                                    <th>Số lượng</th>
                                    <th>Giá</th>
                                </tr>
                                <% for (Integer gc : listCart.keySet()) {%>
                                <tr>
                                    <td><%= listCart.get(gc).getGame().getName() %></td>
                                    <td>x<%= listCart.get(gc).getQuantity()%></td>
                                    <td>$<%= listCart.get(gc).getQuantity()*listCart.get(gc).getGame().getPrice()%></td>
                                </tr>
                                <%}%>
                            </table>
                        </div>
                        <div class="order-detail-summary mt-8">
                            <div>
                                <h2 class="total">Tổng:</h2>
                            </div>
                            <div>
                               <h2 class="total"><%=(total)%>$</h2>
                            </div>
                        </div>
                    </div>
                    <div class="checkout mt-16">
                        <div class="order-heading">
                            <h1>Thông tin khách hàng</h1>
                        </div>
                        <div class="order-form mt-8">
                            <form action="checkout" method="post">
                                <input required placeholder="Tên khách hàng" type="text" name="name"/>
                                <input required placeholder="Địa chỉ khách hàng" type="text" name="address"/>
                                <input required placeholder="Số điện thoại" type="text" name="phone"/>
                                <hr>
                                
                                <input type="submit" value="Đặt hàng"/>
                                
                                
                            </form>
                        </div>
                        
                    </div>

                </div>
            </div>
        </div>

        <%-- chèn font awesome --%>
        <script src="https://kit.fontawesome.com/8922b65fb8.js" crossorigin="anonymous"></script>
    </body>
</html>
