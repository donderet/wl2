<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order Details</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="content-container">
    <h1>Order Details</h1>

    <c:if test="${order != null}">
        <p><strong>Order ID:</strong> ${order.id}</p>
        <p><strong>Customer:</strong> ${customer.name} (${customer.contactInfo})</p>

        <h2>Items:</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Pizza Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${order.items}">
                    <c:set var="currentPizza" value="${null}"/>
                    <c:forEach var="pizza" items="${pizzas}">
                        <c:if test="${pizza.id == item.pizzaId}">
                            <c:set var="currentPizza" value="${pizza}"/>
                        </c:if>
                    </c:forEach>
                    <tr>
                        <td>${currentPizza.name}</td>
                        <td>${item.quantity}</td>
                        <td>${currentPizza.price}</td>
                        <td>${currentPizza.price * item.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/orders?action=list">Back to List</a></p>
    </div>
</body>
</html> 