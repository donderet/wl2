package ua.kpi.controller;

import ua.kpi.model.Order;
import ua.kpi.model.Customer;
import ua.kpi.model.Pizza;
import ua.kpi.model.OrderItem;
import ua.kpi.service.OrderService;
import ua.kpi.service.CustomerService;
import ua.kpi.service.PizzaService;
import ua.kpi.service.impl.OrderServiceImpl;
import ua.kpi.service.impl.CustomerServiceImpl;
import ua.kpi.service.impl.PizzaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orders")
public class OrderController extends HttpServlet {

    private OrderService orderService = new OrderServiceImpl();
    private CustomerService customerService = new CustomerServiceImpl();
    private PizzaService pizzaService = new PizzaServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; 
        }

        try {
            switch (action) {
                case "list":
                    listOrders(request, response);
                    break;
                case "new":
                    showNewForm(request, response);
                    break;
                case "create":
                    createOrder(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateOrder(request, response);
                    break;
                case "delete":
                    deleteOrder(request, response);
                    break;
                case "view":
                    viewOrder(request, response);
                    break;
                default:
                    listOrders(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException("Error processing order request: " + ex.getMessage(), ex); 
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = orderService.getAllOrders();
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/WEB-INF/views/order/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerService.getAllCustomers();
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        request.setAttribute("customers", customers);
        request.setAttribute("pizzas", pizzas);
        request.getRequestDispatcher("/WEB-INF/views/order/form.jsp").forward(request, response);
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            String[] pizzaIds = request.getParameterValues("pizzaId");
            String[] quantities = request.getParameterValues("quantity");

            Order newOrder = new Order();
            newOrder.setCustomerId(customerId);
            List<OrderItem> items = new ArrayList<>();
            if (pizzaIds != null && quantities != null) {
                for (int i = 0; i < pizzaIds.length; i++) {
                    int pizzaId = Integer.parseInt(pizzaIds[i]);
                    int quantity = Integer.parseInt(quantities[i]);
                    if (quantity > 0) {
                         
                        items.add(new OrderItem(0, 0, pizzaId, quantity));
                    }
                }
            }
            newOrder.setItems(items);

            orderService.createOrder(newOrder);

            response.sendRedirect(request.getContextPath() + "/orders?action=list");
        } catch (NumberFormatException e) {
             request.setAttribute("errorMessage", "Invalid input for customer or pizza details.");
             showNewForm(request, response); 
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
            int id = Integer.parseInt(request.getParameter("id"));
            Order existingOrder = orderService.getOrderById(id);
            if (existingOrder != null) {
                List<Customer> customers = customerService.getAllCustomers();
                List<Pizza> pizzas = pizzaService.getAllPizzas();
                request.setAttribute("order", existingOrder);
                request.setAttribute("customers", customers);
                request.setAttribute("pizzas", pizzas);
                request.getRequestDispatcher("/WEB-INF/views/order/form.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID format.");
        }
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            String[] pizzaIds = request.getParameterValues("pizzaId");
            String[] quantities = request.getParameterValues("quantity");

            Order order = orderService.getOrderById(id);
            if (order != null) {
                order.setCustomerId(customerId);
                List<OrderItem> items = new ArrayList<>();
                 if (pizzaIds != null && quantities != null) {
                    for (int i = 0; i < pizzaIds.length; i++) {
                        int pizzaId = Integer.parseInt(pizzaIds[i]);
                        int quantity = Integer.parseInt(quantities[i]);
                         if (quantity > 0) {
                            items.add(new OrderItem(0, id, pizzaId, quantity));
                         }
                    }
                }
                order.setItems(items);

                orderService.updateOrder(order);

                response.sendRedirect(request.getContextPath() + "/orders?action=list");
            } else {
                 response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found for update.");
            }
        } catch (NumberFormatException e) {
             request.setAttribute("errorMessage", "Invalid input for order ID, customer, or pizza details.");
             showEditForm(request, response); 
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            orderService.deleteOrder(id);
            response.sendRedirect(request.getContextPath() + "/orders?action=list");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID format for deletion.");
        }
    }

     private void viewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Order order = orderService.getOrderById(id);
             if (order != null) {
                 
                 Customer customer = customerService.getCustomerById(order.getCustomerId());
                 request.setAttribute("customer", customer);

                 
                 List<Pizza> pizzas = pizzaService.getAllPizzas();
                 request.setAttribute("pizzas", pizzas);

                 request.setAttribute("order", order);
                 request.getRequestDispatcher("/WEB-INF/views/order/view.jsp").forward(request, response);
             } else {
                 
                 response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found.");
             }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID format.");
        }
    }
} 