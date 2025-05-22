<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="content-container">
    <h1>Order List</h1>

    <p><a href="${pageContext.request.contextPath}/orders?action=new">Create New Order</a></p>

    <table border="1">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Customer ID</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.customerId}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/orders?action=view&id=${order.id}">View</a>
                        <a href="${pageContext.request.contextPath}/orders?action=edit&id=${order.id}">Edit</a>
                        <a href="${pageContext.request.contextPath}/orders?action=delete&id=${order.id}" onclick="return confirm('Are you sure you want to delete this order?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
</body>
</html> 