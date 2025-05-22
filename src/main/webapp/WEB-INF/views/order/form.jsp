<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title><c:choose><c:when test="${order != null}">Edit Order</c:when><c:otherwise>Create New Order</c:otherwise></c:choose></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="content-container">
    <h1><c:choose><c:when test="${order != null}">Edit Order</c:when><c:otherwise>Create New Order</c:otherwise></c:choose></h1>

    <form action="${pageContext.request.contextPath}/orders" method="post">
        <c:if test="${order != null}">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="${order.id}"/>
        </c:if>
        <c:if test="${order == null}">
            <input type="hidden" name="action" value="create"/>
        </c:if>

        <label for="customerId">Customer:</label>
        <select id="customerId" name="customerId">
            <c:forEach var="customer" items="${customers}">
                <option value="${customer.id}" <c:if test="${order != null && order.customerId == customer.id}">selected</c:if>>${customer.name}</option>
            </c:forEach>
        </select><br/><br/>

        <h2>Items:</h2>
        <div id="orderItemsContainer">
            <c:if test="${order != null && not empty order.items}">
                <c:forEach var="item" items="${order.items}" varStatus="loop">
                    <div>
                        <label for="pizzaId_${loop.index}">Pizza:</label>
                        <select id="pizzaId_${loop.index}" name="pizzaId">
                            <c:forEach var="pizza" items="${pizzas}">
                                <option value="${pizza.id}" <c:if test="${item.pizzaId == pizza.id}">selected</c:if>>${pizza.name} - ${pizza.price}</option>
                            </c:forEach>
                        </select>

                        <label for="quantity_${loop.index}">Quantity:</label>
                        <input type="number" id="quantity_${loop.index}" name="quantity" value="${item.quantity}" min="1"/>
                        <button type="button" onclick="removeOrderItem(this)">Remove</button>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <button type="button" onclick="addOrderItem()">Add Item</button><br/><br/>

        <input type="submit" value="Save Order"/>
    </form>

    <p><a href="${pageContext.request.contextPath}/orders?action=list">Back to List</a></p>

    <script>
        function addOrderItem() {
            const container = document.getElementById('orderItemsContainer');
            const newItemDiv = document.createElement('div');
            newItemDiv.innerHTML = `
                <label for="pizzaId">Pizza:</label>
                <select name="pizzaId">
                    <c:forEach var="pizza" items="${pizzas}">
                        <option value="${pizza.id}">${pizza.name} - ${pizza.price}</option>
                    </c:forEach>
                </select>

                <label for="quantity">Quantity:</label>
                <input type="number" name="quantity" value="1" min="1"/>
                <button type="button" onclick="removeOrderItem(this)">Remove</button>
            `;
            container.appendChild(newItemDiv);
        }

        function removeOrderItem(button) {
            button.parentElement.remove();
        }
    </script>
    </div>
</body>
</html> 